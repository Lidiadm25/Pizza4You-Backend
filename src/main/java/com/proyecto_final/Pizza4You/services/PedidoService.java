package com.proyecto_final.Pizza4You.services;

import com.proyecto_final.Pizza4You.dto.*;
import com.proyecto_final.Pizza4You.model.*;

import com.proyecto_final.Pizza4You.repositorio.*;
import com.proyecto_final.Pizza4You.error.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PedidoService {

	private static final int UMBRAL_STOCK_BAJO = 10;

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private TamanoRepository tamanoRepository;

	@Autowired
	private MasaRepository masaRepository;

	@Autowired
	private IngredienteRepository ingredienteRepository;

	@Autowired
	private DireccionRepository direccionRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private DetallePedidoRepository detallePedidoRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private DetallePedidoIngredienteRepository detalleIngredienteRepository;

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Autowired
	private ProductoIngredienteRepository productoIngredienteRepository;

	@Transactional
	public PedidoResponseDTO guardarPedido(PedidoRequest request) {
		
		boolean error = hayErrorStock(request);
		if (error) {
			throw new ErrorStock("No hay suficiente stock");
		}
		//System.out.println("PEDIDO RECIBIDO");
		// System.out.println(request);
		 Cliente cliente = clienteRepository.findById(request.getIdCliente())
				    .orElseThrow(() -> new RuntimeException("Error: No se encuentra el cliente " + request.getIdCliente()));
		// System.out.println("CLIENTE ENCONTRADO");
		// System.out.println(cliente);
		 if (request.getId_dir() == null) throw new ErrorBadReq("Dirección requerida");
		// System.out.println("DIRECCIÓN: "+ idDireccion);
		Direccion direccion = direccionRepository.findById(request.getId_dir()).orElseThrow(()-> new ErrorNotFound("Dirección no encontrada"));

		Pedido pedido = crearPedidoBase(cliente, direccion);
		Pedido pedidoFinal = procesarDetallesYStock(pedido, request);

		notificationService.avisarCambioEstado(pedidoFinal.getCliente().getIdCliente(), pedidoFinal.getIdPedido(),
				pedidoFinal.getEstado());

		return mapToResponseDTO(pedidoFinal);
	}

	public PedidoResponseDTO getById(Integer id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(()-> new ErrorNotFound("Pedido no encontrado"));
		return mapToResponseDTO(pedido);
	}

	@Transactional
	public PedidoResponseDTO guardarPedidoLocal(PedidoRequest request) {
		boolean error = hayErrorStock(request);
		if (error) {
			throw new ErrorStock("No hay suficiente stock");
		}
		Integer idDireccion = request.getId_dir() != null ? request.getId_dir() : 1;
		System.out.println("DIRECCIÓN: "+ idDireccion);
		Direccion direccion = direccionRepository.findById(idDireccion).orElseThrow();
		
		Cliente clienteLocal = clienteRepository.findById(1).orElseGet(() -> {
			Cliente nuevo = new Cliente();
			nuevo.setNombre("Mostrador");
			nuevo.setEmail("local@pizza4you.com");
			return clienteRepository.save(nuevo);
		});

		Pedido pedido = crearPedidoBase(clienteLocal, direccion);
		Pedido pedidoFinal = procesarDetallesYStock(pedido, request);

		return mapToResponseDTO(pedidoFinal);
	}

	private Pedido crearPedidoBase(Cliente cliente, Direccion direccion) {
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDireccion(direccion);
		pedido.setFecha(LocalDateTime.now());
		pedido.setEstado(EstadoPedido.RECIBIDO);
		pedido.setPrecioTotal(BigDecimal.ZERO);
		
		return pedidoRepository.save(pedido);
	}

	private Pedido procesarDetallesYStock(Pedido pedidoGuardado, PedidoRequest request) {
		BigDecimal totalPedido = BigDecimal.ZERO;
		List<Ingrediente> ingredientesUsados = new ArrayList<>();

		for (DetallePedidoRequest detalleReq : request.getDetalles()) {
			Detalle_pedido detalle = new Detalle_pedido();
			detalle.setPedido(pedidoGuardado);

			Producto producto = productoRepository.findById(detalleReq.getIdProducto()).orElseThrow();
			detalle.setProducto(producto);
			detalle.setCantidad(detalleReq.getCantidad());

			BigDecimal precioUnitario = producto.getPrecioBase();

			if (detalleReq.getIdTamano() != null) {
				Tamano tamano = tamanoRepository.findById(detalleReq.getIdTamano()).orElse(null);
				detalle.setTamano(tamano);
				if (tamano != null && tamano.getPrecioExtra() != null) {
					precioUnitario = precioUnitario.add(tamano.getPrecioExtra());
				}
			}

			if (detalleReq.getIdMasa() != null) {
				Masa masa = masaRepository.findById(detalleReq.getIdMasa()).orElse(null);
				detalle.setMasa(masa);
				if (masa != null && masa.getPrecioExtra() != null) {
					precioUnitario = precioUnitario.add(masa.getPrecioExtra());
				}
			}

			BigDecimal costeExtrasUnitario = BigDecimal.ZERO;
			
			int contadorIngredientes = 0;
			int contadorSalsas = 0;
			
			if (detalleReq.getExtras() != null) {
				for (IngredienteExtraRequest extraReq : detalleReq.getExtras()) {
					Ingrediente ingrediente = ingredienteRepository.findById(extraReq.getIdIngrediente())
							.orElseThrow(() -> new ErrorNotFound("No se ha encontrado el ingrediente"));
					BigDecimal precioIngrediente = ingrediente.getPrecioExtra();
					BigDecimal cantidadExtra = new BigDecimal(extraReq.getCantidad());
					costeExtrasUnitario = costeExtrasUnitario.add(precioIngrediente.multiply(cantidadExtra));
				}
			}

			precioUnitario = precioUnitario.add(costeExtrasUnitario);
			BigDecimal cantidadDetalle = new BigDecimal(detalleReq.getCantidad());
			BigDecimal precioLinea = precioUnitario.multiply(cantidadDetalle);

			detalle.setPrecioCompra(precioLinea);
			Detalle_pedido detalleGuardado = detallePedidoRepository.save(detalle);

			List<Producto_ingrediente> ingredientesBase = productoIngredienteRepository
					.findByProductoIdProd(producto.getIdProd());
			for (Producto_ingrediente pi : ingredientesBase) {
				Ingrediente ing = pi.getIngrediente();
				int cantidadNecesaria = pi.getCantidad().intValue() * detalleReq.getCantidad();
				int nuevoStock = ing.getStock() - cantidadNecesaria;
				ing.setStock(nuevoStock);
				ingredienteRepository.save(ing);
				ingredientesUsados.add(ing);
			}

			if (detalleReq.getExtras() != null) {
				for (IngredienteExtraRequest extra : detalleReq.getExtras()) {
					

					if ("Extra".equalsIgnoreCase(extra.getTipo())) {
						contadorIngredientes += extra.getCantidad();
					} else if ("Salsa".equalsIgnoreCase(extra.getTipo())) {
						contadorSalsas += extra.getCantidad();
					}

					if (contadorSalsas > 10)
						throw new RuntimeException("Demasiadas salsas");
					if (contadorIngredientes > 5)
						throw new RuntimeException("Demasiados ingredientes");

					DetallePedidoIngrediente detalles_ingredientes = new DetallePedidoIngrediente();
					detalles_ingredientes.setDetallePedido(detalleGuardado);

					Ingrediente ingrediente = ingredienteRepository.findById(extra.getIdIngrediente()).orElseThrow();
					detalles_ingredientes.setIngrediente(ingrediente);
					detalles_ingredientes.setCantidad(extra.getCantidad());

					int cantidadExtraTotal = extra.getCantidad() * detalleReq.getCantidad();
					int nuevoStock = ingrediente.getStock() - cantidadExtraTotal;
					ingrediente.setStock(nuevoStock);
					ingredienteRepository.save(ingrediente);
					detalleIngredienteRepository.save(detalles_ingredientes);

					if (!ingredientesUsados.contains(ingrediente)) {
						ingredientesUsados.add(ingrediente);
					}
				}
			}

			totalPedido = totalPedido.add(precioLinea);
		}

		for (Ingrediente ing : ingredientesUsados) {
			if (ing.getStock() < UMBRAL_STOCK_BAJO) {
				//notificationService.avisarStockBajo(ing.getNombre(), ing.getStock());
			}
		}

		pedidoGuardado.setPrecioTotal(totalPedido);
		return pedidoRepository.save(pedidoGuardado);
	}

	private Boolean hayErrorStock (PedidoRequest request) {
	    if (request.getDetalles() == null || request.getDetalles().isEmpty()) {
	        return true;
	    }

	    Map<Integer, Integer> cantidadesIngredientes = new HashMap<>();

	    for (DetallePedidoRequest detalleReq : request.getDetalles()) {
	        Producto producto = productoRepository.findById(detalleReq.getIdProducto()).orElse(null);
	        if (producto == null || producto.isDescatalogado()) {
	            return true;
	        }

	        List<Producto_ingrediente> ingredientesBase = productoIngredienteRepository
	                .findByProductoIdProd(producto.getIdProd());
	        for (Producto_ingrediente pi : ingredientesBase) {
	            Ingrediente ing = pi.getIngrediente();
	            if (ing.getDescatalogado()) {
	                return true;
	            }

	            int idIngrediente = ing.getIdIng();
	            int cantidad = pi.getCantidad().intValue() * detalleReq.getCantidad();

	            if (cantidadesIngredientes.containsKey(idIngrediente)) {
	                cantidadesIngredientes.put(idIngrediente, cantidadesIngredientes.get(idIngrediente) + cantidad);
	            } else {
	                cantidadesIngredientes.put(idIngrediente, cantidad);
	            }
	        }

	        if (detalleReq.getExtras() != null) {
	            for (IngredienteExtraRequest extra : detalleReq.getExtras()) {
	                Ingrediente ing = ingredienteRepository.findById(extra.getIdIngrediente()).orElse(null);
	                if (ing == null || ing.getDescatalogado()) {
	                    return true;
	                }

	                int idIngrediente = ing.getIdIng();
	                int cantidadExtra = extra.getCantidad() * detalleReq.getCantidad();

	                if (cantidadesIngredientes.containsKey(idIngrediente)) {
	                    cantidadesIngredientes.put(idIngrediente, cantidadesIngredientes.get(idIngrediente) + cantidadExtra);
	                } else {
	                    cantidadesIngredientes.put(idIngrediente, cantidadExtra);
	                }
	            }
	        }
	    }

	    for (Integer idIngrediente : cantidadesIngredientes.keySet()) {
	        Ingrediente ing = ingredienteRepository.findById(idIngrediente).orElse(null);
	        int cantidadTotal = cantidadesIngredientes.get(idIngrediente);

	        if (ing == null || ing.getStock() < cantidadTotal) {
	            return true;
	        }
	    }

	    return false;
	}

	public PedidoResponseDTO actualizarEstado(int idPedido, EstadoPedido nuevoEstado) {
	    Pedido pedido = pedidoRepository.findById(idPedido).orElseThrow();
	    
	    switch (nuevoEstado) {
	        case PREPARANDO:
	            if (pedido.getCocinero() == null) {
	                var cocineros = empleadoRepository.findAvailableByRol("COCINERO");
	                if (!cocineros.isEmpty()) {
	                    Empleado cocinero = cocineros.get(0);
	                    pedido.setCocinero(cocinero);
	                    cocinero.setDisponible(false);
	                    empleadoRepository.save(cocinero);
	                }
	            }
	            break;

	        case ENCAMINO:
	            liberarCocinero(pedido);

	            if (pedido.getRepartidor() == null) {
	                var repartidores = empleadoRepository.findAvailableByRol("REPARTIDOR");
	                if (!repartidores.isEmpty()) {
	                    Empleado repartidor = repartidores.get(0);
	                    pedido.setRepartidor(repartidor);
	                    repartidor.setDisponible(false);
	                    empleadoRepository.save(repartidor);
	                }
	            }
	            break;

	        case ENTREGADO:
	            liberarCocinero(pedido);
	            liberarRepartidor(pedido);
	            break;

	        case CANCELADO:
	            liberarCocinero(pedido);
	            liberarRepartidor(pedido);
	            break;

	        default:
	            break;
	    }

	    pedido.setEstado(nuevoEstado);
	    pedidoRepository.save(pedido);

	    notificationService.avisarCambioEstado(pedido.getCliente().getIdCliente(), idPedido, nuevoEstado);

	    return mapToResponseDTO(pedido);
	}

	private void liberarCocinero(Pedido pedido) {
		System.out.println("se va a liberar cocinero");
	    if (pedido.getCocinero() != null) {
	        Empleado cocinero = pedido.getCocinero();
	        cocinero.setDisponible(true);
	        empleadoRepository.save(cocinero);
	        pedido.setCocinero(null);
	        System.out.println("se libera cocinero");
	    }
	}

	private void liberarRepartidor(Pedido pedido) {
		System.out.println("se va a liberar repartidor");
	    if (pedido.getRepartidor() != null) {
	        Empleado repartidor = pedido.getRepartidor();
	        repartidor.setDisponible(true);
	        empleadoRepository.save(repartidor);
	        pedido.setRepartidor(null);
	        System.out.println("se libera repartidor");
	    }
	}
	
	
	public  List<PedidoHistorial> getHistorial(int page, int size) {
		 Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fecha"));
	        List<EstadoPedido> estadosCompletados = List.of(EstadoPedido.ENTREGADO, EstadoPedido.CANCELADO);
	        Page<Pedido> pedidosPage = pedidoRepository.findByEstadoIn(estadosCompletados, pageable);
	        
	        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	        List<PedidoHistorial> pedidosDTO = mapToPedidoHistorial(pedidosPage.getContent(), formateador);
	        return pedidosDTO;
	}
	
	public List<PedidoHistorial> pedidosCocinero(int id){
		  List<Pedido> pedidos = pedidoRepository.findByCocineroIdEmpleadoOrderByFechaDesc(id);
		  
		  
	        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	        List<PedidoHistorial> pedidosDTO = mapToPedidoHistorial(pedidos, formateador);
	        return pedidosDTO;
	}	
	
	public List<PedidoHistorial> perUser(int id,int page, int size){
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fecha"));
        Page<Pedido> pedidosPage = pedidoRepository.findByClienteIdCliente(id, pageable);
        
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        List<PedidoHistorial> pedidosDTO = mapToPedidoHistorial(pedidosPage.getContent(), formateador);
        System.out.println(pedidosDTO);
        return pedidosDTO;
	}
	
	public List<PedidoHistorial> getAll(int page, int size){
		   Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fecha"));
	        Page<Pedido> pedidosPage = pedidoRepository.findAll(pageable);
	        
	        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	        List<PedidoHistorial> pedidosDTO = mapToPedidoHistorial(pedidosPage.getContent(), formateador);
	        return pedidosDTO;
	}
	public DireccionCompletaDTO obtenerDatosDomicilio(Integer idPedido) {
	    Pedido pedido = pedidoRepository.findById(idPedido)
	        .orElseThrow(() -> new ErrorNotFound("Pedido no encontrado"));

	    DireccionCompletaDTO dto = new DireccionCompletaDTO();

	    if (pedido.getCliente() != null) {
	        dto.setNombreCliente(pedido.getCliente().getNombre());
	        dto.setTelefono(pedido.getCliente().getTlf());
	    }

	    if (pedido.getDireccion() != null) {
	        Direccion dir = pedido.getDireccion();
	        dto.setNombreVia(dir.getNombreVia());
	        dto.setNumero(dir.getNumero());
	        dto.setBloque(dir.getBloque());
	        dto.setPuerta(dir.getPuerta());
	        dto.setPlanta(dir.getPlanta());
	        dto.setPortal(dir.getPortal());
	        dto.setDireccionCompleta(dir.getNombreVia() + " " + dir.getNumero());
	    }
	    System.out.println(dto);
	    return dto;
	}

	public PedidoCompletoDTO getPedidoCompleto(Integer idPedido) {
	    Pedido pedido = pedidoRepository.findById(idPedido)
	        .orElseThrow(() -> new ErrorNotFound("Pedido no encontrado"));

	    PedidoBaseDTO pedidoBase = new PedidoBaseDTO(
	        pedido.getIdPedido(),
	        pedido.getFecha(),
	        pedido.getEstado().getLabel(),
	        pedido.getPrecioTotal()
	    );

	    ClienteDTO clienteDTO = new ClienteDTO(
	        pedido.getCliente().getIdCliente(),
	        pedido.getCliente().getNombre(),
	        pedido.getCliente().getApe1(),
	        pedido.getCliente().getApe2(),
	        pedido.getCliente().getEmail(),
	        pedido.getCliente().getTlf()
	    );

	    DireccionDTO direccionDTO = new DireccionDTO(
	        pedido.getDireccion().getIdDir(),
	        pedido.getDireccion().getNombreVia(),
	        pedido.getDireccion().getNumero(),
	        pedido.getDireccion().getBloque(),
	        pedido.getDireccion().getPuerta(),
	        pedido.getDireccion().getPlanta(),
	        pedido.getDireccion().getPortal()
	    );

	    return new PedidoCompletoDTO(pedidoBase, clienteDTO, direccionDTO);
	}
	
	@Transactional(readOnly = true) // para evitar lazyInitializationException
	public PedidoYDetallesDTO getDetalles(int id) {
		Pedido pedido = pedidoRepository.findById(id).orElseThrow(()->new ErrorNotFound("Pedido no encontrado"));
		 
		// Creamos el nuevo objeto principal
        PedidoYDetallesDTO respuestaDTO = new PedidoYDetallesDTO();
        respuestaDTO.setIdPedido(pedido.getIdPedido());
        respuestaDTO.setEstado(pedido.getEstado().getLabel());
        
        // Mapeamos los detalles 
        List<DetallePedidoDTO> detallesDTO = pedido.getDetalles().stream().map(detalle -> {
            DetallePedidoDTO dto = new DetallePedidoDTO();
            dto.setNombreProducto(detalle.getProducto().getNombre());
            dto.setCantidad(detalle.getCantidad());
            dto.setPrecioCompra(detalle.getPrecioCompra());
            
            
            dto.setSubtotal(detalle.getPrecioCompra());
            
            if (detalle.getIngredientesExtra() != null && !detalle.getIngredientesExtra().isEmpty()) {
                // Entramos a la lista de la tabla intermedia y sacamos el nombre del ingrediente
                List<String> listaExtras = detalle.getIngredientesExtra().stream()
                    .map(extra -> extra.getCantidad() + "x " + extra.getIngrediente().getNombre())
                    .collect(Collectors.toList());
                dto.setExtras(listaExtras);
            } else {
                dto.setExtras(new ArrayList<>());
            }

           
            
            return dto;
        }).collect(Collectors.toList());
        
        // Metemos la lista de pizzas dentro del envoltorio
        respuestaDTO.setDetalles(detallesDTO);
        
        return respuestaDTO;
    
	}
	
	  private List<PedidoHistorial> mapToPedidoHistorial(List<Pedido> pedidos, DateTimeFormatter formateador) {
	        return pedidos.stream().map((Pedido pedido) -> {
	            PedidoHistorial dto = new PedidoHistorial();
	            dto.setIdPedido(pedido.getIdPedido());
	            dto.setFecha(pedido.getFecha().format(formateador));
	            dto.setEstado(pedido.getEstado().getLabel());
	            dto.setPrecioTotal(pedido.getPrecioTotal());
	            dto.setIdDireccion(pedido.getDireccion().getIdDir());
	            
	   
	            if (pedido.getCocinero() != null) {
	                dto.setCocineroId(pedido.getCocinero().getIdEmpleado());
	                dto.setCocineroNombre(pedido.getCocinero().getNombre());
	            }
	      
	            if (pedido.getRepartidor() != null) {
	                dto.setRepartidorId(pedido.getRepartidor().getIdEmpleado());
	                dto.setRepartidorNombre(pedido.getRepartidor().getNombre());
	            }
	        
	            if (pedido.getAtencion() != null) {
	                dto.setAtencionId(pedido.getAtencion().getIdEmpleado());
	                dto.setAtencionNombre(pedido.getAtencion().getNombre());
	            }
	            
	            return dto;
	        }).collect(Collectors.toList());
	    }

	public List<PedidoHistorial> getPerEmpleado(Integer idEmpleado, int page, int size) {
		 
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "fecha"));
        Page<Pedido> pedidosPage = pedidoRepository.findByCocineroIdEmpleadoOrRepartidorIdEmpleado(idEmpleado, idEmpleado, pageable);
        
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        List<PedidoHistorial> pedidosDTO = mapToPedidoHistorial(pedidosPage.getContent(), formateador);
        return pedidosDTO;
	}

	public List<PedidoHistorial> getActivos() {
		 
		List<EstadoPedido> estadosInactivos = List.of(EstadoPedido.ENTREGADO, EstadoPedido.CANCELADO);
	    List<Pedido> pedidos = pedidoRepository.findByEstadoNotIn(estadosInactivos);
	    
	    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	    List<PedidoHistorial> pedidosDTO = mapToPedidoHistorial(pedidos, formateador);
	    
	    return pedidosDTO;
	    }

	public List<PedidoHistorial> activosRepartidor(Integer idEmpleado) {

        List<Pedido> pedidos = pedidoRepository.findByRepartidorIdEmpleadoOrderByFechaDesc(idEmpleado);
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        List<PedidoHistorial> pedidosDTO = mapToPedidoHistorial(pedidos, formateador);
        return pedidosDTO;
	}

	private PedidoResponseDTO mapToResponseDTO(Pedido pedido) {
	    String direccionStr = "";
	    if (pedido.getDireccion() != null) {
	        direccionStr = pedido.getDireccion().getNombreVia() + " " + pedido.getDireccion().getNumero();
	    }
	    return new PedidoResponseDTO(
	        pedido.getIdPedido(),
	        pedido.getFecha(),
	        pedido.getEstado().getLabel(),
	        pedido.getPrecioTotal(),
	        pedido.getCliente() != null ? pedido.getCliente().getIdCliente() : null,
	        pedido.getCliente() != null ? pedido.getCliente().getNombre() : null,
	        pedido.getDireccion() != null ? pedido.getDireccion().getIdDir() : null,
	        direccionStr
	    );
	}
}
package com.proyecto_final.Pizza4You.controller;

import com.proyecto_final.Pizza4You.dto.DireccionCompletaDTO;
import com.proyecto_final.Pizza4You.dto.PedidoHistorial;
import com.proyecto_final.Pizza4You.dto.PedidoRequest;
import com.proyecto_final.Pizza4You.dto.PedidoResponseDTO;
import com.proyecto_final.Pizza4You.dto.PedidoYDetallesDTO;
import com.proyecto_final.Pizza4You.model.EstadoPedido;
import com.proyecto_final.Pizza4You.services.PaymentService;
import com.proyecto_final.Pizza4You.services.PedidoService;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@Autowired
    private PedidoService pedidoService;
	
	@Autowired
	private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> crearPedido(@RequestBody PedidoRequest pedidoRequest) {
    	try {
          

            paymentService.capturarPago(pedidoRequest.getPaymentIntentId());
            //System.out.println(pedidoRequest);
            return ResponseEntity.ok(this.pedidoService.guardarPedido(pedidoRequest));
    	} catch (Exception e) {
            System.err.println("ERROR AL GUARDAR: " + e.getMessage());
            e.printStackTrace(); 
            
            if (pedidoRequest.getPaymentIntentId() != null) {
                paymentService.cancelarPago(pedidoRequest.getPaymentIntentId());
            }
            
            return ResponseEntity.internalServerError().build();
        }
    	
    	

    }
    @PreAuthorize("hasAnyRole('COCINERO', 'REPARTIDOR', 'ATENCION')")
    @PostMapping("/local")
    public ResponseEntity<PedidoResponseDTO> crearPedidoLocal(@RequestBody PedidoRequest pedido) {
    
       return ResponseEntity.ok(this.pedidoService.guardarPedidoLocal(pedido));
    }
    
    @GetMapping("/usuario/{idCliente}")
    public ResponseEntity<List<PedidoHistorial>> obtenerPedidosPorUsuario(
            @PathVariable Integer idCliente,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "200") int size) {
    	
        
        return ResponseEntity.ok( this.pedidoService.perUser(idCliente,page, size));
    }
    
    @GetMapping
    public ResponseEntity<List<PedidoHistorial>> obtenerTodosPedidos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "200") int size) {
     
        return ResponseEntity.ok(this.pedidoService.getAll(page, size));
    }
    
    @GetMapping("/{idPedido}")
    public ResponseEntity<PedidoResponseDTO> findById(@PathVariable int idPedido){

    		return ResponseEntity.ok(this.pedidoService.getById(idPedido));
    }
    
    @GetMapping("/{idPedido}/detalles")
    public ResponseEntity<PedidoYDetallesDTO> obtenerDetallesDePedido(@PathVariable Integer idPedido) {
    
        return ResponseEntity.ok(this.pedidoService.getDetalles(idPedido));
    }
    
    @GetMapping("/{idPedido}/domicilio")
    public ResponseEntity<DireccionCompletaDTO> obtenerDatosDomicilio(@PathVariable Integer idPedido) {
        return ResponseEntity.ok(pedidoService.obtenerDatosDomicilio(idPedido));
    }
    
    @PutMapping("/{id}/estado")
    @PreAuthorize("hasRole('COCINERO', 'REPARTIDOR', 'ATENCION')")
    public ResponseEntity<PedidoResponseDTO> cambiarEstado(@PathVariable int id, @RequestBody Map<String, EstadoPedido> estadoMap) {

    	EstadoPedido estado = estadoMap.get("estado");

        return ResponseEntity.ok(this.pedidoService.actualizarEstado(id, estado));
    }
    /*
    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<PedidoHistorial>> obtenerPedidosPorEmpleado(
            @PathVariable Integer idEmpleado,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
       
    	
        
        return ResponseEntity.ok(this.pedidoService.getPerEmpleado(idEmpleado, page, size));
    } */
    
    @GetMapping("/activos")
    public ResponseEntity<List<PedidoHistorial>> obtenerPedidosActivos()
             {
       
        
        return ResponseEntity.ok(this.pedidoService.getActivos());
    }
    
    @GetMapping("/historial")
    public ResponseEntity<List<PedidoHistorial>> obtenerHistorialPedidos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "200") int size) {
        
   
        return ResponseEntity.ok(this.pedidoService.getHistorial(page,size));
    }
   /* 
    @GetMapping("/activos/cocinero/{idEmpleado}")
    public ResponseEntity<List<PedidoHistorial>> obtenerPedidosActivosCocinero(
            @PathVariable Integer idEmpleado) {
  
        return ResponseEntity.ok(this.pedidoService.pedidosCocinero(idEmpleado));
    }
    
    @GetMapping("/activos/repartidor/{idEmpleado}")
    public ResponseEntity<List<PedidoHistorial>> obtenerPedidosActivosRepartidor(
            @PathVariable Integer idEmpleado) {
        
        
        return ResponseEntity.ok(this.pedidoService.activosRepartidor(idEmpleado));
    } */
    
  
}
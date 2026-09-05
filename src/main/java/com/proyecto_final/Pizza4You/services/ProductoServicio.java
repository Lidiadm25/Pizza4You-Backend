package com.proyecto_final.Pizza4You.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;


import com.proyecto_final.Pizza4You.dto.OpcionesProductoRequest;
import com.proyecto_final.Pizza4You.model.Categoria;
import com.proyecto_final.Pizza4You.model.Masa;
import com.proyecto_final.Pizza4You.model.Producto;
import com.proyecto_final.Pizza4You.model.Producto_ingrediente;
import com.proyecto_final.Pizza4You.model.Tamano;
import com.proyecto_final.Pizza4You.repositorio.CategoriaRepository;
import com.proyecto_final.Pizza4You.repositorio.MasaRepository;
import com.proyecto_final.Pizza4You.repositorio.ProductoRepository;
import com.proyecto_final.Pizza4You.repositorio.TamanoRepository;

import com.proyecto_final.Pizza4You.error.*;
import jakarta.transaction.Transactional;

@Service
public class ProductoServicio {
	@Autowired
	private ProductoRepository productoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
    private TamanoRepository tamanoRepository;

    @Autowired
    private MasaRepository masaRepository;
	
  
	public Producto asignarOpciones(Integer idProducto, OpcionesProductoRequest request) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (request.getIdTamanos() != null) {
            List<Tamano> tamanos = tamanoRepository.findAllById(request.getIdTamanos());
            producto.setTamanos(tamanos);
        }

        if (request.getIdMasas() != null && !request.getIdMasas().isEmpty()) {
            boolean esPizza = producto.getCategoria().getNombre().equalsIgnoreCase("pizza");
            
            if (!esPizza) {
                throw new IllegalArgumentException("Las masas solo se pueden asignar a productos de la categoria pizza");
            }
            
            List<Masa> masas = masaRepository.findAllById(request.getIdMasas());
            producto.setMasas(masas);
        } else {
            producto.setMasas(new ArrayList<>());
        }

        return productoRepository.save(producto);
    }
	
	
	public List<Producto> findAll(){
		return this.productoRepository.findAll();
	}
	
	public Optional<Producto> findById(int id) {
	    Optional<Producto> producto = this.productoRepository.findByIdFiltrandoIngredientes(id);
	    producto.ifPresent(p -> {
	        List<Producto_ingrediente> filtrados = p.getProductoIngredientes().stream()
	            .filter(pi -> pi.getIngrediente() != null && !Boolean.TRUE.equals(pi.getIngrediente().getDescatalogado()))
	            .collect(Collectors.toList());
	        p.setProductoIngredientes(filtrados);
	    });
	    return producto;
	}
	
	public List<Producto> obtenerTop3ProductosDelMes() {
        LocalDateTime haceUnMes = LocalDateTime.now().minusMonths(1);
        Pageable topTres = PageRequest.of(0, 3);
        
        return productoRepository.findTopProductsSoldSince(haceUnMes, topTres);
    }
	
	public List<Producto> fidnByCategoria(Integer idCategoria) {
		System.out.println(idCategoria);
        return productoRepository.findByCategoria_IdCatAndActive(idCategoria);
    }
	
	public Producto create(Producto producto, OpcionesProductoRequest opciones)
	{
		
		if (opciones.getIdTamanos() != null && !opciones.getIdTamanos().isEmpty()) {
            List<Tamano> tamanos = tamanoRepository.findAllById(opciones.getIdTamanos());
            producto.setTamanos(tamanos);
        } else {
            producto.setTamanos(new ArrayList<>());
        }

        if (opciones.getIdMasas() != null && !opciones.getIdMasas().isEmpty()) {
            boolean esPizza = 
                              producto.getCategoria().getIdCat() ==1;

            if (esPizza) {
                List<Masa> masas = masaRepository.findAllById(opciones.getIdMasas());
                producto.setMasas(masas);
            } else {
                producto.setMasas(new ArrayList<>());
            }
        } else {
            producto.setMasas(new ArrayList<>());
        }
        
        if (producto.getCategoria() != null && producto.getCategoria().getIdCat() != 0) {
	        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getIdCat())
	                .orElse(null);
	        producto.setCategoria(categoria);
	    }
		
		
		return productoRepository.save(producto);
	}
	
	
	
	@Transactional
	public Producto update(int id, Producto nuevo, OpcionesProductoRequest opciones) {
	    Producto producto = productoRepository.findById(id)
	            .orElseThrow(() -> new ErrorNotFound("Producto no encontrado"));

	    producto.setNombre(nuevo.getNombre());
	    producto.setPrecioBase(nuevo.getPrecioBase());
	    producto.setDescripcion(nuevo.getDescripcion());
	    producto.setDescatalogado(nuevo.isDescatalogado());
	    if (nuevo.getImagen() != null && !nuevo.getImagen().isEmpty()) {
	        producto.setImagen(nuevo.getImagen());
	    }
	    if (opciones.getIdTamanos() != null && !opciones.getIdTamanos().isEmpty()) {
            List<Tamano> tamanos = tamanoRepository.findAllById(opciones.getIdTamanos());
            producto.setTamanos(tamanos);
        } else {
            producto.setTamanos(new ArrayList<>());
        }
	 

        if (opciones.getIdMasas() != null && !opciones.getIdMasas().isEmpty()) {
            boolean esPizza = producto.getCategoria() != null && 
                              producto.getCategoria().getNombre().equalsIgnoreCase("pizzas");

            if (esPizza) {
                List<Masa> masas = masaRepository.findAllById(opciones.getIdMasas());
                producto.setMasas(masas);
               
            } else {
                producto.setMasas(new ArrayList<>());
            }
        } else {
            producto.setMasas(new ArrayList<>());
        }

	    if (nuevo.getCategoria() != null && nuevo.getCategoria().getIdCat() != 0) {
	        Categoria categoria = categoriaRepository.findById(nuevo.getCategoria().getIdCat())
	                .orElse(null);
	        producto.setCategoria(categoria);
	    }
	    
	    return productoRepository.save(producto);
	}
	
	
	public void delete(int id) {
		this.productoRepository.deleteById(id);
	}
	
	public void descatalogar(int id) {
		Producto producto = productoRepository.findById(id)
	            .orElseThrow(() -> new ErrorNotFound("Producto no encontrado"));
		producto.setDescatalogado(true);
	
		 this.productoRepository.save(producto);
	}
	
	
}

package com.proyecto_final.Pizza4You.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.proyecto_final.Pizza4You.dto.IngredienteProductoRequest;
import com.proyecto_final.Pizza4You.model.Ingrediente;
import com.proyecto_final.Pizza4You.model.Producto;
import com.proyecto_final.Pizza4You.model.Producto_ingrediente;
import com.proyecto_final.Pizza4You.repositorio.IngredienteRepository;
import com.proyecto_final.Pizza4You.repositorio.ProductoIngredienteRepository;
import com.proyecto_final.Pizza4You.repositorio.ProductoRepository;
import com.proyecto_final.Pizza4You.error.*;

@Service
public class ProductoIngService {
	@Autowired
	private ProductoIngredienteRepository productoIngredienteRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private IngredienteRepository ingredienteRepository;

	public List<Producto_ingrediente> findIngredientes(int id) {
		List<Producto_ingrediente> ingredientes = productoIngredienteRepository.findByProductoIdProd(id);
		return ingredientes;

	}

	public Producto_ingrediente addIngrediente(int id, IngredienteProductoRequest request) {

		Producto producto = productoRepository.findById(id).orElse(null);
		Ingrediente ingrediente = ingredienteRepository.findById(request.getIdIngrediente()).orElse(null);

		if (producto == null || ingrediente == null) {
			throw new ErrorBadReq("Faltan datos");
		}

		// Verificar si ya existe
		Optional<Producto_ingrediente> existente = productoIngredienteRepository
				.findByProductoIdProdAndIngredienteIdIng(id, request.getIdIngrediente());
		if (existente.isPresent()) {
			existente.get().setCantidad(request.getCantidad());
			return productoIngredienteRepository.save(existente.get());
		}

		Producto_ingrediente pi = new Producto_ingrediente();
		pi.setProducto(producto);
		pi.setIngrediente(ingrediente);
		pi.setCantidad(request.getCantidad());
		
		this.productoIngredienteRepository.save(pi);
			
		return pi;
	}

	public Producto_ingrediente updateCantidad(int id, IngredienteProductoRequest request) {

        var existente = productoIngredienteRepository.findByProductoIdProdAndIngredienteIdIng(id, request.getIdIngrediente());
        if (existente.isEmpty()) {
        	throw new ErrorNotFound("No existe la combinación de producto - ingrediente");
        }
        
        existente.get().setCantidad(request.getCantidad());
        
        this.productoIngredienteRepository.save(existente.get());
        return existente.get();
	}
	
	public void deleteIngrediente(int idProd, int idIng) {
		 
        var existente = productoIngredienteRepository.findByProductoIdProdAndIngredienteIdIng(idProd, idIng);
        if (existente.isEmpty()) {
        	throw new ErrorNotFound("No existe la combinación de producto - ingrediente");
        }
        
        productoIngredienteRepository.delete(existente.get());
	}
	
	public void deleteAll(int id) {
		productoIngredienteRepository.deleteByProductoIdProd(id);
	}
	public void deleteVarious(List<Integer> ids, int id) {
		productoIngredienteRepository.eliminarIngredientesDeProducto(id,ids);
	}
	
}

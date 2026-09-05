package com.proyecto_final.Pizza4You.services;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.proyecto_final.Pizza4You.model.Ingrediente;
import com.proyecto_final.Pizza4You.repositorio.IngredienteRepository;

import com.proyecto_final.Pizza4You.error.*;

@Service
public class IngredientsService {
	@Autowired
	private IngredienteRepository ingredienteRepository;

	public List<Ingrediente> findAll() {
		return this.ingredienteRepository.findAll();
	}
	
	public Ingrediente findById(int id) {
		return this.ingredienteRepository.findById(id).orElseThrow(()-> new ErrorNotFound("Ingrediente no encontrado"));
	}
	
	public Ingrediente create(Ingrediente ing) {
		return this.ingredienteRepository.save(ing);
	}

	
	
	
	public Ingrediente update(int id, Ingrediente ing) {
		
		Ingrediente updated =	this.ingredienteRepository.findById(id).orElseThrow(()-> new ErrorNotFound("Ingrediente no encontrado"));
		
		
		 updated.setNombre(ing.getNombre());
         updated.setStock(ing.getStock());
         updated.setUnidadMedida(ing.getUnidadMedida());
         updated.setEsVegano(ing.getEsVegano());
         updated.setAlergenos(ing.getAlergenos());
      
         
         if(ing.getImagen() != null) {
        	 updated.setImagen(ing.getImagen());
         }
         
         updated.setTipo(ing.getTipo());
         updated.setPrecioExtra(ing.getPrecioExtra());
         	
         System.out.println(updated);
         return this.ingredienteRepository.save(updated);
		
	}
	
	public void delete(int id) {
		Ingrediente ing = this.ingredienteRepository.findById(id).orElseThrow(()-> new ErrorNotFound("Ingrediente no encontrado"));
		ing.setDescatalogado(!ing.getDescatalogado());
	
		this.ingredienteRepository.save(ing);
		// this.ingredienteRepository.delete(ing);
	}
	
	public Ingrediente findOne(int id) {
		Ingrediente ing = this.ingredienteRepository.findById(id).orElseThrow(()-> new ErrorNotFound("Ingrediente no encontrado"));
		return ing;
	}
	
	public List<Ingrediente> findDisponible(){
		return this.ingredienteRepository.findByDescatalogadoFalse();
	}

}

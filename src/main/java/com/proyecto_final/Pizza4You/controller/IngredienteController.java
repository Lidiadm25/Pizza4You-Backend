package com.proyecto_final.Pizza4You.controller;

import com.proyecto_final.Pizza4You.model.Ingrediente;

import com.proyecto_final.Pizza4You.services.FileStorageService;
import com.proyecto_final.Pizza4You.services.IngredientsService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

// Consumes multipart/form-data para recogida de imágenes

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {
	
  @Autowired
  IngredientsService ingredientsService;
  @Autowired FileStorageService fileStorageService;
    @GetMapping
    public List<Ingrediente> findAll() {
        return this.ingredientsService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> findById(@PathVariable int id) {
       return ResponseEntity.ok(this.ingredientsService.findById(id));
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Ingrediente> crear(
            @RequestPart("ingrediente") Ingrediente ingrediente,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws IOException {

    	if (imagen != null && !imagen.isEmpty()) {
            String nombreImagenGenerado = fileStorageService.guardarImagen(imagen);
            ingrediente.setImagen(nombreImagenGenerado);
        }
        
        return ResponseEntity.ok(ingredientsService.create(ingrediente));
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<Ingrediente> update(
            @PathVariable Integer id,
            @RequestPart("ingrediente") Ingrediente ingredienteActualizado,
            @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws IOException {

    	if (imagen != null && !imagen.isEmpty()) {
            String nombreImagenGenerado = fileStorageService.guardarImagen(imagen);
            ingredienteActualizado.setImagen(nombreImagenGenerado);
        }
        	
       
        
        return ResponseEntity.ok(ingredientsService.update(id, ingredienteActualizado));
    }

    @PatchMapping("/descatalogar")
    public ResponseEntity<Void> delete(@RequestBody Integer id) {
    
    	
    			this.ingredientsService.delete(id);
            return ResponseEntity.ok().build();
       
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Ingrediente>>findDisponibles() {
    	List<Ingrediente> ings = this.ingredientsService.findDisponible();
    	
    	return  ings.isEmpty() ?  ResponseEntity.notFound().build() :  ResponseEntity.ok(ings);
    }
}
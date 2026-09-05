package com.proyecto_final.Pizza4You.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_final.Pizza4You.model.Categoria;
import com.proyecto_final.Pizza4You.services.CategoriaService;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
	
	@Autowired
	CategoriaService categoriaService;
	
	
    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerCategorias() {
        List<Categoria> categorias = this.categoriaService.findAll();
    	if(categorias.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
        return ResponseEntity.ok(categorias);
    }
}
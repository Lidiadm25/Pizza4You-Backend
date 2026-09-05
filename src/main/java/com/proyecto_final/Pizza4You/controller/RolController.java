package com.proyecto_final.Pizza4You.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_final.Pizza4You.model.Rol;
import com.proyecto_final.Pizza4You.services.RolService;

@RestController
@RequestMapping("/api/roles")
public class RolController {
	
	@Autowired
	private RolService rolService;
	
	@GetMapping()
	public ResponseEntity<List<Rol>> getRoles() {
		return ResponseEntity.ok(this.rolService.getAll());
	}
}

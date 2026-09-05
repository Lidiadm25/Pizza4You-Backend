package com.proyecto_final.Pizza4You.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_final.Pizza4You.dto.AuthResponse;
import com.proyecto_final.Pizza4You.dto.FullProfileRequest;

import com.proyecto_final.Pizza4You.dto.LoginRequest;
import com.proyecto_final.Pizza4You.dto.RegisterRequest;
import com.proyecto_final.Pizza4You.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(this.authService.login(request));
	}

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> registrarCliente(@RequestBody RegisterRequest request) {

		return ResponseEntity.ok(this.authService.register(request));
	}

	@PutMapping("/perfil")
	public ResponseEntity<FullProfileRequest> actualizarPerfil(@RequestBody FullProfileRequest request) {
		// Decode del token
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailUsuario = auth.getName();

		return ResponseEntity.ok(this.authService.update(request, emailUsuario));
	}
	
	/*
	@GetMapping("{id}")
	public ResponseEntity<FullProfileResponse> datosPerfil(@PathVariable int id){
		//this.authService.getDatos( id)
		return ResponseEntity.ok().build();
	} */

}
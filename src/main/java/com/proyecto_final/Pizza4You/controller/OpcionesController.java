package com.proyecto_final.Pizza4You.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto_final.Pizza4You.model.Masa;
import com.proyecto_final.Pizza4You.model.Tamano;
import com.proyecto_final.Pizza4You.services.MasaService;
import com.proyecto_final.Pizza4You.services.TamanoService;

@RestController
public class OpcionesController {
	
	@Autowired
    private TamanoService tamanoService;
    
    @Autowired
    private MasaService masaService;

    @GetMapping("/tamanos")
    public List<Tamano> listarTamanos() {
        return tamanoService.findAll();
    }

    @GetMapping("/masas")
    public List<Masa> listarMasas() {
    	
        return masaService.findAll();
    }
    
    @PostMapping("/tamano")
    @PreAuthorize("hasRole('COCINERO', 'REPARTIDOR', 'ATENCION')")
    public ResponseEntity<Tamano> crearTamano(@RequestBody Tamano tamano) {
        return ResponseEntity.ok(tamanoService.guardar(tamano));
    }
    
    @PostMapping("/masa")
    @PreAuthorize("hasRole('COCINERO', 'REPARTIDOR', 'ATENCION')")
    public ResponseEntity<Masa> crearMasa(@RequestBody Masa masa) {
        return ResponseEntity.ok(this.masaService.guardar(masa));
    }
}
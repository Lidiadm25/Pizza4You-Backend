package com.proyecto_final.Pizza4You.controller;

import com.proyecto_final.Pizza4You.dto.DireccionDTO;
import com.proyecto_final.Pizza4You.model.Cliente;
import com.proyecto_final.Pizza4You.model.Direccion;
import com.proyecto_final.Pizza4You.repositorio.ClienteRepository;
import com.proyecto_final.Pizza4You.repositorio.DireccionRepository;
import com.proyecto_final.Pizza4You.services.DireccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/direcciones")

public class DireccionController {

    @Autowired
    private DireccionRepository direccionRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DireccionService direccionService;
    
    
    
    @GetMapping
    public ResponseEntity<List<DireccionDTO>> obtenerMisDirecciones() {
    	// decode to get the email
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = authentication.getName();

        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(emailUsuario);

        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(404).build();
        }
        
        List<DireccionDTO> direcciones = direccionService.buscarDirecciones(clienteOpt.get().getIdCliente());
        return ResponseEntity.ok(direcciones);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarDireccion(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = authentication.getName();

        Optional<Cliente> clienteOpt = clienteRepository.findByEmail(emailUsuario);
        Optional<Direccion> direccionOpt = direccionRepository.findById(id);

        if (clienteOpt.isEmpty() || direccionOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (!direccionOpt.get().getCliente().getIdCliente().equals(clienteOpt.get().getIdCliente())) {
            return ResponseEntity.status(403).build();
        }

        Direccion direccion = direccionOpt.get();
        direccion.setActivo(false);
        direccionRepository.save(direccion);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<DireccionDTO> anyadirDireccion(@RequestBody Direccion request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = authentication.getName();
        return ResponseEntity.ok(direccionService.anyadirDireccion(request, emailUsuario));
    }
}
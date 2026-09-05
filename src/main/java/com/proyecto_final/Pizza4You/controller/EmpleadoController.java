package com.proyecto_final.Pizza4You.controller;

import com.proyecto_final.Pizza4You.dto.EmpleadoDTO;
import com.proyecto_final.Pizza4You.dto.EmpleadoRequest;
import com.proyecto_final.Pizza4You.dto.EmpleadoResponse;

import com.proyecto_final.Pizza4You.services.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

	@Autowired
	EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoDTO>> findAll() {
    	List<EmpleadoDTO> empleados = this.empleadoService.findAll();
    	if(empleados.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empleados);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<EmpleadoDTO>> findDisponibles() {
    	List<EmpleadoDTO> empleados = this.empleadoService.findDisponibles();

    	if(empleados.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(empleados) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> findById(@PathVariable int id) {

        return ResponseEntity.ok(this.empleadoService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<EmpleadoResponse> create(@RequestBody EmpleadoRequest empleado) {
    	System.out.println(empleado);
        return ResponseEntity.ok(this.empleadoService.create(empleado));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<EmpleadoDTO> update(@PathVariable Integer id, @RequestBody EmpleadoRequest empleadoActualizado) {

      return ResponseEntity.ok(this.empleadoService.update(id, empleadoActualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
    		this.empleadoService.delete(id);
            return ResponseEntity.ok().build();


    }

    @PutMapping("/{id}/disponible")
    public ResponseEntity<EmpleadoDTO> toggleDisponibilidad(@PathVariable int id, @RequestBody Map<String, Boolean> request) {
    	Boolean primerValor = request.values().stream().findFirst().orElse(null);
        return ResponseEntity.ok(this.empleadoService.toggleDisponibilidad(id, primerValor));
    }


    @GetMapping("/rol/{rol}")
    public ResponseEntity<List<EmpleadoDTO>> findByRol(@PathVariable String rol) {
    	List<EmpleadoDTO> empleados = this.empleadoService.findByRol(rol);
    	if(empleados.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(empleados);
    }
}
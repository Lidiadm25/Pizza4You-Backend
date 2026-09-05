package com.proyecto_final.Pizza4You.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.proyecto_final.Pizza4You.dto.EmpleadoDTO;
import com.proyecto_final.Pizza4You.dto.EmpleadoRequest;
import com.proyecto_final.Pizza4You.dto.EmpleadoResponse;

import com.proyecto_final.Pizza4You.model.Empleado;
import com.proyecto_final.Pizza4You.model.Rol;
import com.proyecto_final.Pizza4You.repositorio.EmpleadoRepository;
import com.proyecto_final.Pizza4You.security.JwtService;

import com.proyecto_final.Pizza4You.error.*;

@Service
public class EmpleadoService {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private PasswordEncoder passwordEncoder;

    @Autowired
    private EmpleadoRepository empleadoRepository;
    
    
    public List<EmpleadoDTO> findAll(){
    	return this.empleadoRepository.findAll().stream()
    		.map(this::mapToDTO)
    		.collect(Collectors.toList());
    }

    public EmpleadoDTO findById(int id) {
    	Empleado emp = this.empleadoRepository.findById(id).orElseThrow(()-> new ErrorNotFound("Empleado no encontrado"));
    	return mapToDTO(emp);
    }

    public List<EmpleadoDTO> findDisponibles(){
    	return this.empleadoRepository.findAllAvailable().stream()
    		.map(this::mapToDTO)
    		.collect(Collectors.toList());
    }
    
    public EmpleadoResponse create(EmpleadoRequest empleado) {
        if (empleadoRepository.findByEmail(empleado.getEmail()).isPresent()) {
            throw new ErrorBadReq("El email ya está en uso");
        }
        
        Empleado nuevoEmpleado = new Empleado();
        nuevoEmpleado.setEmail(empleado.getEmail());
        String passEncriptada = passwordEncoder.encode(empleado.getPass());
        nuevoEmpleado.setPass(passEncriptada);
        nuevoEmpleado.setDisponible(false);
        nuevoEmpleado.setDni(empleado.getDni());
        nuevoEmpleado.setSsn(empleado.getSsn());
        nuevoEmpleado.setNombre(empleado.getNombre());
        nuevoEmpleado.setFechaAlta(empleado.getFechaAlta());
        nuevoEmpleado.setSalario(empleado.getSalario());
        nuevoEmpleado.setRoles(empleado.getRoles());

        empleadoRepository.save(nuevoEmpleado);
            
        String token = jwtService.generateToken(nuevoEmpleado.getEmail(), nuevoEmpleado.getRoles());
        return new EmpleadoResponse(token, nuevoEmpleado);
    }
    
    
    public EmpleadoDTO update(Integer id, EmpleadoRequest empleado) {
        Empleado e = this.empleadoRepository.findById(id)
                .orElseThrow(() -> new ErrorNotFound("Empleado no encontrado"));
    //    System.out.println(e);
      //  System.out.println(empleado);
        if (empleado.getNombre() != null && !empleado.getNombre().isBlank()) {
            e.setNombre(empleado.getNombre());
        }
        
        if (empleado.getEmail() != null && !empleado.getEmail().isBlank()) {
            e.setEmail(empleado.getEmail());
        }
        
        if (empleado.getPass() != null && !empleado.getPass().isBlank()) {
            e.setPass(passwordEncoder.encode(empleado.getPass()));
        }
        
        if (empleado.getSalario() != null) {
            e.setSalario(empleado.getSalario());
        }
        
        if (empleado.getDni() != null && !empleado.getDni().isBlank()) {
            e.setDni(empleado.getDni());
        }
        
        if (empleado.getSsn() != null && !empleado.getSsn().isBlank()) {
            e.setSsn(empleado.getSsn());
        }
        
        if (empleado.getFechaAlta() != null ) {
            e.setFechaAlta(empleado.getFechaAlta());
        }
        
        if (empleado.getFechaBaja() != null) {
            e.setFechaBaja(empleado.getFechaBaja());
        }
        
        if (empleado.getDisponible() != null) {
            e.setDisponible(empleado.getDisponible());
        }
        
        if (empleado.getRoles() != null) {
            e.setRoles(empleado.getRoles());
        }
      //  System.out.println(e);
        empleadoRepository.save(e);
        return mapToDTO(e);
    }
    
    public void delete(int id) {
    	if(this.empleadoRepository.existsById(id)) {
    		this.empleadoRepository.deleteById(id);
    	} else {
    		throw new ErrorNotFound("Empleado no encontrado");
    	}
    }
    
    public EmpleadoDTO toggleDisponibilidad(int id, boolean estado) {
    	Empleado emp = this.empleadoRepository.findById(id).orElseThrow(()-> new ErrorNotFound("Empleado no encontrado"));
    	if(emp.getDisponible() != null) {
    		emp.setDisponible(estado);
    	}

    	return mapToDTO(empleadoRepository.save(emp));
    }

    public List<EmpleadoDTO> findByRol(String rol){
    	return this.empleadoRepository.findAvailableByRol(rol).stream()
    		.map(this::mapToDTO)
    		.collect(Collectors.toList());
    }

    private EmpleadoDTO mapToDTO(Empleado emp) {
    	List<Rol> rol = new ArrayList<Rol>();
    	if (emp.getRoles() != null && !emp.getRoles().isEmpty()) {
    		rol = emp.getRoles();
    	}
    	return new EmpleadoDTO(
    		emp.getIdEmpleado(),
    		emp.getNombre(),
    		emp.getEmail(),
    		rol,
    		emp.getDisponible(),
    		emp.getDni()
    	);
    }
    
 
  }

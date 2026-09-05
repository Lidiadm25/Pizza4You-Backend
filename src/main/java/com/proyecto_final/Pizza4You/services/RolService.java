package com.proyecto_final.Pizza4You.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.Pizza4You.model.Rol;
import com.proyecto_final.Pizza4You.repositorio.RolRepository;

@Service
public class RolService {
	
	@Autowired
	private RolRepository rolRepository;
	
	public List<Rol> getAll() {
		return this.rolRepository.findAll();
	}
}

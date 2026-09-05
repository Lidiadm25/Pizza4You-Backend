package com.proyecto_final.Pizza4You.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.Pizza4You.model.Tamano;
import com.proyecto_final.Pizza4You.repositorio.TamanoRepository;

@Service
public class TamanoService {

	@Autowired
	private TamanoRepository tamanoRepository;
	
	public List<Tamano> findAll(){
		return tamanoRepository.findAll();
	}
	
	public Tamano guardar(Tamano tamano) {
		return this.tamanoRepository.save(tamano);
	}
}

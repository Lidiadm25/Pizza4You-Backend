package com.proyecto_final.Pizza4You.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.Pizza4You.model.Masa;
import com.proyecto_final.Pizza4You.repositorio.MasaRepository;

@Service
public class MasaService {
	
	@Autowired
	private MasaRepository masaRepository;
	
	public List<Masa> findAll(){
		return this.masaRepository.findAll();
	}
	
	public Masa guardar(Masa masa) {
		return this.masaRepository.save(masa);
	}
	
}

package com.proyecto_final.Pizza4You.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto_final.Pizza4You.model.Categoria;
import com.proyecto_final.Pizza4You.repositorio.CategoriaRepository;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    public List<Categoria> findAll(){
    	return categoriaRepository.findAll();
    }
}

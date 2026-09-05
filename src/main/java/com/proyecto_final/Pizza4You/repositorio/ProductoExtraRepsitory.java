package com.proyecto_final.Pizza4You.repositorio;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.Pizza4You.model.Producto;



@Repository
public interface ProductoExtraRepsitory extends JpaRepository<Producto, Integer> {
    
   
}


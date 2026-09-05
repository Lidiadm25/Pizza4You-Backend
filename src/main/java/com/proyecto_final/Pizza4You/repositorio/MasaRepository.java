package com.proyecto_final.Pizza4You.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.Pizza4You.model.Masa;



@Repository
public interface MasaRepository extends JpaRepository<Masa, Integer> {

}

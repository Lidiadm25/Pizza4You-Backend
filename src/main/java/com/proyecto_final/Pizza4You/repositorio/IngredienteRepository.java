package com.proyecto_final.Pizza4You.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.Pizza4You.model.Ingrediente;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    
    List<Ingrediente> findByDescatalogadoFalse();
    
    List<Ingrediente> findByTipo(String tipo);
    
    List<Ingrediente> findByStockLessThan(int stock);
}

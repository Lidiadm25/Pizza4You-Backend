package com.proyecto_final.Pizza4You.repositorio;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.Pizza4You.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
	Optional<Cliente> findByEmail(String email);
}


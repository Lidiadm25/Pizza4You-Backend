package com.proyecto_final.Pizza4You.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto_final.Pizza4You.model.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    
    Optional<Empleado> findByEmail(String email);
    
    @Query("SELECT e FROM Empleado e JOIN e.roles r WHERE r.nombreCargo = :rol AND e.disponible = true AND e.fechaBaja IS NULL")
    List<Empleado> findAvailableByRol(String rol);
    
    @Query("SELECT e FROM Empleado e WHERE e.disponible = true AND e.fechaBaja IS NULL")
    List<Empleado> findAllAvailable();
    
    Optional<Empleado> findByDni(String dni);
}


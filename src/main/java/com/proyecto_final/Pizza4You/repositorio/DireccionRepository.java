package com.proyecto_final.Pizza4You.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.proyecto_final.Pizza4You.model.Cliente;
import com.proyecto_final.Pizza4You.model.Direccion;



public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
    
	@Query("SELECT d FROM Direccion d "
			+ "WHERE d.cliente = :cliente AND d.nombreVia = :nombreVia "
			+ "AND d.numero = :numero AND d.bloque = :bloque AND d.puerta = :puerta "
			+ "AND d.planta = :planta AND d.portal = :portal")
    Optional<Direccion> existeDireccion(
        @Param("cliente") Cliente cliente,
        @Param("nombreVia") String nombreVia,
        @Param("numero") Integer numero,
        @Param("bloque") String bloque,
        @Param("puerta") String puerta,
        @Param("planta") String planta,
        @Param("portal") String portal
    );
	
	List<Direccion> findByClienteAndActivoTrue(Cliente cliente);
}
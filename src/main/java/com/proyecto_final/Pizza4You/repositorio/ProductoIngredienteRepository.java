package com.proyecto_final.Pizza4You.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto_final.Pizza4You.model.Producto_ingrediente;

import jakarta.transaction.Transactional;

import com.proyecto_final.Pizza4You.model.ProductoIngredienteId;

@Repository
public interface ProductoIngredienteRepository extends JpaRepository<Producto_ingrediente, ProductoIngredienteId> {
    
    List<Producto_ingrediente> findByProductoIdProd(int idProd);
    
    Optional<Producto_ingrediente> findByProductoIdProdAndIngredienteIdIng(int idProd, int idIng);
    
    void deleteByProductoIdProd(int idProd);
    
    @Modifying
    @Transactional
    @Query("DELETE FROM Producto_ingrediente pi WHERE pi.producto.idProd = :idProducto AND pi.ingrediente.idIng IN :idsIngredientes")
    void eliminarIngredientesDeProducto(@Param("idProducto") Integer idProducto, @Param("idsIngredientes") List<Integer> idsIngredientes);
}


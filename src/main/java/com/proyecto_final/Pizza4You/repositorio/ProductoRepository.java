package com.proyecto_final.Pizza4You.repositorio;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.proyecto_final.Pizza4You.model.Producto;



@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    
    List<Producto> findByCategoria_Nombre(String nombreCat);
    @Query("SELECT p FROM Producto p " +
    	
            "WHERE p.categoria.idCat = :id AND descatalogado=false " +
            "GROUP BY p ")
           
    List<Producto> findByCategoria_IdCatAndActive(@Param("id") Integer idCat);
    
    @Query("SELECT p FROM Producto p " +
            "JOIN Detalle_pedido d ON d.producto = p " +
            "JOIN d.pedido ped " +
            "WHERE ped.fecha >= :fechaInicio AND p.categoria.idCat <> 2 AND descatalogado=false " +
            "GROUP BY p " +
            "ORDER BY SUM(d.cantidad) DESC")
     List<Producto> findTopProductsSoldSince(
             @Param("fechaInicio") LocalDateTime fechaInicio, 
             Pageable pageable
     );
    @Query("SELECT DISTINCT p FROM Producto p " +
    	       "LEFT JOIN FETCH p.productoIngredientes pi " +
    	       "WHERE p.id = :id")
    	Optional<Producto> findByIdFiltrandoIngredientes(@Param("id") Integer id);
}


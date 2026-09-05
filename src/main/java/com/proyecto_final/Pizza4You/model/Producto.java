package com.proyecto_final.Pizza4You.model;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProd;

    @Column(nullable = false, length = 100)
    private String nombre;

    private BigDecimal precioBase;
    private String imagen;
    
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    private boolean descatalogado;

    @ManyToOne
    @JoinColumn(name = "id_cat", nullable = false)
    private Categoria categoria;

    @OneToMany
    (mappedBy = "producto")
    private List<Producto_ingrediente> productoIngredientes;
    
    @ManyToMany
    @JoinTable(
        name = "productos_tamanos",
        joinColumns = @JoinColumn(name = "id_prod"),
        inverseJoinColumns = @JoinColumn(name = "id_tamano")
    )
    private List<Tamano> tamanos;

    @ManyToMany
    @JoinTable(
        name = "productos_masas",
        joinColumns = @JoinColumn(name = "id_prod"),
        inverseJoinColumns = @JoinColumn(name = "id_masa")
    )
    private List<Masa> masas;
}
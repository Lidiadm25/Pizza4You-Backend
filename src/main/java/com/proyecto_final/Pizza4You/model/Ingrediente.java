package com.proyecto_final.Pizza4You.model;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ingredientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIng;

    @Column(nullable = false, unique = true, length = 100)
    private String nombre;

    private int stock;
    @Column(length = 20)
    private String unidadMedida;
    private Boolean esVegano;
    @Column(length = 255)
    private String alergenos;
    private Boolean descatalogado;
    
    private String imagen;
    private String tipo;
    @Column(name = "precio_extra")
    private BigDecimal precioExtra;
}

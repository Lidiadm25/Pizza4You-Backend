package com.proyecto_final.Pizza4You.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "masas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Masa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_masa")
    private Integer idMasa;

    private String nombre;

    @Column(name = "precio_extra")
    private BigDecimal precioExtra;


    
}
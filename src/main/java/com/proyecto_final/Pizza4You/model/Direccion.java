package com.proyecto_final.Pizza4You.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name= "direcciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "cliente") 
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDir;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
    @Column(nullable = false, length = 100)
    private String nombreVia;
    
    @Column(length = 10)
    private Integer numero;
    @Column(length = 10)
    private String bloque;
    @Column(length = 10)
    private String puerta;
    @Column(length = 10)
    private String planta;
    @Column(length = 10)
    private String portal;
    
    @Column(name = "activo", columnDefinition = "boolean default true")
    private Boolean activo = true;

}

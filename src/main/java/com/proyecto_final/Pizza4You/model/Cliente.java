package com.proyecto_final.Pizza4You.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCliente;

    @Column(nullable = true, length = 50)
    private String nombre;
    
    @Column(nullable = true, length = 50)
    private String ape1;
    
    @Column(length = 50)
    private String ape2;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 255)
    private String pass;
    
    @Column(nullable = true, length = 20)
    private String tlf;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnoreProperties("cliente")
    private List<Direccion> direcciones;
}
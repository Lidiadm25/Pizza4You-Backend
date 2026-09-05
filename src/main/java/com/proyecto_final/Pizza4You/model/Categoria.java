package com.proyecto_final.Pizza4You.model;

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
@Table(name = "categorias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int idCat;

	    @Column(nullable = false, length = 50)
	    private String nombre;
	    
	    @Column(nullable = false)
	    private String imagen;
}

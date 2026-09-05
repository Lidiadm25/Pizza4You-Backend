package com.proyecto_final.Pizza4You.dto;

import java.util.List;

import lombok.Data;

@Data
public class OpcionesProductoRequest {
	    private List<Integer> idTamanos;
	    private List<Integer> idMasas;
	
}

package com.proyecto_final.Pizza4You.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
	private String token;
    private int idCliente;
    private int idDireccion;
}
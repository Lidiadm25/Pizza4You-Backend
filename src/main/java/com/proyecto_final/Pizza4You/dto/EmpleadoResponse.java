package com.proyecto_final.Pizza4You.dto;

import com.proyecto_final.Pizza4You.model.Empleado;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmpleadoResponse {
	private String token;
	private Empleado empleado;
}

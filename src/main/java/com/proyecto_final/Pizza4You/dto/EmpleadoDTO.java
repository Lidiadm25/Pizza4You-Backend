package com.proyecto_final.Pizza4You.dto;

import java.util.List;

import com.proyecto_final.Pizza4You.model.Rol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO {
	private Integer idEmpleado;
    private String nombre;
    private String email;
    private List<Rol> roles;
    private Boolean disponible;
    private String dni;
}
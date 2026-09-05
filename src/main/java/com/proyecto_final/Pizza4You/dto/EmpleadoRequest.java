package com.proyecto_final.Pizza4You.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

import com.proyecto_final.Pizza4You.model.Rol;
@Data
public class EmpleadoRequest {
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String pass;

    private Double salario;

    @NotBlank(message = "El DNI es obligatorio")
    @Size(max = 20, message = "El DNI no puede exceder 20 caracteres")
    private String dni;

    @Size(max = 50, message = "El SSN no puede exceder 50 caracteres")
    private String ssn;

    private LocalDate fechaAlta;
    private LocalDate fechaBaja;
    private Boolean disponible = true;
    
    private List<Rol> roles;

}
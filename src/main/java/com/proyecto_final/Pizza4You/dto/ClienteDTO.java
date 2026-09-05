package com.proyecto_final.Pizza4You.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Integer idCliente;
    private String nombre;
    private String ape1;
    private String ape2;
    private String email;
    private String tlf;
}
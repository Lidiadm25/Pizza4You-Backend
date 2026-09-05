package com.proyecto_final.Pizza4You.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DireccionCompletaDTO {
    private String nombreCliente;
    private String telefono;
    private String nombreVia;
    private Integer numero;
    private String bloque;
    private String puerta;
    private String planta;
    private String portal;
    private String direccionCompleta;
}
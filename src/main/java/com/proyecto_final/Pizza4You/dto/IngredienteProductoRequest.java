package com.proyecto_final.Pizza4You.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredienteProductoRequest {
    
    private Integer idIngrediente;
    private BigDecimal cantidad;

   
}
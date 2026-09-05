package com.proyecto_final.Pizza4You.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoBaseDTO {
    private int idPedido;
    private LocalDateTime fecha;
    private String estado;
    private BigDecimal precioTotal;
}
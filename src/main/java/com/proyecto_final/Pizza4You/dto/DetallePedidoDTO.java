package com.proyecto_final.Pizza4You.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
@Data
public class DetallePedidoDTO {
    
    private String nombreProducto;
    private int cantidad;
    private BigDecimal precioCompra;
    private BigDecimal subtotal;
    private List<String> extras;

}
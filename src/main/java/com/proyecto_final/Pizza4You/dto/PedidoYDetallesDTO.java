package com.proyecto_final.Pizza4You.dto;

import java.util.List;

import lombok.Data;
@Data
public class PedidoYDetallesDTO {
    
    private int idPedido;
    private String estado;
    private List<DetallePedidoDTO> detalles;

    
}
package com.proyecto_final.Pizza4You.dto;

import java.util.List;

import lombok.Data;

@Data
public class PedidoRequest {
    private Integer idCliente;
    private Integer id_dir;
    private List<DetallePedidoRequest> detalles;
    private String paymentIntentId;
}

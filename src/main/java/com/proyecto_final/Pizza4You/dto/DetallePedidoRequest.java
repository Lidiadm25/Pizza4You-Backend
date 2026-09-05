package com.proyecto_final.Pizza4You.dto;

import java.util.List;

import lombok.Data;

@Data
public class DetallePedidoRequest {
    private Integer idProducto;
    private Integer cantidad;
    private Integer idTamano;
    private Integer idMasa;
    private List<IngredienteExtraRequest> extras;
}

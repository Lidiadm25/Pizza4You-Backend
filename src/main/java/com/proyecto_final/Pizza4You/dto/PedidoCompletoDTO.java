package com.proyecto_final.Pizza4You.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCompletoDTO {
    private PedidoBaseDTO pedido;
    private ClienteDTO cliente;
    private DireccionDTO direccion;
}
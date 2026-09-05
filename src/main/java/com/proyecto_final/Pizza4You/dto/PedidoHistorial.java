package com.proyecto_final.Pizza4You.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoHistorial {
    
    private int idPedido;
    private String fecha;
    private String estado;
    private BigDecimal precioTotal;
    private Integer cocineroId;
    private Integer idDireccion;
    private String cocineroNombre;
    
    private Integer repartidorId;
    private String repartidorNombre;

    private Integer atencionId;
    private String atencionNombre;
    
    private List<IngredienteExtraRequest> extras;
    
}
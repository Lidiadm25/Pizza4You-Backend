package com.proyecto_final.Pizza4You.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "detalle_pedido_ingrediente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "detallePedido")
public class DetallePedidoIngrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_ing")
    private Integer idDetalleIng;

    @ManyToOne
    @JoinColumn(name = "id_detalle", nullable = false)
    private Detalle_pedido detallePedido;

    @ManyToOne
    @JoinColumn(name = "id_ing", nullable = false)
    private Ingrediente ingrediente;

    @Column(nullable = false)
    private Integer cantidad;
}
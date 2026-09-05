package com.proyecto_final.Pizza4You.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "detalle_pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor

@ToString(exclude = {"ingredientesExtra", "pedido"})
public class Detalle_pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle")
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_prod", nullable = false)
    private Producto producto;

    @Column(name = "precio_compra", nullable = false)
    private BigDecimal precioCompra;

    @Column(nullable = false)
    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "id_tamano")
    private Tamano tamano;

    @ManyToOne
    @JoinColumn(name = "id_masa")
    private Masa masa;

    @OneToMany(mappedBy = "detallePedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedidoIngrediente> ingredientesExtra = new ArrayList<>();
}
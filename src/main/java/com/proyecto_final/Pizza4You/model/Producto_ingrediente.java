package com.proyecto_final.Pizza4You.model;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "productos_ingredientes")
@IdClass(ProductoIngredienteId.class)
public class Producto_ingrediente {

    @Id
    @ManyToOne
    @JoinColumn(name = "id_prod")
    private Producto producto;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_ing")
    private Ingrediente ingrediente;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
        this.ingrediente = ingrediente;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }
}
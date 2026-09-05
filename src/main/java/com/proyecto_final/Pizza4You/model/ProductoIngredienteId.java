package com.proyecto_final.Pizza4You.model;

import java.io.Serializable;
import java.util.Objects;

public class ProductoIngredienteId implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer producto;
    private Integer ingrediente;

    public ProductoIngredienteId() {}

    public Integer getProducto() {
        return producto;
    }

    public void setProducto(Integer producto) {
        this.producto = producto;
    }

    public Integer getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Integer ingrediente) {
        this.ingrediente = ingrediente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductoIngredienteId that = (ProductoIngredienteId) o;
        return Objects.equals(producto, that.producto) && Objects.equals(ingrediente, that.ingrediente);
    }

    @Override
    public int hashCode() {
        return Objects.hash(producto, ingrediente);
    }
}

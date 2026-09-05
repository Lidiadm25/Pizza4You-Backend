package com.proyecto_final.Pizza4You.model;

public enum EstadoPedido {
    RECIBIDO("RECIBIDO"),
    PREPARANDO("PREPARANDO"),
    ENCAMINO("ENCAMINO"),
    ENTREGADO("ENTREGADO"),
    CANCELADO("CANCELADO");

    private final String label;

    EstadoPedido(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EstadoPedido fromString(String estado) {
        for (EstadoPedido e : EstadoPedido.values()) {
            if (e.label.equalsIgnoreCase(estado)) {
                return e;
            }
        }
        return RECIBIDO;
    }
}
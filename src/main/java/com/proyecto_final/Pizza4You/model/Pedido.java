package com.proyecto_final.Pizza4You.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "detalles") 
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPedido;


 

    @ManyToOne
    @JoinColumn(name = "id_repartidor")
    @JsonIgnore
    private Empleado repartidor;

    @ManyToOne
    @JoinColumn(name = "id_cocinero")
    @JsonIgnore
    private Empleado cocinero;

    @ManyToOne
    @JoinColumn(name = "id_atencion")
    @JsonIgnore
    private Empleado atencion;

    private LocalDateTime fecha;
    
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('RECIBIDO', 'PREPARANDO','ENCAMINO', 'ENTREGADO', 'CANCELADO') DEFAULT 'RECIBIDO'")
    private EstadoPedido estado = EstadoPedido.RECIBIDO;
    private BigDecimal precioTotal;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "id_dir")
    @JsonIgnore
    private Direccion direccion;

    @OneToMany(mappedBy = "pedido")
    @JsonIgnore
    private List<Detalle_pedido> detalles;
}
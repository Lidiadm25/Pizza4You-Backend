package com.proyecto_final.Pizza4You.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto_final.Pizza4You.model.EstadoPedido;
import com.proyecto_final.Pizza4You.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
    List<Pedido> findByClienteIdClienteOrderByFechaDesc(Integer idCliente);
    
    Page<Pedido> findByClienteIdCliente(Integer idCliente, Pageable pageable);
    
    List<Pedido> findByCocineroIdEmpleadoOrderByFechaDesc(Integer idEmpleado);
    
    Page<Pedido> findByCocineroIdEmpleado(Integer idEmpleado, Pageable pageable);
    
    List<Pedido> findByRepartidorIdEmpleadoOrderByFechaDesc(Integer idEmpleado);
    
    Page<Pedido> findByRepartidorIdEmpleado(Integer idEmpleado, Pageable pageable);
    
    List<Pedido> findByCocineroIdEmpleadoOrRepartidorIdEmpleadoOrderByFechaDesc(Integer idCocinero, Integer idRepartidor);
    
    Page<Pedido> findByCocineroIdEmpleadoOrRepartidorIdEmpleado(Integer idCocinero, Integer idRepartidor, Pageable pageable);
    
    List<Pedido> findByEstadoNotInOrderByFechaDesc(List<EstadoPedido> estados);
    
    List<Pedido> findByEstadoNotIn(List<EstadoPedido> estados);
    
    List<Pedido> findByEstadoInOrderByFechaDesc(List<EstadoPedido> estados);
    
    Page<Pedido> findByEstadoIn(List<EstadoPedido> estados, Pageable pageable);
}


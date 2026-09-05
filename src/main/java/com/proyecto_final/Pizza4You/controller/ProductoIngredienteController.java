package com.proyecto_final.Pizza4You.controller;

import com.proyecto_final.Pizza4You.dto.IngredienteProductoRequest;
import com.proyecto_final.Pizza4You.model.Producto_ingrediente;
import com.proyecto_final.Pizza4You.services.ProductoIngService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos-ing")
public class ProductoIngredienteController {


    
    @Autowired
    private ProductoIngService service;

    // Obtener todos los ingredientes de un producto
    @GetMapping("/{id}")
    public ResponseEntity<List<Producto_ingrediente>> getIngredientesPorProducto(@PathVariable int id) {
       
        return ResponseEntity.ok(this.service.findIngredientes(id));
    }

    // Añadir ingrediente a un producto con cantidad
    @PostMapping("/{idProducto}")
    public ResponseEntity<Producto_ingrediente> addIngrediente(
            @PathVariable int idProducto,
            @RequestBody IngredienteProductoRequest request) {
    	
    	
        
        return ResponseEntity.ok(this.service.addIngrediente(idProducto, request));
    }

    // Actualizar cantidad de un ingrediente en un producto
    @PutMapping("/{idProducto}")
    public ResponseEntity<Producto_ingrediente> updateCantidad(
            @PathVariable int idProducto,
            @PathVariable int idIngrediente,
            @RequestBody IngredienteProductoRequest request) {
        
        return ResponseEntity.ok(this.service.updateCantidad( idProducto, request  ));
    }

    // Eliminar ingrediente de un producto
    @DeleteMapping("/{idProducto}/{idIngrediente}")
    public ResponseEntity<Void> removeIngrediente(
            @PathVariable int idProducto,
            @PathVariable int idIngrediente) {
       
    	this.service.deleteIngrediente(idProducto,  idIngrediente);
        return ResponseEntity.ok().build();
    }

    // Eliminar todos los ingredientes de un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAllFromProducto(@PathVariable int id) {
        this.service.deleteAll(id);
        return ResponseEntity.ok().build();
    }
    
    // Eliminar varios ingredientes
    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> deleteAllFromProducto(@PathVariable int id, @RequestBody List<Integer> ids) {
        this.service.deleteVarious(ids, id);
        return ResponseEntity.ok().build();
    }
}
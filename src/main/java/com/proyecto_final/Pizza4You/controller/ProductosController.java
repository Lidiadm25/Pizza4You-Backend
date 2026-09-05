package com.proyecto_final.Pizza4You.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.proyecto_final.Pizza4You.model.Producto;
import com.proyecto_final.Pizza4You.dto.OpcionesProductoRequest;
import com.proyecto_final.Pizza4You.services.FileStorageService;
import com.proyecto_final.Pizza4You.services.ProductoServicio;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@Autowired
    private FileStorageService fileStorageService;
	
	
	@GetMapping()
	public ResponseEntity<List<Producto>> findAll(){
		List<Producto> productos = this.productoServicio.findAll();
		if(productos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productos);
	}
	
	@PostMapping(value = "/crear", consumes = {"multipart/form-data"})
	public ResponseEntity<Producto> crearConImagen(
	        @RequestPart("producto") Producto producto,
	        @RequestPart("opciones") OpcionesProductoRequest opciones,
	        @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws IOException {
		
	
		if (imagen != null && !imagen.isEmpty()) {
            String nombreImagenGenerado = fileStorageService.guardarImagen(imagen);
            producto.setImagen(nombreImagenGenerado);
        }
	    
	    return ResponseEntity.ok(productoServicio.create(producto, opciones));
	}
	
	@GetMapping("/destacados")
	public ResponseEntity<List<Producto>> findDestacados(){
		List<Producto> productos = this.productoServicio.obtenerTop3ProductosDelMes();
		if(productos.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(productos);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> findById(@PathVariable Integer id){
	    Optional<Producto> producto = this.productoServicio.findById(id);
	    if(producto.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    }
	    return ResponseEntity.ok(producto.get());
	}
	
	@GetMapping("/categoria/{id}")
    public ResponseEntity<List<Producto>> obtenerProductosPorCategoria(@PathVariable("id") Integer idCategoria) {
        List<Producto> productos = productoServicio.fidnByCategoria(idCategoria);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productos);
    }
	
	/*@PostMapping()
	public ResponseEntity<Producto> create(@RequestBody Producto producto) {

		return ResponseEntity.ok(this.productoServicio.create(producto));
	}
	*/
	@PatchMapping(value = "/{id}", consumes = {"multipart/form-data"})
	public ResponseEntity<Producto> update(
	        @PathVariable Integer id,
	        @RequestPart("producto") Producto productoActualizado,
	        @RequestPart("opciones") OpcionesProductoRequest opciones,
	        @RequestPart(value = "imagen", required = false) MultipartFile imagen) throws IOException {

		if (imagen != null && !imagen.isEmpty()) {
            String nombreImagenGenerado = fileStorageService.guardarImagen(imagen);
            productoActualizado.setImagen(nombreImagenGenerado);
        }

	    return ResponseEntity.ok(this.productoServicio.update(id, productoActualizado, opciones));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		this.productoServicio.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@PatchMapping("/descatalogar")
	public ResponseEntity<Void> descatalogar(@RequestBody Integer id) {
		this.productoServicio.descatalogar(id);
		System.out.println("entra");
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/{id}/opciones")
	 @PreAuthorize("hasAnyRole('COCINERO', 'REPARTIDOR', 'ATENCION')")
	public ResponseEntity<Producto> asignarOpcionesAlProducto(
	        @PathVariable Integer id, 
	        @RequestBody OpcionesProductoRequest request) {
	    
	    return ResponseEntity.ok(this.productoServicio.asignarOpciones(id, request));
	}
}

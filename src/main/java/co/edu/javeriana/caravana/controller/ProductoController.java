package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.model.Producto;
import co.edu.javeriana.caravana.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productoService.crear(producto));
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable Long id, 
                                           @RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.actualizar(id, producto));
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    // Incrementar la cantidad de un producto
    @PatchMapping("/{id}/incrementar")
    public ResponseEntity<Void> incrementarCantidad(@PathVariable Long id,
                                                    @RequestParam Integer cantidad) {
        productoService.incrementarCantidad(id, cantidad);
        return ResponseEntity.ok().build();
    }

    // Decrementar la cantidad de un producto
    @PatchMapping("/{id}/decrementar")
    public ResponseEntity<Void> decrementarCantidad(@PathVariable Long id,
                                                    @RequestParam Integer cantidad) {
        productoService.decrementarCantidad(id, cantidad);
        return ResponseEntity.ok().build();
    }
}
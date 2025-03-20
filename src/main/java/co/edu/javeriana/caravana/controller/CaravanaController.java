package co.edu.javeriana.caravana.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana.model.Caravana;
import co.edu.javeriana.caravana.service.CaravanaService;

@RestController
@RequestMapping("/api/caravanas")
public class CaravanaController {

    private final CaravanaService caravanaService;

    public CaravanaController(CaravanaService caravanaService) {
        this.caravanaService = caravanaService;
    }

    // Operaciones CRUD básicas
    @GetMapping
    public List<Caravana> getAll() {
        return caravanaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Caravana> getById(@PathVariable Long id) {
        return caravanaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Caravana> create(@RequestBody Caravana caravana) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(caravanaService.save(caravana));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Caravana> update(@PathVariable Long id, @RequestBody Caravana caravana) {
        return ResponseEntity.ok(caravanaService.update(id, caravana));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        caravanaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Acciones personalizadas
    @PostMapping("/{id}/comprar")
    public ResponseEntity<Void> comprarProducto(
            @PathVariable Long id,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad) {
        caravanaService.comprarProducto(id, productoId, cantidad);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/vender")
    public ResponseEntity<Void> venderProducto(
            @PathVariable Long id,
            @RequestParam Long productoId,
            @RequestParam Integer cantidad) {
        caravanaService.venderProducto(id, productoId, cantidad);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/viajar")
    public ResponseEntity<Void> viajar(
            @PathVariable Long id,
            @RequestParam Long ciudadDestinoId,
            @RequestParam Long rutaId) {
        caravanaService.viajar(id, ciudadDestinoId, rutaId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/pagar-servicio")
    public ResponseEntity<Void> pagarServicio(
            @PathVariable Long id,
            @RequestParam Long servicioId) {
        caravanaService.pagarServicio(id, servicioId);
        return ResponseEntity.ok().build();
    }
}
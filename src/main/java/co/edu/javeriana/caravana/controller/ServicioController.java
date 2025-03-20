package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.model.Servicio;
import co.edu.javeriana.caravana.service.ServicioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios")
public class ServicioController {

    private final ServicioService servicioService;

    public ServicioController(ServicioService servicioService) {
        this.servicioService = servicioService;
    }

    @GetMapping
    public ResponseEntity<List<Servicio>> getAll() {
        return ResponseEntity.ok(servicioService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> getById(@PathVariable Long id) {
        return ResponseEntity.ok(servicioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<Servicio> create(@RequestBody Servicio servicio) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(servicioService.crear(servicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> update(@PathVariable Long id, 
                                          @RequestBody Servicio servicio) {
        return ResponseEntity.ok(servicioService.actualizar(id, servicio));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicioService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{servicioId}/aplicar/{caravanaId}")
    public ResponseEntity<Void> aplicarServicio(@PathVariable Long servicioId,
                                                @PathVariable Long caravanaId) {
        servicioService.aplicarServicio(servicioId, caravanaId);
        return ResponseEntity.ok().build();
    }
}
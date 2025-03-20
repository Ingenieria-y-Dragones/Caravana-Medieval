package co.edu.javeriana.caravana.controller;

import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.model.Mapa;
import co.edu.javeriana.caravana.service.MapaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/mapas")
public class MapaController {

    private final MapaService mapaService;

    public MapaController(MapaService mapaService) {
        this.mapaService = mapaService;
    }

    // CRUD Básico
    @GetMapping
    public ResponseEntity<Set<Mapa>> getAll() {
        return ResponseEntity.ok(mapaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mapa> getById(@PathVariable Long id) {
        return mapaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mapa> create(@RequestBody Mapa mapa) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapaService.save(mapa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mapa> update(@PathVariable Long id, @RequestBody Mapa mapa) {
        return ResponseEntity.ok(mapaService.update(id, mapa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mapaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Operaciones específicas
    @PostMapping("/{id}/ciudades")
    public ResponseEntity<Void> addCiudad(
            @PathVariable Long id,
            @RequestParam Long ciudadId) {
        mapaService.addCiudadToMapa(id, ciudadId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/ciudades/{ciudadId}")
    public ResponseEntity<Void> removeCiudad(
            @PathVariable Long id,
            @PathVariable Long ciudadId) {
        mapaService.removeCiudadFromMapa(id, ciudadId);
        return ResponseEntity.ok().build();
    }
}
package co.edu.javeriana.caravana.controlers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.service.JugadorService;

@RestController
@RequestMapping("/api/jugadores")

public class JugadorController {
    @Autowired
    private JugadorService jugadorService;

    @GetMapping
    public ResponseEntity<List<Jugador>> listarJugadores() {
        return ResponseEntity.ok(jugadorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jugador> obtenerJugador(@PathVariable Long id) {
        return ResponseEntity.ok(jugadorService.buscarPorId(id));
    }

    @GetMapping("/{id}/puede-viajar")
    public ResponseEntity<Boolean> puedeViajar(@PathVariable Long id) {
        Jugador jugador = jugadorService.buscarPorId(id);
        return ResponseEntity.ok(jugador.puedeViajar());
    }
}

package co.edu.javeriana.caravana.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.model.Jugador.Rol;
import co.edu.javeriana.caravana.repository.JugadorRepository;

@Service
public class JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    public List<Jugador> listarTodos() {
        return jugadorRepository.findAll();
    }

    public Jugador buscarPorId(Long id) {
        return jugadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    public List<Jugador> buscarPorRol(Rol rol) {
        return jugadorRepository.findByRol(rol);
    }
}
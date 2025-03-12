package co.edu.javeriana.caravana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.repository.CaravanaRepository;
import co.edu.javeriana.caravana.repository.CiudadRepository;
import co.edu.javeriana.caravana.repository.JugadorRepository;
import co.edu.javeriana.caravana.repository.SistemaRepository;

@Service
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepo;
    @Autowired
    private CaravanaRepository caravanaRepo;
    @Autowired
    private CiudadRepository ciudadRepo;
    @Autowired
    private SistemaRepository sistemaRepo;

    public List<Jugador> findAll() {
        return jugadorRepo.findAll();
    }

    public Jugador findById(Long id) {
        return jugadorRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }

    public Jugador createJugador(String nombre, Long tiempoJugado, Jugador.TipoRol rol,
                                Long caravanaId, Long ciudadId, Long sistemaId) {
        Jugador jugador = new Jugador();
        jugador.setNombre(nombre);
        jugador.setTiempoJugado(tiempoJugado);
        jugador.setRol(rol);

        // Asignar relación según rol
        switch(rol) {
            case CARAVANERO -> jugador.setCaravana(caravanaRepo.findById(caravanaId)
                        .orElseThrow(() -> new RuntimeException("Caravana no encontrada")));
            case COMERCIANTE -> jugador.setCiudad(ciudadRepo.findById(ciudadId)
                        .orElseThrow(() -> new RuntimeException("Ciudad no encontrada")));
            case ADMINISTRADOR -> jugador.setSistema(sistemaRepo.findById(sistemaId)
                        .orElseThrow(() -> new RuntimeException("Sistema no encontrado")));
        }

        return jugadorRepo.save(jugador);
    }

    public void deleteById(Long id) {
        jugadorRepo.deleteById(id);
    }

    public Jugador update(Long id, Jugador jugadorActualizado) {
        Jugador jugador = findById(id);
        jugador.setNombre(jugadorActualizado.getNombre());
        jugador.setTiempoJugado(jugadorActualizado.getTiempoJugado());

        // Actualizar relación según rol
        switch(jugador.getRol()) {
            case CARAVANERO -> jugador.setCaravana(jugadorActualizado.getCaravana());
            case COMERCIANTE -> jugador.setCiudad(jugadorActualizado.getCiudad());
            case ADMINISTRADOR -> jugador.setSistema(jugadorActualizado.getSistema());
        }

        return jugadorRepo.save(jugador);
    }

    // Métodos para filtrar por rol
    public List<Jugador> findByRol(Jugador.TipoRol rol) {
        return jugadorRepo.findByRol(rol);
    }
}
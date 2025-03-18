package co.edu.javeriana.caravana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.model.Caravana;
import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.model.Sistema;
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

        asignarRelacion(jugador, rol, caravanaId, ciudadId, sistemaId);
        return jugadorRepo.save(jugador);
    }

    public void updateJugador(Long id, String nombre, Long tiempoJugado, Jugador.TipoRol rol,
                              Long caravanaId, Long ciudadId, Long sistemaId) {
        Jugador jugador = findById(id);
        jugador.setNombre(nombre);
        jugador.setTiempoJugado(tiempoJugado);

        // Limpiar relaciones anteriores si el rol cambia
        jugador.setCaravana(null);
        jugador.setCiudad(null);
        jugador.setSistema(null);

        asignarRelacion(jugador, rol, caravanaId, ciudadId, sistemaId);
        jugadorRepo.save(jugador);
    }

    private void asignarRelacion(Jugador jugador, Jugador.TipoRol rol, 
                                 Long caravanaId, Long ciudadId, Long sistemaId) {
        switch(rol) {
            case CARAVANERO -> {
                Caravana caravana = caravanaRepo.findById(caravanaId)
                        .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
                jugador.setCaravana(caravana);
            }
            case COMERCIANTE -> {
                Ciudad ciudad = ciudadRepo.findById(ciudadId)
                        .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
                jugador.setCiudad(ciudad);
            }
            case ADMINISTRADOR -> {
                Sistema sistema = sistemaRepo.findById(sistemaId)
                        .orElseThrow(() -> new RuntimeException("Sistema no encontrado"));
                jugador.setSistema(sistema);
            }
        }
    }

    public List<Jugador> findByRol(Jugador.TipoRol rol) {
        return jugadorRepo.findByRol(rol);
    }

    public void deleteById(Long id) {
        jugadorRepo.deleteById(id);
    }
}
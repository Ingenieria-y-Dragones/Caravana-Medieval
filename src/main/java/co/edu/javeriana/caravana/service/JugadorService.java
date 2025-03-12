package co.edu.javeriana.caravana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.model.Administrador;
import co.edu.javeriana.caravana.model.Caravana;
import co.edu.javeriana.caravana.model.Caravanero;
import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.model.Comerciante;
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

    public Jugador createJugador(String tipo, String nombre, Long tiempoJugado, 
                                Long caravanaId, Long ciudadId, Long sistemaId) {
        return jugadorRepo.save(crearSubclase(tipo, nombre, tiempoJugado, caravanaId, ciudadId, sistemaId));
    }

    private Jugador crearSubclase(String tipo, String nombre, Long tiempoJugado, 
                                Long caravanaId, Long ciudadId, Long sistemaId) {
        switch(tipo.toLowerCase()) {
            case "caravanero" -> {
                Caravana caravana = caravanaRepo.findById(caravanaId)
                        .orElseThrow(() -> new RuntimeException("Caravana no encontrada"));
                return new Caravanero(null, nombre, tiempoJugado, caravana);
            }
                
            case "comerciante" -> {
                Ciudad ciudad = ciudadRepo.findById(ciudadId)
                        .orElseThrow(() -> new RuntimeException("Ciudad no encontrada"));
                return new Comerciante(null, nombre, tiempoJugado, ciudad);
            }
                
            case "administrador" -> {
                Sistema sistema = sistemaRepo.findById(sistemaId)
                        .orElseThrow(() -> new RuntimeException("Sistema no encontrado"));
                return new Administrador(null, nombre, tiempoJugado, sistema);
            }
                
            default -> throw new IllegalArgumentException("Tipo de jugador desconocido");
        }
    }

    public List<Jugador> findAllCaravaneros() {
        return jugadorRepo.findAllCaravaneros();
    }

    public List<Jugador> findAllComerciantes() {
        return jugadorRepo.findAllComerciantes();
    }

    public List<Jugador> findAllAdministradores() {
        return jugadorRepo.findAllAdministradores();
    }

    public void deleteById(Long id) {
        jugadorRepo.deleteById(id);
    }

    public Jugador update(Long id, Jugador jugadorActualizado) {
        Jugador jugador = findById(id);
        jugador.setNombre(jugadorActualizado.getNombre());
        jugador.setTiempoJugado(jugadorActualizado.getTiempoJugado());
        return jugadorRepo.save(jugador);
    }
}
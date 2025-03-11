package co.edu.javeriana.caravana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.javeriana.caravana.model.Caravana;
import co.edu.javeriana.caravana.model.Caravanero;
import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.model.Comerciante;
import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.repository.CaravanaRepository;
import co.edu.javeriana.caravana.repository.CiudadRepository;
import co.edu.javeriana.caravana.repository.JugadorRepository;

@Service
public class JugadorService {
    @Autowired
    private JugadorRepository jugadorRepo;
    
    @Autowired
    private CaravanaRepository caravanaRepo;
    
    @Autowired
    private CiudadRepository ciudadRepo;

    // Crear jugador con relaciones
    public Jugador createJugador(String tipo, String nombre, Long tiempoJugado, 
                                Long caravanaId, Long ciudadId) {
        return jugadorRepo.save(crearSubclase(tipo, nombre, tiempoJugado, caravanaId, ciudadId));
    }

    // Método privado para crear subclases
    private Jugador crearSubclase(String tipo, String nombre, Long tiempoJugado, 
                                Long caravanaId, Long ciudadId) {
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
                
            default -> throw new IllegalArgumentException("Tipo de jugador desconocido");
        }
    }

    // Métodos CRUD básicos
    public List<Jugador> findAll() { return jugadorRepo.findAll(); }
    public Jugador findById(Long id) { 
        return jugadorRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Jugador no encontrado"));
    }
    public void deleteById(Long id) { jugadorRepo.deleteById(id); }
    
    public List<Jugador> findAllCaravaneros() {
        return jugadorRepo.findAllCaravaneros();
    }

    public List<Jugador> findAllComerciantes() {
        return jugadorRepo.findAllComerciantes();
    }
}
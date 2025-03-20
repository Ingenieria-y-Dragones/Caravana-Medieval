package co.edu.javeriana.caravana.service;

import java.util.Set;

import co.edu.javeriana.caravana.model.Ciudad;
import co.edu.javeriana.caravana.model.Mapa;

public interface MapaService {
    // CRUD Básico
    Set<Mapa> obtenerTodos();
    Mapa obtenerPorId(Long id);
    Mapa crear(Mapa mapa);
    Mapa actualizar(Long id, Mapa mapa);
    void eliminar(Long id);
    
    // Gestión de ciudades
    void agregarCiudad(Long mapaId, Long ciudadId);
    void eliminarCiudad(Long mapaId, Long ciudadId);
    boolean existeCiudadEnMapa(Long mapaId, Long ciudadId);
    Set<Ciudad> obtenerCiudades(Long mapaId);
    
    // Validaciones y operaciones compuestas
    void validarCiudadUnica(Long ciudadId);
    Mapa crearConCiudades(Set<Long> ciudadIds);
}
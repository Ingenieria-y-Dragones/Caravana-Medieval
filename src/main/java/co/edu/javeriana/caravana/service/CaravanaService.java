package co.edu.javeriana.caravana.service;

import java.util.List;
import java.util.Optional;

import co.edu.javeriana.caravana.model.Caravana;

public interface CaravanaService {
    List<Caravana> findAll();
    Optional<Caravana> findById(Long id);
    Caravana save(Caravana caravana);
    Caravana update(Long id, Caravana caravana);
    void deleteById(Long id);
    
    void comprarProducto(Long caravanaId, Long productoId, Integer cantidad);
    void venderProducto(Long caravanaId, Long productoId, Integer cantidad);
    void viajar(Long caravanaId, Long ciudadDestinoId, Long rutaId);
    void pagarServicio(Long caravanaId, Long servicioId);
}
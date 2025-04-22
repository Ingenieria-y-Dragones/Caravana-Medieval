package co.edu.javeriana.caravana.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.caravana.model.InventarioCiudad;

public interface InventarioCiudadRepository extends JpaRepository<InventarioCiudad, Long> {

    Optional<InventarioCiudad> findByCiudadIdAndProductoId(Long ciudadId, Long productoId);

}


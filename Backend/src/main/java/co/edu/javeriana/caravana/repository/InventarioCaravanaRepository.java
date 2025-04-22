package co.edu.javeriana.caravana.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.javeriana.caravana.model.InventarioCaravana;

public interface InventarioCaravanaRepository extends JpaRepository<InventarioCaravana, Long> {

    Optional<InventarioCaravana> findByCaravanaIdAndProductoId(Long caravanaId, Long productoId);

}


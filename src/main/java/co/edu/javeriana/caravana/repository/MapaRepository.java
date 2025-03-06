package co.edu.javeriana.caravana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana.model.Mapa;

@Repository
public interface MapaRepository extends JpaRepository<Mapa, Long> {

}

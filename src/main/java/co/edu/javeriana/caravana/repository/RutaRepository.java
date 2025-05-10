package co.edu.javeriana.caravana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana.model.Ruta;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {

}

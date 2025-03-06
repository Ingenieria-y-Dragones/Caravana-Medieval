package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {

}

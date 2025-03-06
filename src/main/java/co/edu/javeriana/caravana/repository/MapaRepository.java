package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Mapa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface MapaRepository extends JpaRepository<Mapa, Long> {

}

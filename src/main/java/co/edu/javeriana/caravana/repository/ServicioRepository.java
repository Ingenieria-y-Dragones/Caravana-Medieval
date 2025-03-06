package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

}

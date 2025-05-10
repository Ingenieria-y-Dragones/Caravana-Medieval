package co.edu.javeriana.caravana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana.model.Comerciante;

@Repository
public interface ComercianteRepository extends JpaRepository<Comerciante, Long> {

}

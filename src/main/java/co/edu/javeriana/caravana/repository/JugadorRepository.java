package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {

}
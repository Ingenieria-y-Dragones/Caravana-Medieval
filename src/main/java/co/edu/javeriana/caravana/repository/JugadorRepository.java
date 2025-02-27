package co.edu.javeriana.caravana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.model.Jugador.Rol;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
     List<Jugador> findByRol(Rol rol);
}
package co.edu.javeriana.caravana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana.model.Jugador;
import co.edu.javeriana.caravana.model.Jugador.TipoRol;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    List<Jugador> findByRol(TipoRol rol);
    List<Jugador> findByNombreIgnoreCase(String nombre);
}
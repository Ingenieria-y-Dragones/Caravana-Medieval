package co.edu.javeriana.caravana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana.model.Jugador;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    @Query("SELECT j FROM Jugador j WHERE TYPE(j) = Caravanero")
    List<Jugador> findAllCaravaneros();

    @Query("SELECT j FROM Jugador j WHERE TYPE(j) = Comerciante")
    List<Jugador> findAllComerciantes();

    @Query("SELECT j FROM Jugador j WHERE TYPE(j) = Administrador")
    List<Jugador> findAllAdministradores();

    List<Jugador> findByNombreIgnoreCase(String nombre);
}
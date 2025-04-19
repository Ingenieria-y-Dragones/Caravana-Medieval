package co.edu.javeriana.caravana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana.model.Ruta;

@Repository
public interface RutaRepository extends JpaRepository<Ruta, Long> {
    @Query("SELECT r FROM Ruta r WHERE r.ciudadOrigen.id = (SELECT c.ciudad.id FROM Caravana c WHERE c.id = :caravanaId)")
    List<Ruta> findRutasByCiudadOrigenDeCaravana(@Param("caravanaId") Long caravanaId);
}

package co.edu.javeriana.caravana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
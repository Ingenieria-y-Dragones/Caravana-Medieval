package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
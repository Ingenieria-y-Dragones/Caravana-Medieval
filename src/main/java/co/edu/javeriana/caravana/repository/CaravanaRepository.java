package co.edu.javeriana.caravana.repository;

import co.edu.javeriana.caravana.model.Caravana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CaravanaRepository extends JpaRepository<Caravana, Long> {

}

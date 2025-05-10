package co.edu.javeriana.caravana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.caravana.model.Caravana;

@Repository
public interface CaravanaRepository extends JpaRepository<Caravana, Long> {

}

package viaflow.catalogodeviagens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viaflow.catalogodeviagens.entities.Destinos;

@Repository
public interface DestinosRepository extends JpaRepository<Destinos, Integer> {

}

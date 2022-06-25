package viaflow.catalogodeviagens.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viaflow.catalogodeviagens.entities.DestinosEntity;

@Repository
public interface DestinosRepository extends JpaRepository<DestinosEntity, Integer> {

}

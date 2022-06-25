package viaflow.catalogodeviagens.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viaflow.catalogodeviagens.entities.FormularioEntity;

@Repository
public interface FormularioRepository extends JpaRepository<FormularioEntity, Integer> {

}

package viaflow.catalogodeviagens.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viaflow.catalogodeviagens.entities.Formulario;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Integer> {

}

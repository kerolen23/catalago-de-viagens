package viaflow.catalogodeviagens.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import viaflow.catalogodeviagens.entities.UsuarioEntity;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {


    @Transactional
    UsuarioEntity findByEmail(String email);

    @Transactional
    UsuarioEntity findUsuarioEntityById(Integer id);

    @Transactional
    Optional<UsuarioEntity> findById(Integer id);


}
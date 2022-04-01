package papler.projetologin.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import papler.projetologin.entities.UsuarioEntity;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

   @Transactional
    UsuarioEntity findByEmail(String email);






}
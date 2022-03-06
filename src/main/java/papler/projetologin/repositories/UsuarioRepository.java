package papler.projetologin.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import papler.projetologin.entities.UsuarioEntity;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    public Optional<UsuarioEntity> findByLogin(String login);

}
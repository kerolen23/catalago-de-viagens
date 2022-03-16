package papler.projetologin.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import papler.projetologin.entities.LoginEntity;

import java.util.Optional;

@Repository
public interface UsuarioLoginRepository extends JpaRepository<LoginEntity, Integer> {

    public Optional<LoginEntity> findByLogin(String login);

    public Optional<LoginEntity> findTop1ById(Integer id);




}
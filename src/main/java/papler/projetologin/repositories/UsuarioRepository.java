package papler.projetologin.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import papler.projetologin.entities.UsuarioEntity;


@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {

    public UsuarioEntity findByEmail(String email);




}
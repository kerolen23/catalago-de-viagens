package papler.projetologin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import papler.projetologin.dto.UsuarioDto;
import papler.projetologin.entities.LoginEntity;
import papler.projetologin.repositories.UsuarioLoginRepository;
import papler.projetologin.service.exceptions.ObjectNotFoundException;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private final UsuarioLoginRepository repository;



    @Transactional
    public Optional<LoginEntity> update(Integer id){
        Optional<LoginEntity> entity = repository.findById(id);
        if (entity == null) {
           throw new ObjectNotFoundException("Id não encontrado");
        }
        return entity;
    }



}
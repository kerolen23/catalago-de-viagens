package papler.projetologin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import papler.projetologin.dto.UsuarioDto;
import papler.projetologin.entities.LoginEntity;
import papler.projetologin.entities.UsuarioEntity;
import papler.projetologin.repositories.UsuarioLoginRepository;
import papler.projetologin.repositories.UsuarioRepository;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioLoginRepository repository;

    @Transactional
    public void create(UsuarioDto dto) {

        LoginEntity LoginEntity = repository.save(papler.projetologin.entities.LoginEntity.builder()

                .login(dto.getLogin())
                .password(dto.getPassword())
                .email(dto.getEmail()).build());
    }


}
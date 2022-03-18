package papler.projetologin.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import papler.projetologin.dto.UsuarioDto;
import papler.projetologin.entities.UsuarioEntity;
import papler.projetologin.repositories.UsuarioRepository;
import papler.projetologin.service.exceptions.ObjectNotFoundException;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    @Transactional
    public Optional<UsuarioEntity> list(Integer id){
        Optional<UsuarioEntity> entity = repository.findById(id);
        if (entity == null) {
            throw new ObjectNotFoundException("Id não encontrado");
        }
        return entity;
    }
    @Transactional
    public void update(UsuarioDto dto) {

        UsuarioEntity usuario = repository.save(UsuarioEntity.builder()

                .nomeCompleto(dto.getNomeCompleto())
                .cpf(dto.getCpf())
                .dtNascimento(dto.getDtNascimento())
                        .bairro(dto.getBairro())
                        .cep(dto.getCep())
                        .cidade(dto.getCidade())
                        .complemento(dto.getComplemento())
                        .estado(dto.getEstado())
                        .telefone(dto.getTelefone())

                .build());
    }


}
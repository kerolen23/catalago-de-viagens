package papler.projetologin.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import papler.projetologin.dto.UsuarioDto;
import papler.projetologin.entities.UsuarioEntity;
import papler.projetologin.repositories.UsuarioRepository;
import papler.projetologin.service.exceptions.ObjectNotFoundException;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    private PasswordEncoder encoder;

    @Autowired
    private ModelMapper modelMapper;


    @Transactional
    public Optional<UsuarioEntity> list(Integer id){
        Optional<UsuarioEntity> entity = repository.findById(id);
        if (entity == null) {
            throw new ObjectNotFoundException("Id não encontrado");
        }
        return entity;
    }

    public UsuarioEntity update(UsuarioEntity obj) {
        find(obj.getId());
        return repository.save(obj);
    }

    public Optional<UsuarioEntity> getById(Integer id){
        return repository.findById(id);
    }
//
//    public Optional<UsuarioEntity> completarCadastro(UsuarioDto dto) {
//        Optional<UsuarioEntity> entity = getById(dto.getId());
//        entity.get().setPassword(dto.getPassword());
//        entity.get().setEmail(dto.getEmail());
//        entity.get().setNomeCompleto(dto.getNomeCompleto());
//
//        return repository.save(entity);
//
//    }
    public UsuarioEntity completarCadastro(UsuarioDto dto) throws ParseException {
        UsuarioEntity entity = modelMapper.map(dto, UsuarioEntity.class);
        if (dto.getId() != null) {
            Optional<UsuarioEntity> oldEntity = getById(dto.getId());
            entity.setPassword(dto.getPassword());
            entity.setEmail(dto.getEmail());
            entity.setNomeCompleto(dto.getNomeCompleto());
            entity.setDtNascimento(dto.getDtNascimento());
            entity.setBairro(dto.getBairro());
            entity.setCep(dto.getCep());
            entity.setCidade(dto.getCidade());
            entity.setComplemento(dto.getComplemento());
            entity.setEstado(dto.getEstado());
            entity.setTelefone(dto.getTelefone());
            entity.setNumero(dto.getNumero());
        }
        return repository.save(entity);
    }



    @Transactional
    public UsuarioEntity fromDto(UsuarioDto dto) {

        UsuarioEntity usuario = repository.save(UsuarioEntity.builder()

                .nomeCompleto(dto.getNomeCompleto())
                .password(dto.getPassword())
                .id(dto.getId())
                .dtNascimento(dto.getDtNascimento())
                .bairro(dto.getBairro())
                .cep(dto.getCep())
                .cidade(dto.getCidade())
                .complemento(dto.getComplemento())
                .estado(dto.getEstado())
                .telefone(dto.getTelefone())
                .numero(dto.getNumero())

                .build());
        return repository.save(usuario);
    }

    public UsuarioEntity find(Integer id) {

        Optional<UsuarioEntity> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + UsuarioEntity.class.getName()));

    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}
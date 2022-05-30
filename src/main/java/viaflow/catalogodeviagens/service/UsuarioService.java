package viaflow.catalogodeviagens.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import viaflow.catalogodeviagens.dto.UsuarioDto;
import viaflow.catalogodeviagens.entities.UsuarioEntity;
import viaflow.catalogodeviagens.repositories.UsuarioRepository;
import viaflow.catalogodeviagens.security.UserSS;
import viaflow.catalogodeviagens.service.exceptions.ObjectNotFoundException;

import javax.transaction.Transactional;
import java.net.URI;
import java.text.ParseException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    private ModelMapper modelMapper;

    private S3Service s3Service;


    @Transactional
    public Optional<UsuarioEntity> list(Integer id) {
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

    public Optional<UsuarioEntity> getById(Integer id) {
        return repository.findById(id);
    }

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


    public URI uploadProfilePicture(MultipartFile multipartFile) {
        UserSS user = UserService.authenticated();

        if (user == null) {
            throw new AuthorizationServiceException("Acesso negado");
        }
        URI uri = s3Service.uploadFile(multipartFile);
        UsuarioEntity usuario = repository.findUsuarioEntityById(user.getId());
        usuario.setImageUrl(uri.toString());

        repository.save(usuario);
        return uri;
    }


}
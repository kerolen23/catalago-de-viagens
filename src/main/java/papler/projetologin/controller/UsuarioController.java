package papler.projetologin.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import papler.projetologin.dto.UsuarioDto;
import papler.projetologin.entities.UsuarioEntity;
import papler.projetologin.repositories.UsuarioRepository;
import papler.projetologin.service.EmailService;
import papler.projetologin.service.UsuarioService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioService service;

    private PasswordEncoder encoder;
    @Autowired
    private EmailService emailService;


    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody UsuarioEntity usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        emailService.sendOrderConfirmationEmail(usuario);
        repository.save(usuario);
    }

    @PostMapping("/picture")
    public ResponseEntity<Void> uploadPicture(@RequestParam(name = "file") MultipartFile file) {
        URI uri = service.uploadProfilePicture(file);
        return ResponseEntity.created(uri).build();
    }

    @PatchMapping("completar/cadastro")
    public ResponseEntity completarCadastro(@Valid @RequestBody UsuarioDto dto, @PathVariable Integer id) throws ParseException {
        UsuarioEntity entity = service.completarCadastro(dto);
        return ResponseEntity.ok().body(entity);
    }

    @PutMapping("/update")
    public void update(@RequestBody UsuarioEntity usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Optional<UsuarioEntity>> buscaUsuario(@PathVariable(value = "id") Integer id) {
        Optional<UsuarioEntity> entity = service.list(id);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioEntity>> listarTodos() {
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("{id}/cadastro")
    public void delete(UsuarioEntity usuario) {
        repository.delete(usuario);
    }


}
package viaflow.catalogodeviagens.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import viaflow.catalogodeviagens.dto.UsuarioDto;
import viaflow.catalogodeviagens.entities.UsuarioEntity;
import viaflow.catalogodeviagens.repositories.UsuarioRepository;
import viaflow.catalogodeviagens.service.UsuarioService;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private UsuarioRepository repository;
    private UsuarioService service;
    private PasswordEncoder encoder;

    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody UsuarioEntity usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        repository.save(usuario);
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
package papler.projetologin.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import papler.projetologin.entities.UsuarioEntity;
import papler.projetologin.repositories.UsuarioRepository;
import papler.projetologin.service.UsuarioService;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private final UsuarioRepository repository;
    @Autowired
    private final UsuarioService service;

    private final PasswordEncoder encoder;


    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody UsuarioEntity usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }
    @PatchMapping ("/update/{id}/cadastro")
    public void update(@RequestBody UsuarioEntity usuario){
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Optional<UsuarioEntity>> buscaUsuario(@PathVariable(value="id") Integer id){
        Optional<UsuarioEntity> entity = service.list(id);
        return ResponseEntity.ok().body(entity);
    }
    @GetMapping("/listar/")
    public ResponseEntity<List<UsuarioEntity>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }


    @DeleteMapping("{id}/delete")
    public void deletaProduto(@RequestBody UsuarioEntity usuario) {
        repository.delete(usuario);
    }
    }

package papler.projetologin.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import papler.projetologin.entities.UsuarioEntity;
import papler.projetologin.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioRepository repository;

    private final PasswordEncoder encoder;


    @PostMapping("/cadastrar")
    public void cadastrar(@RequestBody UsuarioEntity usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }

    @PutMapping ("/update/cadastro")
    public UsuarioEntity update(@RequestBody UsuarioEntity usuario){
        return repository.save(usuario);
    }

    @GetMapping("/cadastro/{id}")
    public Optional<UsuarioEntity> listaCadastro(@PathVariable(value="id") Integer id){
        return repository.findById(id);
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

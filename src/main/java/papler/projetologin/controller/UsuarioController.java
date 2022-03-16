package papler.projetologin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import papler.projetologin.entities.UsuarioEntity;
import papler.projetologin.repositories.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepository repository;

    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }


    @PostMapping("/concluirCadastro")
    public void concluirCadastro(@RequestBody UsuarioEntity usuario) {
        repository.save(usuario);

    }

    @GetMapping("/cadastro/{id}")
    public Optional<UsuarioEntity> listaCadastro(@PathVariable(value="id") Integer id){
        return repository.findById(id);
    }

    @GetMapping("/listar/")
    public ResponseEntity<List<UsuarioEntity>> listarTodos(){
        return ResponseEntity.ok(repository.findAll());
    }

    @PatchMapping ("/update/cadastro")
    public UsuarioEntity update(@RequestBody UsuarioEntity usuario){
           return repository.save(usuario);
    }

    @DeleteMapping("{id}/delete")
    public void deletaProduto(@RequestBody UsuarioEntity usuario) {
        repository.delete(usuario);
    }
    }

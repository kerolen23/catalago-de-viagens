package papler.projetologin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import papler.projetologin.entities.UsuarioEntity;
import papler.projetologin.repositories.UsuarioRepository;
import papler.projetologin.service.UsuarioService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private final UsuarioRepository repository;
    @Autowired
    private final UsuarioService service;

    public UsuarioController(UsuarioRepository repository, UsuarioService service) {
        this.repository = repository;
        this.service = service;
    }


    @PostMapping("/concluirCadastro")
    public void concluirCadastro(@RequestBody UsuarioEntity usuario) {
        repository.save(usuario);

    }
    @GetMapping("/usuario/{id}")
    public ResponseEntity<Optional<UsuarioEntity>> buscaUsuario(@PathVariable(value="id") Integer id){
        Optional<UsuarioEntity> entity = service.update(id);
        return ResponseEntity.ok().body(entity);
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

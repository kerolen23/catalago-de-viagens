package papler.projetologin.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import papler.projetologin.entities.LoginEntity;
import papler.projetologin.repositories.UsuarioLoginRepository;
import papler.projetologin.service.LoginService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private final UsuarioLoginRepository loginRepository;
    @Autowired
    private final PasswordEncoder encoder;
    @Autowired
    private final LoginService service;

    public LoginController(UsuarioLoginRepository loginRepository, PasswordEncoder encoder, LoginService service) {
        this.loginRepository = loginRepository;
        this.encoder = encoder;
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/salvar")
    public ResponseEntity<LoginEntity> salvar(@RequestBody LoginEntity usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        return ResponseEntity.ok(loginRepository.save(usuario));
    }

   @GetMapping("/usuario")
  public ResponseEntity<Boolean> validarSenha(@RequestParam String login,
                                              @RequestParam String password) {

       Optional<LoginEntity> optUsuario = loginRepository.findByLogin(login);
        if (optUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

       LoginEntity usuario = optUsuario.get();
        boolean valid = encoder.matches(password, usuario.getPassword());

        HttpStatus status = (valid) ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return ResponseEntity.status(status).body(valid);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<Optional<LoginEntity>> listaUsuario(@PathVariable(value="id") Integer id){
        Optional<LoginEntity> entity = service.update(id);
        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/listar/")
    public ResponseEntity<List<LoginEntity>> listarTodos(){
        return ResponseEntity.ok(loginRepository.findAll());
    }

    @PatchMapping ("/update/usuario")
    public LoginEntity update(@RequestBody LoginEntity usuario){
        return loginRepository.save(usuario);
    }

    @DeleteMapping("{id}/delete")
    public void deletaUsuario(@RequestBody LoginEntity usuario) {
        loginRepository.delete(usuario);
    }
    }

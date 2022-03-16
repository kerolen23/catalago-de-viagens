package papler.projetologin.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import papler.projetologin.entities.LoginEntity;
import papler.projetologin.repositories.UsuarioLoginRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final UsuarioLoginRepository repository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<LoginEntity> usuario = repository.findByLogin(login);
        if (usuario == null){
            throw new UsernameNotFoundException("Usuario [" + login + "] não encontrado");
        }
        return new User(usuario.get().getUsername(), usuario.get()
                .getPassword(), true, true, true, true,
                usuario.get().getAuthorities());
    }
}

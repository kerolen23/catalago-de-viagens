package papler.projetologin.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name="LoginUsuario")

public class LoginEntity implements UserDetails {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name= "ID_USER")
        private Integer id;
        @Column(unique = true, name = "login")
        private String login;
        @Column(name = "password")
        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        private String password;
        @Column(name = "email", unique = true)
        private String email;

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
        }

        @Override
        public String getUsername() {
                return this.login;
        }

        @Override
        public String getPassword() {
                return this.password;
        }
        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }
}


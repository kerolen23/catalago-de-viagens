package papler.projetologin.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import papler.projetologin.service.DetalheUsuarioServiceImpl;

import static papler.projetologin.security.SecurityConstants.SIGN_UP_URL;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
public class JWTConfiguracao extends WebSecurityConfigurerAdapter {

    private final DetalheUsuarioServiceImpl detalheUsuarioService;
    private final PasswordEncoder passwordEncoder;

    public JWTConfiguracao(DetalheUsuarioServiceImpl detalheUsuarioService, PasswordEncoder passwordEncoder) {
        this.detalheUsuarioService = detalheUsuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detalheUsuarioService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, SIGN_UP_URL).permitAll()
                .antMatchers("*/protected/**").hasRole("USER")
                .antMatchers("/*/admin/**").hasRole("ADMIN")
                .and()
                .addFilter(new JWTAutenticarFilter(authenticationManager()))
                .addFilter(new JWTValidarFilter(authenticationManager(), detalheUsuarioService))
                .addFilterBefore(new JWTAutenticarFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/login/usuario").permitAll()
                .antMatchers("/login/usuario").permitAll();
    }
   // @Bean
   // CorsConfigurationSource corsConfigurationSource() {
   // final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

  //  CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
  //  source.registerCorsConfiguration("/**", corsConfiguration);
  //  return source;
  //  }
}

package papler.projetologin.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import papler.projetologin.service.DetalheUsuarioServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static papler.projetologin.security.SecurityConstants.*;


public class JWTValidarFilter extends BasicAuthenticationFilter {

    public JWTValidarFilter(AuthenticationManager authenticationManager, DetalheUsuarioServiceImpl service) {
        super(authenticationManager);
        this.service = service;
    }

    private final DetalheUsuarioServiceImpl service;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        String header = request.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = getAuthenticationToken(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }
    private UsernamePasswordAuthenticationToken getAuthenticationToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        if(token == null ) return null;
        String username = Jwts.parser().setSigningKey(SCRET)
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();

        UserDetails detalheUsuarioData = service.loadUserByUsername(username);
        return username != null ?
                new UsernamePasswordAuthenticationToken(username, null
                        , detalheUsuarioData.getAuthorities()): null;


    }

}

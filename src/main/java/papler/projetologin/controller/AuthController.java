package papler.projetologin.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import papler.projetologin.dto.EmailDto;
import papler.projetologin.security.JWTUtil;
import papler.projetologin.security.UserSS;
import papler.projetologin.service.AuthService;
import papler.projetologin.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/auth")
@RestController
@AllArgsConstructor
public class AuthController {


    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService service;

    @PostMapping ("/refresh_token")
    public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
        UserSS user = UserService.authenticated();
        String token = jwtUtil.generateToken(user.getUsername());
        response.addHeader("Authorization", "Bearer " + token);
        return ResponseEntity.noContent().build();
    }


    @PostMapping ("/forgot")
    public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDto objDto) {

        service.sendNewPassword(objDto.getEmail());
        return ResponseEntity.noContent().build();
    }




}

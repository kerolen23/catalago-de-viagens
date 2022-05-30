package viaflow.catalogodeviagens.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import viaflow.catalogodeviagens.entities.UsuarioEntity;
import viaflow.catalogodeviagens.repositories.UsuarioRepository;
import viaflow.catalogodeviagens.service.exceptions.ObjectNotFoundException;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private BCryptPasswordEncoder pe;

    @Autowired
    private EmailService emailService;


    private Random rand = new Random();

    public void sendNewPassword(String email) {

        UsuarioEntity cliente = repository.findByEmail(email);
        if (cliente == null) {
            throw new ObjectNotFoundException("Email não encontrado");
        }

        String newPass = newPassword();
        cliente.setPassword(pe.encode(newPass));

        repository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;

    }

}

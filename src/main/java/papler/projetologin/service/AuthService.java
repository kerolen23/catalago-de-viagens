package papler.projetologin.service;

import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import papler.projetologin.entities.UsuarioEntity;
import papler.projetologin.repositories.UsuarioRepository;
import papler.projetologin.service.exceptions.ObjectNotFoundException;

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

  // private String newPassword() {
   //     char[] vet = new char[10];
   //     for (int i=0; i<10; i++) {
   //         vet[i] = randomChar();
   //     }
   //     return new String(vet);
  //  }

   // private char randomChar() {
    //    int opt = rand.nextInt(3);
    //    if (opt == 0) { // gera um digito
    //        return (char) (rand.nextInt(10) + 48);
    //    }
     //   else if (opt == 1) { // gera letra maiuscula
    //        return (char) (rand.nextInt(26) + 65);
    //    }
    //    else { // gera letra minuscula
     //       return (char) (rand.nextInt(26) + 97);
   //     }
   // }

    private String newPassword() {
        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = true;
        String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
        return generatedString;

    }

}

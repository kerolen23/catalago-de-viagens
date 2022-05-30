package viaflow.catalogodeviagens.service;

import org.springframework.mail.SimpleMailMessage;
import viaflow.catalogodeviagens.entities.UsuarioEntity;

public interface EmailService {

    void sendEmail (SimpleMailMessage msg);

    void sendNewPasswordEmail (UsuarioEntity usuario, String newPass);

}

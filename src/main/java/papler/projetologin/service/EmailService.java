package papler.projetologin.service;

import org.springframework.mail.SimpleMailMessage;
import papler.projetologin.entities.UsuarioEntity;

public interface EmailService {

    void sendOrderConfirmationEmail (UsuarioEntity usuario);

    void sendEmail (SimpleMailMessage msg);

    void sendNewPasswordEmail (UsuarioEntity usuario, String newPass);

}

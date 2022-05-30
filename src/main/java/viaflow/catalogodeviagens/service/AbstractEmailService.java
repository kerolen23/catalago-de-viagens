package viaflow.catalogodeviagens.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import viaflow.catalogodeviagens.entities.UsuarioEntity;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    protected SimpleMailMessage prepareSimpleMailMessageFromUsuario(UsuarioEntity usuario) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(usuario.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Usuário cadastrado com Sucesso! ");
        sm.setText(usuario.toString());
        return sm;
    }

    @Override
    public void sendNewPasswordEmail(UsuarioEntity usuario, String newPass) {
        SimpleMailMessage sm = prepareSimpleNewPasswordEmail(usuario, newPass);
        sendEmail(sm);

    }

    protected SimpleMailMessage prepareSimpleNewPasswordEmail(UsuarioEntity usuario, String newPass) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setTo(usuario.getEmail());
        sm.setFrom(sender);
        sm.setSubject("Solicitação de nova senha ");
        sm.setText("Nova senha: " + newPass);
        return sm;
    }

}

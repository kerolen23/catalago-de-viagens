package viaflow.catalogodeviagens.service;

import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class SmtpEmailService extends AbstractEmailService{

    @Autowired
    private MailSender mailSender;

    private static final Logger Log = LoggerFactory.getLogger(SmtpEmailService.class);

    @Override
    public void sendEmail(SimpleMailMessage msg) {
        Log.info("Envio de email ");
        mailSender.send(msg);
        Log.info("Email enviado ");

    }

}

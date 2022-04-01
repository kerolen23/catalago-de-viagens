package papler.projetologin.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MockEmailService {

    private static final Logger Log = LoggerFactory.getLogger(MockEmailService.class);


    public void sendEmail(SimpleMailMessage msg) {
        Log.info("Simulando envio de email");
        Log.info(msg.toString());
        Log.info("Email enviado");

    }
}

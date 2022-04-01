package papler.projetologin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import papler.projetologin.service.EmailService;
import papler.projetologin.service.SmtpEmailService;

import java.text.ParseException;

@Profile("dev")
@Configuration
public class DevConfig {


        @Bean
        public EmailService emailService() {
            return new SmtpEmailService();
        }

}

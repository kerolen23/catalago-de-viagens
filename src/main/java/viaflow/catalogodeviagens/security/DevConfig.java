package viaflow.catalogodeviagens.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import viaflow.catalogodeviagens.service.EmailService;
import viaflow.catalogodeviagens.service.SmtpEmailService;

@Profile("dev")
@Configuration
public class DevConfig {


        @Bean
        public EmailService emailService() {
            return new SmtpEmailService();
        }

}

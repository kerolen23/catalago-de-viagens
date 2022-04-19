package papler.projetologin;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import papler.projetologin.service.S3Service;

@SpringBootApplication
public class ProjetoLoginApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ProjetoLoginApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

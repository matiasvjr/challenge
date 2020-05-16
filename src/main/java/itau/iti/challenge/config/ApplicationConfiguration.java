package itau.iti.challenge.config;

import itau.iti.challenge.service.PasswordService;
import itau.iti.challenge.service.impl.PasswordServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PasswordService passwordService() {
        return new PasswordServiceImpl();
    }
}

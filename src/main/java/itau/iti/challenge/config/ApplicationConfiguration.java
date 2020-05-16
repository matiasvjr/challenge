package itau.iti.challenge.config;

import itau.iti.challenge.password.service.PasswordService;
import itau.iti.challenge.password.service.impl.PasswordServiceImpl;
import itau.iti.challenge.password.validation.impl.MinimumSizeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    private static final int PASSWORD_MINIMUM_SIZE = 9;

    @Bean
    public PasswordService passwordService() {
        final MinimumSizeValidator minimumSizeValidator = new MinimumSizeValidator(PASSWORD_MINIMUM_SIZE);

        return new PasswordServiceImpl(minimumSizeValidator);
    }
}

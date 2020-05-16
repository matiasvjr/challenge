package itau.iti.challenge.config;

import itau.iti.challenge.password.service.PasswordService;
import itau.iti.challenge.password.service.impl.PasswordServiceImpl;
import itau.iti.challenge.password.validation.Validator;
import itau.iti.challenge.password.validation.impl.AtLeastOneValidator;
import itau.iti.challenge.password.validation.impl.CompositeValidator;
import itau.iti.challenge.password.validation.impl.MinimumSizeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ApplicationConfiguration {

    private static final int PASSWORD_MINIMUM_SIZE = 9;
    private static final String PASSWORD_DIGIT_ALPHABET = "0123456789";
    private static final String PASSWORD_LOWER_CASE_ALPHABET = "abcdefghijklmnopqrstuvwxyzç";
    private static final String PASSWORD_UPPER_CASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZÇ";
    private static final String PASSWORD_SPECIAL_CHARACTERS_ALPHABET = "\"'!@#$%*()_-+`{}[]~^<>,.:;?/\\|";

    @Bean
    public PasswordService passwordService() {
        final Validator validator = buildValidator();

        return new PasswordServiceImpl(validator);
    }

    private Validator buildValidator() {
        final List<Validator> validators = Arrays.asList(
                new MinimumSizeValidator(PASSWORD_MINIMUM_SIZE),
                new AtLeastOneValidator(PASSWORD_DIGIT_ALPHABET),
                new AtLeastOneValidator(PASSWORD_LOWER_CASE_ALPHABET),
                new AtLeastOneValidator(PASSWORD_UPPER_CASE_ALPHABET),
                new AtLeastOneValidator(PASSWORD_SPECIAL_CHARACTERS_ALPHABET)
        );

        return new CompositeValidator(validators);
    }
}

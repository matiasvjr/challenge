package itau.iti.challenge.config;

import itau.iti.challenge.password.service.PasswordService;
import itau.iti.challenge.password.service.impl.PasswordServiceImpl;
import itau.iti.challenge.password.validation.Validator;
import itau.iti.challenge.password.validation.impl.AtLeastOneValidator;
import itau.iti.challenge.password.validation.impl.CompositeValidator;
import itau.iti.challenge.password.validation.impl.MinimumSizeValidator;
import itau.iti.challenge.password.validation.impl.NonRepeatingValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ApplicationConfiguration {

    @Value("${password.minimum.size}")
    private int passwordMinimumSize;

    @Value("${password.digit.alphabet}")
    private String passwordDigitAlphabet;

    @Value("${password.lower.case.alphabet}")
    private String passwordLowerCaseAlphabet;

    @Value("${password.upper.case.alphabet}")
    private String passwordUpperCaseAlphabet;

    @Value("${password.special.characters.alphabet}")
    private String passwordSpecialCharactersAlphabet;

    @Bean
    public PasswordService passwordService() {
        final Validator validator = buildValidator();

        return new PasswordServiceImpl(validator);
    }

    private Validator buildValidator() {
        final List<Validator> validators = Arrays.asList(
                new MinimumSizeValidator(passwordMinimumSize),
                new AtLeastOneValidator(passwordDigitAlphabet),
                new AtLeastOneValidator(passwordLowerCaseAlphabet),
                new AtLeastOneValidator(passwordUpperCaseAlphabet),
                new AtLeastOneValidator(passwordSpecialCharactersAlphabet),
                new NonRepeatingValidator()
        );

        return new CompositeValidator(validators);
    }
}

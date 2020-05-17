package itau.iti.challenge.password.service.impl;

import itau.iti.challenge.password.validation.Validator;
import itau.iti.challenge.password.validation.impl.AtLeastOneValidator;
import itau.iti.challenge.password.validation.impl.CompositeValidator;
import itau.iti.challenge.password.validation.impl.MinimumSizeValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordServiceImplTest {

    private static final int PASSWORD_MINIMUM_SIZE = 9;
    private static final String PASSWORD_DIGIT_ALPHABET = "0123456789";
    private static final String PASSWORD_LOWER_CASE_ALPHABET = "abcdefghijklmnopqrstuvwxyzç";
    private static final String PASSWORD_UPPER_CASE_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZÇ";
    private static final String PASSWORD_SPECIAL_CHARACTERS_ALPHABET = "\"'!@#$%*()_-+`{}[]~^<>,.:;?/\\|";

    private PasswordServiceImpl passwordService;

    @BeforeEach
    void init() {
        final List<Validator> validators = Arrays.asList(
                new MinimumSizeValidator(PASSWORD_MINIMUM_SIZE),
                new AtLeastOneValidator(PASSWORD_DIGIT_ALPHABET),
                new AtLeastOneValidator(PASSWORD_LOWER_CASE_ALPHABET),
                new AtLeastOneValidator(PASSWORD_UPPER_CASE_ALPHABET),
                new AtLeastOneValidator(PASSWORD_SPECIAL_CHARACTERS_ALPHABET)
        );

        final Validator validator = new CompositeValidator(validators);

        passwordService = new PasswordServiceImpl(validator);
    }

    @Test
    void test_isValid_ShouldBeTrue_WhenMetTheCriteria() {
        final String password = "AbTp9!fok";
        final boolean result = passwordService.isValid(password);

        assertTrue(result, "Should be true!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenNull() {
        final String password = null;
        final boolean result = passwordService.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenEmpty() {
        final String password = "";
        final boolean result = passwordService.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenShorterThan9Characters() {
        final String password = "AbTp9!fo";
        final boolean result = passwordService.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenThereIsNoDigit() {
        final String password = "AbTpX!fok";
        final boolean result = passwordService.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenThereIsNoCapitalLetter() {
        final String password = "abtp9!fok";
        final boolean result = passwordService.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenThereIsNoLowerCase() {
        final String password = "ABTP9!FOK";
        final boolean result = passwordService.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenThereIsNoSpecialCharacter() {
        final String password = "AbTp9Xfok";
        final boolean result = passwordService.isValid(password);

        assertFalse(result, "Should be false!");
    }

}
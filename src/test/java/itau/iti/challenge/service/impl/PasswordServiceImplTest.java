package itau.iti.challenge.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordServiceImplTest {

    private PasswordServiceImpl passwordService;

    @BeforeEach
    void init() {
        passwordService = new PasswordServiceImpl();
    }

    @Test
    void test_isValid_ShouldBeTrue_WhenMetTheCriteria() {
        final String password = "AbTp9!fok";
        final boolean result = passwordService.isValid(password);

        assertTrue(result, "Should be true!");
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
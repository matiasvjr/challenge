package itau.iti.challenge.password.validation.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NonRepeatingValidatorTest {

    private NonRepeatingValidator nonRepeatingValidator;

    @BeforeEach
    void init() {
        nonRepeatingValidator = new NonRepeatingValidator();
    }

    @Test
    void test_isValid_ShouldBeTrue_WhenThereIsNoRepeatingCharacters() {
        final String password = "AbTp9!fokq";
        final boolean result = nonRepeatingValidator.isValid(password);

        assertTrue(result, "Should be true!");
    }

    @Test
    void test_isValid_ShouldBeTrue_WhenEmpty() {
        final String password = "";
        final boolean result = nonRepeatingValidator.isValid(password);

        assertTrue(result, "Should be true!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenNull() {
        final String password = null;
        final boolean result = nonRepeatingValidator.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenThereIsRepeatingCharacters() {
        final String password = "AbTp9!fokqq";
        final boolean result = nonRepeatingValidator.isValid(password);

        assertFalse(result, "Should be false!");
    }
}

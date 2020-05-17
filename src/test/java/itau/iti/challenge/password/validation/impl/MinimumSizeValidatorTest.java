package itau.iti.challenge.password.validation.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MinimumSizeValidatorTest {

    private MinimumSizeValidator minimumSizeValidator;

    @BeforeEach
    void init() {
        minimumSizeValidator = new MinimumSizeValidator(9);
    }

    @Test
    void test_isValid_ShouldBeTrue_WhenSizeIs9() {
        final String password = "AbTp9!fok";
        final boolean result = minimumSizeValidator.isValid(password);

        assertTrue(result, "Should be true!");
    }

    @Test
    void test_isValid_ShouldBeTrue_WhenSizeIsGreaterThan9() {
        final String password = "AbTp9!fokqwert";
        final boolean result = minimumSizeValidator.isValid(password);

        assertTrue(result, "Should be true!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenNull() {
        final String password = null;
        final boolean result = minimumSizeValidator.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenEmpty() {
        final String password = "";
        final boolean result = minimumSizeValidator.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenSizeIsLowerThan9() {
        final String password = "AbTp9!fo";
        final boolean result = minimumSizeValidator.isValid(password);

        assertFalse(result, "Should be false!");
    }
}
package itau.iti.challenge.password.validation.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AtLeastOneValidatorTest {

    private AtLeastOneValidator atLeastOneValidator;

    @BeforeEach
    void init() {
        atLeastOneValidator = new AtLeastOneValidator("ABC");
    }

    @Test
    void test_isValid_ShouldBeTrue_WhenOneCharacterBelongsToTheAlphabet() {
        final String password = "AbTp9!fok";
        final boolean result = atLeastOneValidator.isValid(password);

        assertTrue(result, "Should be true!");
    }

    @Test
    void test_isValid_ShouldBeTrue_WhenManyCharactersBelongsToTheAlphabet() {
        final String password = "AbTp9!fokBC";
        final boolean result = atLeastOneValidator.isValid(password);

        assertTrue(result, "Should be true!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenNull() {
        final String password = null;
        final boolean result = atLeastOneValidator.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenEmpty() {
        final String password = "";
        final boolean result = atLeastOneValidator.isValid(password);

        assertFalse(result, "Should be false!");
    }

    @Test
    void test_isValid_ShouldBeFalse_WhenNoCharactersBelongsToTheAlphabet() {
        final String password = "bTp9!fok";
        final boolean result = atLeastOneValidator.isValid(password);

        assertFalse(result, "Should be false!");
    }
}
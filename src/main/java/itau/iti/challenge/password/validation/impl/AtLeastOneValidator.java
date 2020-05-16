package itau.iti.challenge.password.validation.impl;

import itau.iti.challenge.password.validation.Validator;

import java.util.stream.IntStream;

public class AtLeastOneValidator implements Validator {

    private static final int NOT_OCCURRING_INDEX = -1;

    private final String alphabet;

    public AtLeastOneValidator(final String alphabet) {
        this.alphabet = alphabet;
    }

    @Override
    public boolean isValid(final String password) {
        return toStreamOfChars(password)
                .anyMatch(this::existsOnAlphabet);
    }

    private IntStream toStreamOfChars(final String text) {
        return text != null ? text.codePoints() : IntStream.empty();
    }

    private boolean existsOnAlphabet(final int charCode) {
        return alphabet.indexOf(charCode) > NOT_OCCURRING_INDEX;
    }
}

package itau.iti.challenge.password.validation.impl;

import itau.iti.challenge.password.validation.Validator;

public class NonRepeatingValidator implements Validator {

    @Override
    public boolean isValid(final String password) {
        if (password != null) {
            long distinctCharactersCount = countDistinctCharacters((password));

            return distinctCharactersCount == password.length();
        }

        return false;
    }

    private long countDistinctCharacters(final String text) {
        return text.codePoints()
                .distinct()
                .count();
    }
}

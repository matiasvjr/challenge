package itau.iti.challenge.password.validation.impl;

import itau.iti.challenge.password.validation.Validator;

public class MinimumSizeValidator implements Validator {

    private final int size;

    public MinimumSizeValidator(final int size) {
        this.size = size;
    }

    @Override
    public boolean isValid(final String password) {
        return password != null && password.length() >= size;
    }
}

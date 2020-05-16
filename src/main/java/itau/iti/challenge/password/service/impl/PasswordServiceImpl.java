package itau.iti.challenge.password.service.impl;

import itau.iti.challenge.password.service.PasswordService;
import itau.iti.challenge.password.validation.Validator;

public class PasswordServiceImpl implements PasswordService {

    private final Validator validator;

    public PasswordServiceImpl(final Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean isValid(final String password) {
        return validator.isValid(password);
    }
}

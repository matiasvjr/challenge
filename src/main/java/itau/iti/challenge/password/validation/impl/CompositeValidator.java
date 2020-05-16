package itau.iti.challenge.password.validation.impl;

import itau.iti.challenge.password.validation.Validator;

import java.util.Collection;

public class CompositeValidator implements Validator {

    private final Collection<Validator> validators;

    public CompositeValidator(final Collection<Validator> validators) {
        this.validators = validators;
    }

    @Override
    public boolean isValid(final String password) {
        return validators.stream()
                .allMatch(validator -> validator.isValid(password));
    }
}

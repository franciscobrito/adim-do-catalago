package com.fullcycle.admin.catalago.domain.validation.handler;

import com.fullcycle.admin.catalago.domain.exceptions.DomainException;
import com.fullcycle.admin.catalago.domain.validation.Error;
import com.fullcycle.admin.catalago.domain.validation.ValidationHandler;

import java.util.List;

public class ThrowsValidationhandler implements ValidationHandler {
    @Override
    public ValidationHandler append(final Error anError) {
        throw DomainException.with(anError);
    }

    @Override
    public ValidationHandler append(final ValidationHandler anHandler) {
        throw DomainException.with(anHandler.getErrors());
    }

    @Override
    public ValidationHandler validate(Validation aValidation) {
        try {
            aValidation.validate();
        } catch (final Exception ex) {
            throw DomainException.with(new Error(ex.getMessage()));
        }

        return this;
    }

    @Override
    public List<Error> getErrors() {
        return List.of();
    }
}

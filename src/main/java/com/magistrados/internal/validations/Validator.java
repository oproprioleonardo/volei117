package com.magistrados.internal.validations;

import com.magistrados.internal.validations.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;

public abstract class Validator<T> {

    protected final List<FieldError> errors = new ArrayList<>();

    public abstract void validate(T object) throws ValidationException;

    protected void addError(String field, ErrorMessage message) {
        this.errors.add(new FieldError(field, message));
    }

    protected void throwPossibleErrors() throws ValidationException {
        if (!errors.isEmpty())
            throw new ValidationException(this.errors);
    }


}

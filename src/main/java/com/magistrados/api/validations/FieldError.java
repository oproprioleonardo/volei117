package com.magistrados.api.validations;

public record FieldError(String field, ErrorMessage message) {

    public String getMessage() {
        return this.message.getText(field);
    }
}

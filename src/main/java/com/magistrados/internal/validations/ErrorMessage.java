package com.magistrados.internal.validations;

public enum ErrorMessage {

    NOT_NULLABLE("O campo '{field}' não pode ser nulo ou vazio."),
    NOT_ID("O campo '{field}' não é um número de ID válido."),
    NOT_VALID_NUMBER("O campo '{field}' não é um número válido."),
    NOT_NEGATIVE_NUMBER("O campo '{field}' não pode ser um número nulo ou negativo."),
    NOT_VALID_PLAYER_NUMBER("O campo '{field}' não é um número inteiro no intervalo de 1-100."),
    NOT_VALID_DATE("O campo '{field}' não possui uma data válida (dd/MM/yyyy)."),
    NOT_VALID_TIME("O campo '{field}' não possui um horário válido (HH:mm)."),
    NOT_REPEATABLE("Os campos '{field}' estão idênticos.");

    private final String text;

    ErrorMessage(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getText(String field) {
        return text.replace("{field}", field);
    }
}

package com.magistrados.api.validations.exceptions;

import com.magistrados.api.validations.FieldError;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ValidationException extends RuntimeException {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ValidationException.class);
    private final List<FieldError> errors;

    public ValidationException(List<FieldError> errors) {
        super("Não foi possível validar os dados enviados.");
        this.errors = errors;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void printOnFile() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        final Date now = new Date();
        final String name = "error_validation_" + dateFormat.format(now) + ".txt";
        final String errorList = this.errors.stream().map(fieldError -> " - " + fieldError.getMessage() + "\n").collect(Collectors.joining());
        final String content = this.getMessage() + "\n\nValidações que deram erro:\n" + errorList;

        try {
            final FileWriter fileWriter = new FileWriter(name);
            final BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.write(content);
            writer.close();
            log.info("Arquivo criado com sucesso: " + name);
        } catch (IOException e) {
            log.error("Ocorreu um erro ao criar o arquivo: " + e.getMessage());
        }
    }
}

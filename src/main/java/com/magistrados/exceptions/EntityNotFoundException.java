package com.magistrados.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String id, String entity) {
        super("A entidade " + entity + " não foi encontrada com o identificador " + id);
    }
}

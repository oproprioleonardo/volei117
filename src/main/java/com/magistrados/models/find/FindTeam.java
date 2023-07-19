package com.magistrados.models.find;

public record FindTeam(
        String id,
        String nome
) {

    public Long getId() {
        return Long.valueOf(this.id);
    }

}

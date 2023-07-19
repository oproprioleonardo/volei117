package com.magistrados.models.remove;

public record RemovePlayer(
        String id,
        String id_time,
        String numero
) {

    public Long getId() {
        return Long.valueOf(id);
    }

    public Long getIdTime() {
        return Long.valueOf(id_time);
    }

    public Integer getNumero() {
        return Integer.valueOf(numero);
    }

}

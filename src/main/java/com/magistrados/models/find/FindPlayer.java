package com.magistrados.models.find;

public record FindPlayer(
        String id,
        String id_time,
        String numero,
        String nome
) {

    public Long getId() {
        return Long.valueOf(id);
    }

    public Long getIdTime() {
        return Long.valueOf(this.id_time);
    }

    public Integer getNumero() {
        return Integer.valueOf(this.numero);
    }


}

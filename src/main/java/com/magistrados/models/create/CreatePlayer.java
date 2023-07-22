package com.magistrados.models.create;

public record CreatePlayer(
        String nome,
        String timeId,
        String numeroJogador,
        String quantidadeBloqueios,
        String quantidadeSaques,
        String quantidadeDefesas,
        String quantidadePontos,
        String partidasJogadas
) {

    public String getNome() {
        return this.nome;
    }

    public Long getIdTime() {
        if (timeId == null || timeId.isBlank()) return null;
        return Long.valueOf(this.timeId);
    }

    public Integer getNumero() {
        return Integer.valueOf(this.numeroJogador);
    }

    public Integer getBloqueios() {
        return Integer.valueOf(this.quantidadeBloqueios);
    }

    public Integer getDefesas() {
        return Integer.valueOf(this.quantidadeDefesas);
    }

    public Integer getPontos() {
        return Integer.valueOf(this.quantidadePontos);
    }

    public Integer getSaques() {
        return Integer.valueOf(this.quantidadeSaques);
    }

    public Integer getPartidasJogadas() {
        return Integer.valueOf(this.partidasJogadas);
    }


}

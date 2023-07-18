package com.magistrados.models.edit;

public record EditPlayer(
        String nome,
        String timeId,
        String numeroJogador,
        String quantidadeBloqueios,
        String quantidadeSaques,
        String quantidadeDefesas,
        String quantidadePontos
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

}

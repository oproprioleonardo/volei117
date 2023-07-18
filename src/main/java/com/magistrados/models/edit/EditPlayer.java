package com.magistrados.models.edit;

public record EditPlayer(
        String id,
        String nome,
        String timeId,
        String numeroJogador,
        String quantidadeBloqueios,
        String quantidadeSaques,
        String quantidadeDefesas,
        String quantidadePontos
) {
}

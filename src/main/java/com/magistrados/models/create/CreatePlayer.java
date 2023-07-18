package com.magistrados.models.create;

public record CreatePlayer(
        String nome,
        String timeId,
        String numeroJogador,
        String quantidadeBloqueios,
        String quantidadeSaques,
        String quantidadeDefesas,
        String quantidadePontos
) {
}

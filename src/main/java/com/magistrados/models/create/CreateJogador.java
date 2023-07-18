package com.magistrados.models.create;

public record CreateJogador(
        String nome,
        String timeId,
        String numeroJogador,
        String quantidadeBloqueios,
        String quantidadeSaques,
        String quantidadeDefesas,
        String quantidadePontos
) {
}

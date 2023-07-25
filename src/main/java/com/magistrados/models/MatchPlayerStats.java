package com.magistrados.models;

public class MatchPlayerStats {

    private Long Id;
    private Long playerId;
    private Long partidaId;
    private Integer quantidadeBloqueios = 0;
    private Integer quantidadeSaques = 0;
    private Integer quantidadeDefesas = 0;
    private Integer quantidadePontos = 0;

    public MatchPlayerStats() {
    }

    public MatchPlayerStats(Long Id, Long playerId, Long partidaId) {
        this.Id = Id;
        this.playerId = playerId;
        this.partidaId = partidaId;
    }

    public boolean isCreated() {
        return this.Id != null && this.Id > 0;
    }

    public void addPonto() {
        this.quantidadePontos++;
    }

    public void addSaque() {
        this.quantidadeSaques++;
    }

    public void addDefesa() {
        this.quantidadeDefesas++;
    }

    public void addBloqueio() {
        this.quantidadeBloqueios++;
    }

    public void removePonto() {
        if (quantidadePontos > 0)
            this.quantidadePontos--;
    }

    public void removeSaque() {
        if (quantidadeSaques > 0) this.quantidadeSaques--;
    }

    public void removeDefesa() {
        if (quantidadeDefesas > 0) this.quantidadeDefesas--;
    }

    public void removeBloqueio() {
        if (quantidadeBloqueios > 0) this.quantidadeBloqueios--;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Long getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(Long partidaId) {
        this.partidaId = partidaId;
    }

    public int getQuantidadePontos() {
        return quantidadePontos;
    }

    public void setQuantidadePontos(int pontos) {
        quantidadePontos = pontos;
    }

    public int getQuantidadeSaques() {
        return quantidadeSaques;
    }

    public void setQuantidadeSaques(int saques) {
        quantidadeSaques = saques;
    }

    public int getQuantidadeDefesas() {
        return quantidadeDefesas;
    }

    public void setQuantidadeDefesas(int defesas) {
        quantidadeSaques = defesas;
    }

    public int getQuantidadeBloqueios() {
        return quantidadeBloqueios;
    }

    public void setQuantidadeBloqueios(int bloqueios) {
        quantidadeBloqueios = bloqueios;
    }
}

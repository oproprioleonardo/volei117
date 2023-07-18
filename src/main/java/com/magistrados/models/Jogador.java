package com.magistrados.models;

import com.magistrados.models.create.CreatePlayer;
import com.magistrados.models.edit.EditPlayer;

import java.util.HashSet;
import java.util.Set;

public class Jogador {
    private Long id;
    private String nome;
    private Long timeId;
    private Integer numeroJogador;
    private Integer quantidadeBloqueios = 0;
    private Integer quantidadeSaques = 0;
    private Integer quantidadeDefesas = 0;
    private Integer quantidadePontos = 0;
    private Time time;
    private Set<MatchPlayerStats> matchPlayerStats = new HashSet<>();

    public Jogador() {
    }

    public Jogador(Long id, String nome, Time time, Integer numeroJogador, Integer quantidadeBloqueios, Integer quantidadeSaques, Integer quantidadeDefesas, Integer quantidadePontos) {
        this.id = id;
        this.nome = nome;
        this.time = time;
        this.numeroJogador = numeroJogador;
        this.quantidadeBloqueios = quantidadeBloqueios;
        this.quantidadeSaques = quantidadeSaques;
        this.quantidadeDefesas = quantidadeDefesas;
        this.quantidadePontos = quantidadePontos;
    }

    public MatchPlayerStats matchStats(Long matchId){
        return this.matchPlayerStats.stream()
                .filter(x->x.getPartidaId().equals(matchId))
                .findFirst()
                .orElse(null);
    }

    public boolean isCreated() {
        return this.id != null && this.id > 0;
    }

    public Long getTimeId() {
        return this.timeId;
    }

    public void setTimeId(Long timeId) {
        this.timeId = timeId;
    }

    public void addPonto() {
        this.quantidadePontos++;
    }

    public void addPontos(int pontos){this.quantidadePontos+= pontos;}

    public void addSaque() {
        this.quantidadeSaques++;
    }

    public void addSaques(int saques) { this.quantidadeSaques += saques; }

    public void addDefesa() {
        this.quantidadeDefesas++;
    }

    public void addDefesas(int defesas) { this.quantidadeDefesas += defesas; }

    public void addBloqueio() {
        this.quantidadeBloqueios++;
    }

    public void addBloqueios(int bloqueios){ this.quantidadeBloqueios += bloqueios; }

    public void removePonto() {
        this.quantidadePontos--;
    }

    public void removePontos(int pontos) { this.quantidadePontos -= pontos; }

    public void removeSaque() {
        this.quantidadeSaques--;
    }

    public void removeSaques(int saques){ this.quantidadeSaques -= saques; }

    public void removeDefesa() { this.quantidadeDefesas--; }

    public void removeDefesas(int defesas) { this.quantidadeDefesas -= defesas; }

    public void removeBloqueio() {
        this.quantidadeBloqueios--;
    }

    public void removeBloqueios(int bloqueios) { this.quantidadeBloqueios -= bloqueios; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
        if (time != null)
            this.timeId = time.getId();
    }

    public Integer getNumeroJogador() {
        return numeroJogador;
    }

    public void setNumeroJogador(Integer numeroJogador) {
        this.numeroJogador = numeroJogador;
    }

    public Integer getQuantidadeBloqueios() {
        return quantidadeBloqueios;
    }

    public void setQuantidadeBloqueios(Integer quantidadeBloqueios) {
        this.quantidadeBloqueios = quantidadeBloqueios;
    }

    public Integer getQuantidadeSaques() {
        return quantidadeSaques;
    }

    public void setQuantidadeSaques(Integer quantidadeSaques) {
        this.quantidadeSaques = quantidadeSaques;
    }

    public Integer getQuantidadeDefesas() {
        return quantidadeDefesas;
    }

    public void setQuantidadeDefesas(Integer quantidadeDefesas) {
        this.quantidadeDefesas = quantidadeDefesas;
    }

    public Integer getQuantidadePontos() {
        return quantidadePontos;
    }

    public void setQuantidadePontos(Integer quantidadePontos) {
        this.quantidadePontos = quantidadePontos;
    }

    public Set<MatchPlayerStats> getMatchPlayerStats() {
        return matchPlayerStats;
    }

    public void setMatchPlayerStats(Set<MatchPlayerStats> matchPlayerStats) {
        this.matchPlayerStats = matchPlayerStats;
    }

    public void addMatchPlayerStats(MatchPlayerStats matchPlayerStats){
        this.matchPlayerStats.add(matchPlayerStats);
    }
}

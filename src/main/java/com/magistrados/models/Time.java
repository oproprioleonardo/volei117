package com.magistrados.models;

import java.util.HashSet;
import java.util.Set;

public class Time {
    private Long id;
    private String nomeTime;
    private Integer vitorias;
    private Integer derrotas;
    private Set<Jogador> jogadores = new HashSet<>();

    public Time(){
    }

    public Time(Long id, String nomeTime, Integer vitorias, Integer derrotas){
        this.id = id;
        this.nomeTime = nomeTime;
        this.vitorias = vitorias;
        this.derrotas = derrotas;
    }

    public boolean isCreated() {
        return this.id != null && this.id != 0;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public void setNomeTime(String nomeTime){
        this.nomeTime = nomeTime;
    }

    public String getNomeTime(){
        return this.nomeTime;
    }

    public Set<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(Set<Jogador> jogadores) {
        this.jogadores = jogadores;
    }
}

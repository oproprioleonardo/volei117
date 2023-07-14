package com.magistrados.services;

import com.magistrados.api.repositories.JogadorRepository;
import com.magistrados.api.repositories.TimeRepository;
import com.magistrados.models.Jogador;
import com.magistrados.models.Time;

import java.util.Set;

public class JogadorService {

    private final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public void registrarJogador(Jogador jogador) {
        this.jogadorRepository.create(jogador);
    }

    public void salvarJogador(Jogador jogador) {
        this.jogadorRepository.save(jogador);
    }

    public Jogador buscarJogador(String nome) {
        return this.jogadorRepository.findByName(nome);
    }

    public Set<Jogador> buscarJogadores(Long idTime) {
        return this.jogadorRepository.findAllByTeamId(idTime);
    }

}

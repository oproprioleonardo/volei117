package com.magistrados.services;

import com.magistrados.api.repositories.JogadorRepository;
import com.magistrados.models.Jogador;

import java.util.Set;

public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final MatchPlayerStatsService matchPlayerStatsService;

    public JogadorService(JogadorRepository jogadorRepository, MatchPlayerStatsService matchPlayerStatsService) {
        this.jogadorRepository = jogadorRepository;
        this.matchPlayerStatsService = matchPlayerStatsService;
    }

    public void registrarJogador(Jogador jogador) {
        this.jogadorRepository.create(jogador);
        jogador.getMatchPlayerStats().forEach(matchPlayerStatsService::createMatchPlayerStats);
    }

    public void salvarJogador(Jogador jogador) {
        this.jogadorRepository.save(jogador);
        jogador.getMatchPlayerStats().forEach(matchPlayerStatsService::saveMatchPlayerStats);
    }

    public Jogador buscarJogador(String nome) {
        final Jogador jogador = this.jogadorRepository.findByName(nome);
        jogador.setMatchPlayerStats(this.matchPlayerStatsService.findByPlayerId(jogador.getId()));
        return jogador;
    }

    public Jogador buscarJogadorPorNumero(Long timeId, Integer numero){
        final Jogador jogador = this.jogadorRepository.findByNumber(timeId, numero);
        if(jogador.isCreated())
            throw new EntityNotFoundException(
                    jogador.getTimeId().toString() + ", " + jogador.getNumeroJogador().toString(),
                    "jogador"
            );
        return jogador;
    }

    public Set<Jogador> buscarJogadores(Long idTime) {
        Set<Jogador> allByTeamId = this.jogadorRepository.findAllByTeamId(idTime);
        allByTeamId.forEach(jogador -> jogador.setMatchPlayerStats(this.matchPlayerStatsService.findByPlayerId(jogador.getId())));
        return allByTeamId;
    }

}

package com.magistrados.services;

import com.magistrados.api.repositories.PartidaRepository;
import com.magistrados.models.Jogador;
import com.magistrados.models.Partida;

import java.util.HashSet;
import java.util.Objects;
import java.util.stream.Stream;

public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final GameSetService gameSetService;
    private final TimeService timeService;
    private final MatchPlayerStatsService matchPlayerStatsService;

    public PartidaService(PartidaRepository partidaRepository, GameSetService gameSetService, TimeService timeService, MatchPlayerStatsService matchPlayerStatsService) {
        this.partidaRepository = partidaRepository;
        this.gameSetService = gameSetService;
        this.timeService = timeService;
        this.matchPlayerStatsService = matchPlayerStatsService;
    }

    public Partida buscarPartida(Long id) {
        final Partida partida = this.partidaRepository.findById(id);
        partida.setGameSets(this.gameSetService.buscarSets(id));
        partida.setTimeA(this.timeService.buscarTime(partida.getIdTimeA()));
        partida.setTimeB(this.timeService.buscarTime(partida.getIdTimeB()));
        return partida;
    }

    public void salvarPartida(Partida partida) {
        partida.getGameSets().forEach(this.gameSetService::salvarSet);
        this.partidaRepository.save(partida);
        this.timeService.salvarTime(partida.getTimeA());
        this.timeService.salvarTime(partida.getTimeB());
    }

    public void deletarPartida(Partida partida) {
        partida.getGameSets().forEach(this.gameSetService::deletarSet);
        partida.setGameSets(new HashSet<>());
        partida.getJogadores().stream().map(Jogador::getMatchPlayerStats)
                .map(matchPlayerStats ->
                        matchPlayerStats.stream()
                        .filter(matchPlayerStats1 -> Objects.equals(matchPlayerStats1.getPartidaId(), partida.getId())))
                .map(Stream::findAny)
                .forEach(matchPlayerStats -> matchPlayerStats
                        .ifPresent(matchPlayerStats1 ->
                                this.matchPlayerStatsService.deleteMatchPlayerStats(matchPlayerStats1.getId()))
                );
        this.partidaRepository.deleteById(partida.getId());
    }
}

package com.magistrados.services;

import com.magistrados.api.repositories.PartidaRepository;
import com.magistrados.models.Partida;

import java.util.HashSet;

public class PartidaService {

    private final PartidaRepository partidaRepository;
    private final GameSetService gameSetService;
    private final TimeService timeService;

    public PartidaService(PartidaRepository partidaRepository, GameSetService gameSetService, TimeService timeService) {
        this.partidaRepository = partidaRepository;
        this.gameSetService = gameSetService;
        this.timeService = timeService;
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
    }
}

package com.magistrados.managers;

import com.magistrados.managers.enums.TeamID;
import com.magistrados.models.GameSet;
import com.magistrados.models.Jogador;
import com.magistrados.models.MatchPlayerStats;
import com.magistrados.models.Partida;
import com.magistrados.services.*;

import java.time.LocalDateTime;

public class MatchManager {

    private static final int POINTS_MAX = 25;
    private final PartidaService partidaService;
    private final TimeService timeService;
    private final MatchPlayerStatsService statsService;
    private Partida partida;
    private int setNum = 1;
    private GameSet currentSet;

    public MatchManager(PartidaService partidaService, TimeService timeService, MatchPlayerStatsService statsService) {
        this.partidaService = partidaService;
        this.timeService = timeService;
        this.statsService = statsService;
    }

    public void iniciarPartida(Long timeAId, Long timeBId, String local, LocalDateTime dataHora) {
        this.partida = new Partida();
        partida.setLocal(local);
        partida.setDateTime(dataHora);
        partida.setTimeA(this.timeService.buscarTime(timeAId));
        partida.setTimeB(this.timeService.buscarTime(timeBId));
        this.partidaService.salvarPartida(partida);

        partida.getJogadores().forEach(jogador -> {
            final MatchPlayerStats playerStats = new MatchPlayerStats();
            playerStats.setPlayerId(jogador.getId());
            playerStats.setPartidaId(partida.getId());

            jogador.addMatchPlayerStats(playerStats);
            this.statsService.saveMatchPlayerStats(playerStats);
        });

        this.currentSet = partida.getSetByOrder(this.setNum);

    }

    public void cancelarPartida() {
        this.partidaService.deletarPartida(partida);
        // todo lógica para cancelar partida
    }

    public boolean nextSetAndCheckGameWon(TeamID teamID) {
        this.currentSet.finalizarSet(teamID.getId());
        switch (teamID) {
            case TIME_A -> this.partida.addSetsA();
            case TIME_B -> this.partida.addSetsB();
        }
        this.currentSet = partida.getSetByOrder(++this.setNum);
        if (this.currentSet != null && Math.abs(this.partida.getSetsA() - this.partida.getSetsB()) < 2) {
            this.currentSet.setIniciado(true);
            return true;
        }
        return false;
    }

    public void gameSetWon(TeamID teamID) {
        if (!this.nextSetAndCheckGameWon(teamID)) {
            this.partida.finalizarPartida(teamID);
            this.partidaService.salvarPartida(partida);
        }
    }

    public void adicionarPontoTimeA() {
        currentSet.addPontosTimeA();
        if (this.checkWonSet(TeamID.TIME_A)) this.gameSetWon(TeamID.TIME_A);
    }

    public void adicionarPontoTimeB() {
        currentSet.addPontosTimeB();
        if (this.checkWonSet(TeamID.TIME_B)) this.gameSetWon(TeamID.TIME_B);
    }


    public int pointsDifference() {
        return Math.abs(this.currentSet.getPontosTimeA() - this.currentSet.getPontosTimeB());
    }

    private boolean checkWonSet(TeamID teamID) {
        switch (teamID) {
            case TIME_A -> {
                return !checkPostpone() && currentSet.getPontosTimeA() >= POINTS_MAX;
            }
            case TIME_B -> {
                return !checkPostpone() && currentSet.getPontosTimeB() >= POINTS_MAX;
            }
            default -> {
                return false;
            }
        }
    }

    private boolean checkPostpone() {
        return currentSet.getPontosTimeB() >= POINTS_MAX - 1 &&
                currentSet.getPontosTimeA() >= POINTS_MAX - 1 &&
                pointsDifference() != 2;
    }

    public void addSaque(Jogador jogador) {
        jogador.matchStats(this.partida.getId()).addSaque();
    }

    public void removeSaque(Jogador jogador){
        jogador.matchStats(this.partida.getId()).removeSaque();
    }

    public void addDefesa(Jogador jogador){
        jogador.matchStats(this.partida.getId()).addDefesa();
    }

    public void removeDefesa(Jogador jogador){
        jogador.matchStats(this.partida.getId()).removeDefesa();
    }

    public void addPonto(Jogador jogador){
        jogador.matchStats(this.partida.getId()).addPonto();
    }

    public void removePonto(Jogador jogador){
        jogador.matchStats(this.partida.getId()).removePonto();
    }

    public void addBloqueio(Jogador jogador){
        jogador.matchStats(this.partida.getId()).addBloqueio();
    }

    public void removeBloqueio(Jogador jogador){
        jogador.matchStats(this.partida.getId()).removeBloqueio();
    }
}

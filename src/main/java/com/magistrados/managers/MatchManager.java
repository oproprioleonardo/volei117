package com.magistrados.managers;

import com.magistrados.managers.enums.TeamID;
import com.magistrados.models.GameSet;
import com.magistrados.models.Partida;
import com.magistrados.models.Time;

public class MatchManager {

    private static final int POINTS_MAX = 25;
    private Partida partida;
    private int setNum = 1;
    private GameSet currentSet;


    // time a
    // time b
    // local
    public void iniciarPartida(Time a, Time b, String local) {
        this.partida = new Partida();
        partida.setTimeA(a);
        partida.setTimeB(b);
        partida.setLocal(local);
        this.currentSet = partida.getSetByOrder(this.setNum);

    }

    public boolean nextSet(TeamID teamID) {
        this.currentSet.finalizarSet(teamID.getId());
        this.currentSet = partida.getSetByOrder(++this.setNum);
        if (this.currentSet != null) {
            this.currentSet.setIniciado(true);
            return true;
        }
        return false;
    }

    public void adicionarPontoTimeA() {
        currentSet.addPontosTimeA();
        if (this.checkWin(TeamID.TIME_A)) this.gameSetWon(TeamID.TIME_A);
    }

    public void adicionarPontoTimeB() {
        currentSet.addPontosTimeB();
        if(this.checkWin(TeamID.TIME_B)) this.gameSetWon(TeamID.TIME_B);
    }

    public void gameSetWon(TeamID teamID) {
        if (!this.nextSet(teamID)) {
            this.partida.finalizarPartida(teamID.getId());
            switch (teamID) {
                case TIME_A -> {
                    this.partida.getTimeA().addVitoria();
                    this.partida.getTimeB().addDerrota();
                }
                case TIME_B -> {
                    this.partida.getTimeB().addVitoria();
                    this.partida.getTimeA().addDerrota();
                }
            }
        }
    }

    public int pointsDifference() {
        return Math.abs(this.currentSet.getPontosTimeA() - this.currentSet.getPontosTimeB());
    }

    private boolean checkWin(TeamID teamID) {
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
}

package com.magistrados.graph.screens.match.models;

import com.magistrados.graph.notification.Notifications;
import com.magistrados.models.Partida;
import com.magistrados.services.PartidaService;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MatchJob {
    private PartidaService partidaService;
    private Partida partida;
    private Timer timer;

    public MatchJob(PartidaService partidaService, Partida partida) {
        this.partidaService = partidaService;
        this.partida = partida;
    }

    public void watch() {
        timer = new Timer();
        timer.schedule(new updateMatch(),
                0, // delay inicial de 0seg
                10000); // atualiza partida a cada 30seg
    }

    public void stopWatch() {
        timer.cancel();
    }

    class updateMatch extends TimerTask {
        @Override
        public void run() {
            if (partida.isFinalizada()) {
                cancel();
                return;
            }
            new Thread(() -> {
                partidaService.salvarPartida(partida);
                LoggerFactory.getLogger(MatchJob.class).info("Partida #" + partida.getId() + " foi salva.");
            }).start();

        }
    }
}

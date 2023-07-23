package com.magistrados.graph.screens.match;

import com.magistrados.graph.notification.Notifications;
import com.magistrados.models.Partida;
import com.magistrados.services.PartidaService;
import io.smallrye.mutiny.Uni;

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

    public void watch(){
        timer = new Timer();
        timer.schedule(new updateMatch(),
                30000, // delay inicial de 30seg
                30000); // atualiza partida a cada 30seg
    }

    public void stopWatch(){
        timer.cancel();
    }

    class updateMatch extends TimerTask {
        @Override
        public void run() {
            Notifications.info("Salvando partida...");
            final Uni<Void> emitter = Uni.createFrom().emitter((em) -> new Thread(() -> {
                partidaService.salvarPartida(partida);
                em.complete(null);
            }).start());

            emitter.subscribe().with(partida -> SwingUtilities.invokeLater(() -> {
                Notifications.info("Partida salva");
            }), failure -> {
                Notifications.error("Erro ao salvar partida");
            });
        }
    }
}

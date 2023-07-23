package com.magistrados.graph.screens.match;

import com.magistrados.graph.notification.Notifications;
import com.magistrados.models.Partida;
import com.magistrados.services.PartidaService;
import io.smallrye.mutiny.Uni;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class MatchViewer {
    private PartidaService partidaService;
    private Partida partida;
    private Timer timer;

    public MatchViewer(PartidaService partidaService, Partida partida) {
        this.partidaService = partidaService;
        this.partida = partida;
    }

    public void watch(){
        timer = new Timer();
        timer.schedule(new searchMatch(this.partida),
                30000, // delay inicial de 30seg
                30000); // atualiza partida a cada 30seg
    }

    public void stopWatch(){
        timer.cancel();
    }

    class searchMatch extends TimerTask {
        private final Partida partida;

        public searchMatch(Partida partida){
            this.partida = partida;
        }
        @Override
        public void run() {
            final Uni<Partida> emitter = Uni.createFrom().emitter((em) -> new Thread(() -> {
                final Partida partida = partidaService.buscarPartida(this.partida.getId());
                em.complete(partida);
            }).start());

            emitter.subscribe().with(partida -> SwingUtilities.invokeLater(() -> {
                Notifications.info("Partida buscada");
            }), failure -> {
                Notifications.error("Erro ao buscar partida");
            });
        }
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
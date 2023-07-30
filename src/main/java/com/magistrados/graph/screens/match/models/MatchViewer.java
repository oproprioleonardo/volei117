package com.magistrados.graph.screens.match.models;

import com.magistrados.graph.notification.Notifications;
import com.magistrados.graph.screens.start.MenuInicial;
import com.magistrados.models.Partida;
import com.magistrados.services.PartidaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Timer;
import java.util.TimerTask;

public abstract class MatchViewer extends JFrame {

    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MatchViewer.class);

    private PartidaService partidaService;
    private Partida partida;
    private Timer timer;

    public MatchViewer(PartidaService partidaService, Partida partida) {
        this.partidaService = partidaService;
        this.partida = partida;

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                stopWatch();
                dispose();
            }
        });
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.updateComponents();
        this.pack();
        watch();
    }

    public void watch() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
                           @Override
                           public void run() {
                               new Thread(() -> {
                                   partida = partidaService.buscarPartidaOtimizado(partida.getId());
                                   Notifications.info("Partida atualizada.");

                                   SwingUtilities.invokeLater(() -> {
                                       getContentPane().removeAll();
                                       updateComponents();
                                       pack();
                                   });
                               }).start();
                           }
                       },
                0, // delay inicial de 0seg
                15000); // atualiza partida a cada 30seg
    }

    public abstract void updateComponents();

    public Partida getPartida() {
        return partida;
    }

    public void stopWatch() {
        timer.cancel();
    }

}

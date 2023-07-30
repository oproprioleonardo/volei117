package com.magistrados.graph.screens.start;

import com.google.gson.GsonBuilder;
import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.CustomInputDialog;
import com.magistrados.graph.notification.Notifications;
import com.magistrados.graph.screens.match.MatchManagerFrame;
import com.magistrados.graph.screens.match.MatchViewerFrame;
import com.magistrados.graph.screens.player.PlayerManagerFrame;
import com.magistrados.graph.screens.team.TeamManagerFrame;
import com.magistrados.models.Partida;
import com.magistrados.services.*;
import com.magistrados.graph.screens.match.StartMatchRequestFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuInicial extends JFrame {

    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MenuInicial.class);

    private JPanel mainPanel;

    public MenuInicial(PartidaService partidaService, MatchPlayerStatsService statsService, JogadorService jogadorService, TimeService timeService, GameSetService gameSetService) throws HeadlessException {
        super("Menu Inicial");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        // Criando o painel principal com BorderLayout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Criando painel com box layout para ficar um botão debaixo do outro
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#171717"));
        // Criando botões
        this.createButton(buttonPanel, "Gerenciar Jogadores", e -> {
            final JFrame gerenciarJogadores = new PlayerManagerFrame(jogadorService);
            gerenciarJogadores.setVisible(true);

        }, false);
        this.createButton(buttonPanel, "Gerenciar Times", e -> {
            final JFrame gerenciarTimes = new TeamManagerFrame(timeService);
            gerenciarTimes.setVisible(true);

        }, true);
        this.createButton(buttonPanel, "Iniciar Partida", e -> {
            final JFrame startPartidasFrameRequests = new StartMatchRequestFrame(partidaService, timeService, statsService, gameSetService);
            startPartidasFrameRequests.setVisible(true);

        }, true);

        // solicitar partida e só poder visualizar caso esteja em andamento.

        this.createButton(buttonPanel, "Visualizar Partidas", e -> {


            final CustomInputDialog inputDialog = new CustomInputDialog(this);
            inputDialog.setVisible(true);

            final String inputText = inputDialog.getInputValue();
            new Thread(() -> {
                try {
                    final Partida partida = partidaService.buscarPartidaOtimizado(Long.valueOf(inputText));
                    if (partida.isFinalizada()) {
                        SwingUtilities.invokeLater(() -> {
                            final MatchViewerFrame matchViewerFrame = new MatchViewerFrame(partidaService, partida);
                            matchViewerFrame.setVisible(true);

                        });
                    } else {
                        Notifications.warning("Partida não foi finalizada");
                    }
                }catch (Exception er){
                    Notifications.error("Partida não encontrada");
                    //er.printStackTrace();
                }
            }).start();

        }, true);

        // Criando um painel de preenchimento com EmptyBorder
        JPanel paddingPanel = new JPanel(new BorderLayout());
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(100, 300, 100, 300));
        paddingPanel.add(buttonPanel, BorderLayout.CENTER);
        paddingPanel.setBackground(Color.decode("#171717"));

        // Adicionando o painel de preenchimento (com os botões) ao centro do painel principal
        mainPanel.add(paddingPanel, BorderLayout.CENTER);

        // Adicionando o painel principal ao frame
        this.getContentPane().add(mainPanel);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void createButton(JPanel panel, String text, ActionListener listener, boolean space) {
        if (space) panel.add(Box.createRigidArea(new Dimension(0, 50)));
        final DefaultButton button = new DefaultButton(text, listener);
        panel.add(button);
    }

}

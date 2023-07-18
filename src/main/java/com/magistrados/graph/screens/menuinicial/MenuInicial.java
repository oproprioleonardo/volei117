package com.magistrados.graph.screens.menuinicial;

import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.screens.managerplayers.ManagerPlayersFrame;
import com.magistrados.graph.screens.managertimes.ManagerTimesFrame;
import com.magistrados.graph.screens.partidas.StartPartidaRequestsFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MenuInicial extends JFrame {

    private JPanel mainPanel;

    public MenuInicial() throws HeadlessException {
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
            final JFrame gerenciarJogadores = new ManagerPlayersFrame();
            gerenciarJogadores.setVisible(true);

        }, false);
        this.createButton(buttonPanel, "Gerenciar Times", e -> {
            final JFrame gerenciarTimes = new ManagerTimesFrame();
            gerenciarTimes.setVisible(true);

        }, true);
        this.createButton(buttonPanel, "Iniciar Partida", e -> {
            final JFrame startPartidasFrameRequests = new StartPartidaRequestsFrame();
            startPartidasFrameRequests.setVisible(true);

        }, true);
        this.createButton(buttonPanel, "Visualizar Partidas", e -> {


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

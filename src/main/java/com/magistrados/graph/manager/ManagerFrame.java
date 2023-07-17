package com.magistrados.graph.manager;

import com.magistrados.graph.manager.buttons.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerFrame extends JFrame {

    private JPanel mainPanel;

    public ManagerFrame() throws HeadlessException {
        super("Vôlei - Gerenciador de partidas");
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





        }, false);
        this.createButton(buttonPanel, "Gerenciar Times", e -> {


        }, true);
        this.createButton(buttonPanel, "Gerenciar Partidas", e -> {


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

package com.magistrados.graph.managertimes;

import com.magistrados.graph.buttons.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerTimesFrame extends JFrame {
    private JPanel inputPanel;
    private JPanel buttonsPanel;

    public ManagerTimesFrame() throws HeadlessException {
        super("Gerenciador de Jogadores");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        // Criando painel com box layout para ficar um botão debaixo do outro
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#171717"));

        // Criando botões
        this.createButton(buttonPanel, "Adicionar Time", e -> {


        }, false);
        this.createButton(buttonPanel, "Visualizar Time", e -> {


        }, true);
        this.createButton(buttonPanel, "Editar Time", e -> {


        }, true);
        this.createButton(buttonPanel, "Remover Time", e -> {


        }, true);
        // Criando um painel de preenchimento com EmptyBorder
        JPanel paddingPanel = new JPanel(new BorderLayout());
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(100, 25, 100, 25));
        paddingPanel.add(buttonPanel, BorderLayout.CENTER);
        paddingPanel.setBackground(Color.decode("#171717"));

        // Adicionando o painel de preenchimento (com os botões) ao centro do painel principal
        buttonsPanel.add(paddingPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);

    }
    private void createButton(JPanel panel, String text, ActionListener listener, boolean space) {
        if (space) panel.add(Box.createRigidArea(new Dimension(0, 50)));
        final DefaultButton button = new DefaultButton(text, listener);
        panel.add(button);
    }

}

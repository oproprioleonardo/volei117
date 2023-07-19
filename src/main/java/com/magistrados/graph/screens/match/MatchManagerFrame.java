package com.magistrados.graph.screens.match;

import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;
import com.magistrados.managers.MatchManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MatchManagerFrame extends JFrame {
    private JPanel timeWPanel;
    private JPanel buttonsWPanel;
    private JPanel dadosPartidaPanel;
    private JPanel timeEPanel;
    private JPanel buttonsEPanel;
    private MatchManager matchManager;

    public MatchManagerFrame(MatchManager matchManager){
        super("Iniciar Partida - Jogo");
        this.matchManager = matchManager;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        initComponents();
        //empilha tudo
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void initComponents(){

    }

    private void createButton(JPanel panel, String text, ActionListener listener, boolean space) {
        if (space) panel.add(Box.createRigidArea(new Dimension(0, 50)));
        final DefaultButton button = new DefaultButton(text, listener);
        panel.add(button);
    }

    private DefaultInput createInput(int sizeX, int sizeY) {
        final DefaultInput input = new DefaultInput(sizeX, sizeY);
        return input;
    }
    private DefaultLabel createLabel(Font font, String text) {
        final DefaultLabel label = new DefaultLabel(font, text);
        return label;
    }


}

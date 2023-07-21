package com.magistrados.graph.screens.match;

import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;
import com.magistrados.managers.MatchManager;
import com.magistrados.models.Jogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MatchManagerFrame extends JFrame {

    private JPanel mainPanel;
    private JPanel timeAPanel;
    private JPanel headerAPanel;
    private JPanel buttonsAPanel;
    private JPanel footerAPanel;
    private JPanel dadosPartidaPanel;
    private JPanel headerDadosPanel;
    private JPanel setsContPanel;
    private JPanel setsViewerPanel;
    private JPanel footerDadosPanel;
    private JPanel timeBPanel;
    private JPanel headerBPanel;
    private JPanel buttonsBPanel;
    private JPanel footerBPanel;
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

        //Criando JPanels
        mainPanel = new JPanel(new BorderLayout());

        timeAPanel = new JPanel(new BorderLayout());
        headerAPanel = new JPanel(new BorderLayout());
        buttonsAPanel = new JPanel(new BorderLayout());
        footerAPanel = new JPanel(new BorderLayout());

        dadosPartidaPanel = new JPanel();
        headerDadosPanel = new JPanel(new BorderLayout());
        setsContPanel = new JPanel(new BorderLayout());
        setsViewerPanel = new JPanel(new BorderLayout());
        footerDadosPanel = new JPanel(new BorderLayout());

        timeBPanel = new JPanel(new BorderLayout());
        headerBPanel = new JPanel(new BorderLayout());
        buttonsBPanel = new JPanel(new BorderLayout());
        footerBPanel = new JPanel(new BorderLayout());


        //Layout
        dadosPartidaPanel.setLayout(new BoxLayout(dadosPartidaPanel, BoxLayout.Y_AXIS));


        //Adicionando Painéis ao Frame
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(timeAPanel, BorderLayout.WEST);
        mainPanel.add(dadosPartidaPanel, BorderLayout.CENTER);
        mainPanel.add(timeBPanel, BorderLayout.EAST);

        timeAPanel.add(headerAPanel, BorderLayout.NORTH);
        timeAPanel.add(buttonsAPanel, BorderLayout.CENTER);
        timeAPanel.add(footerAPanel, BorderLayout.SOUTH);

        dadosPartidaPanel.add(headerDadosPanel, BorderLayout.AFTER_LAST_LINE);
        dadosPartidaPanel.add(setsContPanel, BorderLayout.AFTER_LAST_LINE);
        dadosPartidaPanel.add(setsViewerPanel, BorderLayout.AFTER_LAST_LINE);
        dadosPartidaPanel.add(footerDadosPanel, BorderLayout.AFTER_LAST_LINE);

        timeBPanel.add(headerBPanel, BorderLayout.NORTH);
        timeBPanel.add(buttonsBPanel, BorderLayout.CENTER);
        timeBPanel.add(footerBPanel, BorderLayout.SOUTH);
    }

    public void criarBotoesTimeA(JPanel panel) {
        for (Jogador jogador : this.matchManager.getPartida().getTimeA().getJogadores()) {

        }
    }

    public void criarBotoesTimeB(JPanel panel) {
        for (Jogador jogador : this.matchManager.getPartida().getTimeB().getJogadores()) {

        }
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

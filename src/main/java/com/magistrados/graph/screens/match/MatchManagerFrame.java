package com.magistrados.graph.screens.match;

import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;
import com.magistrados.managers.MatchManager;
import com.magistrados.models.Jogador;
import com.magistrados.models.MatchPlayerStats;
import com.magistrados.models.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class MatchManagerFrame extends JFrame {

    private final Font font = new Font("Roboto", Font.BOLD, 20);
    private final Font btnFont = new Font("Roboto", Font.BOLD, 10);
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


    public MatchManagerFrame(MatchManager matchManager) {
        super("Iniciar Partida - Jogo");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                matchManager.cancelarPartida();
            }
        });
        this.matchManager = matchManager;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        initComponents();
        //empilha tudo
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void initComponents() {

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

        // populate panels

        this.criarBotoesTime(matchManager.getPartida().getTimeA(), buttonsAPanel);
        this.criarBotoesTime(matchManager.getPartida().getTimeB(), buttonsBPanel);

    }

    public void criarBotoesTime(Time time, JPanel panel) {
        // layout config
        final GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        final GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup(); // Configuração das verticais
        final GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup(); // Configuração das horizontais

        final GroupLayout.ParallelGroup nomes = layout.createParallelGroup();
        final GroupLayout.ParallelGroup bloqueios = layout.createParallelGroup();
        final GroupLayout.ParallelGroup saques = layout.createParallelGroup();
        final GroupLayout.ParallelGroup defesas = layout.createParallelGroup();
        final GroupLayout.ParallelGroup pontos = layout.createParallelGroup();

        for (Jogador jogador : time.getJogadores()) {
            final JLabel labelNome = createLabel(font, jogador.getNome());
            final MatchPlayerStats stats = jogador.getMatchPlayerStats(matchManager.getPartida().getId());
            final JButton btnBloqueios = createButton("Bloqueios (" + stats.getQuantidadeBloqueios() + ")", null);
            final JButton btnSaques = createButton("Saques (" + stats.getQuantidadeSaques() + ")", null);
            final JButton btnDefesas = createButton("Defesas (" + stats.getQuantidadeDefesas() + ")", null);
            final JButton btnPontos = createButton("Pontos (" + stats.getQuantidadePontos() + ")", null);

            nomes.addComponent(labelNome);
            bloqueios.addComponent(btnBloqueios);
            saques.addComponent(btnSaques);
            defesas.addComponent(btnDefesas);
            pontos.addComponent(btnPontos);

            vGroup.addGroup(
                    layout.createParallelGroup()
                            .addComponent(labelNome)
                            .addComponent(btnBloqueios)
                            .addComponent(btnSaques)
                            .addComponent(btnDefesas)
                            .addComponent(btnPontos)
            );
        }

        if (Objects.equals(matchManager.getPartida().getIdTimeA(), time.getId()))
            hGroup.addGroup(nomes);
        hGroup.addGroup(bloqueios);
        hGroup.addGroup(saques);
        hGroup.addGroup(defesas);
        hGroup.addGroup(pontos);
        if (Objects.equals(matchManager.getPartida().getIdTimeB(), time.getId()))
            hGroup.addGroup(nomes);

        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);
    }


    private void createButton(JPanel panel, String text, ActionListener listener, boolean space) {
        if (space) panel.add(Box.createRigidArea(new Dimension(0, 50)));
        final DefaultButton button = new DefaultButton(text, listener);
        panel.add(button);
    }

    private DefaultButton createButton(String text, ActionListener listener) {
        return new DefaultButton(text, listener, btnFont, new Dimension(135, 45));
    }


    private DefaultInput createInput(int sizeX, int sizeY) {
        return new DefaultInput(sizeX, sizeY);
    }

    private DefaultLabel createLabel(Font font, String text) {
        return new DefaultLabel(font, text);
    }


}

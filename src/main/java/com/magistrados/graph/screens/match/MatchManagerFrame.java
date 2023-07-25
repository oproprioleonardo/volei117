package com.magistrados.graph.screens.match;

import com.magistrados.graph.buttons.*;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;
import com.magistrados.managers.MatchManager;
import com.magistrados.models.Jogador;
import com.magistrados.models.MatchPlayerStats;
import com.magistrados.models.Time;
import com.magistrados.services.MatchPlayerStatsService;
import com.magistrados.services.PartidaService;
import com.magistrados.services.TimeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class MatchManagerFrame extends MatchManager {

    private static final Font font = new Font("Roboto", Font.BOLD, 20);
    private static final Color BACKGROUND_COLOR = Color.decode("#171717");

    private OperatorButton operator;
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


    public MatchManagerFrame(PartidaService partidaService, TimeService timeService, MatchPlayerStatsService statsService) {
        super(partidaService, timeService, statsService);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                cancelarPartida();
            }
        });
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
        headerAPanel.setBackground(BACKGROUND_COLOR);
        buttonsAPanel = new JPanel(new BorderLayout());
        buttonsAPanel.setBackground(BACKGROUND_COLOR);
        footerAPanel = new JPanel(new BorderLayout());
        footerAPanel.setBackground(BACKGROUND_COLOR);

        dadosPartidaPanel = new JPanel();
        headerDadosPanel = new JPanel(new BorderLayout());
        headerDadosPanel.setBackground(BACKGROUND_COLOR);
        setsContPanel = new JPanel(new BorderLayout());
        setsContPanel.setBackground(BACKGROUND_COLOR);
        setsViewerPanel = new JPanel(new BorderLayout());
        setsViewerPanel.setBackground(BACKGROUND_COLOR);
        footerDadosPanel = new JPanel(new BorderLayout());
        footerDadosPanel.setBackground(BACKGROUND_COLOR);

        timeBPanel = new JPanel(new BorderLayout());
        headerBPanel = new JPanel(new BorderLayout());
        headerBPanel.setBackground(BACKGROUND_COLOR);
        buttonsBPanel = new JPanel(new BorderLayout());
        buttonsBPanel.setBackground(BACKGROUND_COLOR);
        footerBPanel = new JPanel(new BorderLayout());
        footerBPanel.setBackground(BACKGROUND_COLOR);


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

        dadosPartidaPanel.add(headerDadosPanel, BorderLayout.NORTH);
        headerDadosPanel.add(setsContPanel, BorderLayout.SOUTH);
        dadosPartidaPanel.add(setsViewerPanel, BorderLayout.CENTER);
        dadosPartidaPanel.add(footerDadosPanel, BorderLayout.SOUTH);

        timeBPanel.add(headerBPanel, BorderLayout.NORTH);
        timeBPanel.add(buttonsBPanel, BorderLayout.CENTER);
        timeBPanel.add(footerBPanel, BorderLayout.SOUTH);

        //Painel Dados da Partida
        this.headerDadosPanel.add(new DefaultLabel(font, getPartida().getDateTime().toString()), BorderLayout.NORTH);
        this.headerDadosPanel.add(new DefaultLabel(font, getPartida().getLocal()), BorderLayout.CENTER);

        this.setsContPanel.add(new DefaultLabel(font, "" + getPartida().getSetsA(), 28), BorderLayout.WEST);
        this.setsContPanel.add(new DefaultLabel(font, "X", 24), BorderLayout.CENTER);
        this.setsContPanel.add(new DefaultLabel(font, "" + getPartida().getSetsB(), 28), BorderLayout.EAST);

        operator = new OperatorButton("Somando");
        footerDadosPanel.add(operator, BorderLayout.NORTH);

        //Painel do Time A
        this.headerAPanel.add(new DefaultLabel(font, getPartida().getTimeA().getNomeTime()));
        this.criarBotoesTime(getPartida().getTimeA(), buttonsAPanel);
        this.createButton(footerAPanel, "Ponto", null, false);

        //Painel do Time B
        this.headerBPanel.add(new DefaultLabel(font, getPartida().getTimeB().getNomeTime()));
        this.criarBotoesTime(getPartida().getTimeB(), buttonsBPanel);
        this.createButton(footerBPanel, "Ponto", null, false);

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

        final JLabel titleNomes = new DefaultLabel(font, "Nomes");
        titleNomes.setHorizontalAlignment(SwingConstants.CENTER);
        final JLabel titleBloqueios = new DefaultLabel(font, "Bloqueios", new Dimension(100, 45), SwingConstants.CENTER);
        final JLabel titleSaques = new DefaultLabel(font, "Saques", new Dimension(100, 45), SwingConstants.CENTER);
        final JLabel titleDefesas = new DefaultLabel(font, "Defesas", new Dimension(100, 45), SwingConstants.CENTER);
        final JLabel titlePontos = new DefaultLabel(font, "Pontos", new Dimension(100, 45), SwingConstants.CENTER);

        vGroup.addGroup(
                layout.createParallelGroup()
                        .addComponent(titleNomes)
                        .addComponent(titleBloqueios)
                        .addComponent(titleSaques)
                        .addComponent(titleDefesas)
                        .addComponent(titlePontos)
        );

        nomes.addComponent(titleNomes);
        bloqueios.addComponent(titleBloqueios);
        saques.addComponent(titleSaques);
        defesas.addComponent(titleDefesas);
        pontos.addComponent(titlePontos);

        for (Jogador jogador : time.getJogadores()) {
            final JLabel labelNome = new DefaultLabel(font, jogador.getNome());
            final MatchPlayerStats stats = jogador.getMatchPlayerStats(getPartida().getId());
            final JButton btnBloqueios = new BloqueiosButton("(" + stats.getQuantidadeBloqueios() + ")", stats, operator);
            final JButton btnSaques = new SaquesButton("(" + stats.getQuantidadeSaques() + ")", stats, operator);
            final JButton btnDefesas = new DefesasButton("(" + stats.getQuantidadeDefesas() + ")", stats, operator);
            final JButton btnPontos = new PontosButton("(" + stats.getQuantidadePontos() + ")", stats, operator);

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

        if (Objects.equals(getPartida().getIdTimeA(), time.getId()))
            hGroup.addGroup(nomes);
        hGroup.addGroup(bloqueios);
        hGroup.addGroup(saques);
        hGroup.addGroup(defesas);
        hGroup.addGroup(pontos);
        if (Objects.equals(getPartida().getIdTimeB(), time.getId()))
            hGroup.addGroup(nomes);

        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);
    }


    private void createButton(JPanel panel, String text, ActionListener listener, boolean space) {
        if (space) panel.add(Box.createRigidArea(new Dimension(0, 50)));
        final DefaultButton button = new DefaultButton(text, listener);
        panel.add(button);
    }


}

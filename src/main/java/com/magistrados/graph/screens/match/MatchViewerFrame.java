package com.magistrados.graph.screens.match;

import com.magistrados.graph.buttons.*;
import com.magistrados.graph.labels.DefaultLabel;
import com.magistrados.graph.screens.match.models.MatchViewer;
import com.magistrados.models.*;
import com.magistrados.services.PartidaService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MatchViewerFrame extends MatchViewer {

    private static final Font font = new Font("Roboto", Font.BOLD, 20);
    private static final Color BACKGROUND_COLOR = Color.decode("#171717");
    private static final Color BACKGROUND_DADOS_COLOR = new Color(51, 51, 51);
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(MatchManagerFrame.class);
    private boolean botoesTravados = false;
    private OperatorButton operator;
    private DefaultButton btnCancelar;
    public DefaultButton btnConfirmarSet;
    private JPanel mainPanel;
    private JPanel timeAPanel;
    private JPanel headerAPanel;
    private JPanel buttonsAPanel;
    private JPanel dadosPartidaPanel;
    private JPanel headerDadosPanel;
    private JPanel setsContPanel;
    private JPanel setsViewerPanel;
    private JPanel timeBPanel;
    private JPanel headerBPanel;
    private JPanel buttonsBPanel;



    public MatchViewerFrame(PartidaService partidaService, Partida partida) {
        super(partidaService, partida);
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
                layout.createParallelGroup(GroupLayout.Alignment.CENTER)
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
            final JButton btnBloqueios = new ViewerButton("(" + stats.getQuantidadeBloqueios() + ")");
            final JButton btnSaques = new ViewerButton("(" + stats.getQuantidadeSaques() + ")");
            final JButton btnDefesas = new ViewerButton("(" + stats.getQuantidadeDefesas() + ")");
            final JButton btnPontos = new ViewerButton("(" + stats.getQuantidadePontos() + ")");

            nomes.addComponent(labelNome);
            bloqueios.addComponent(btnBloqueios);
            saques.addComponent(btnSaques);
            defesas.addComponent(btnDefesas);
            pontos.addComponent(btnPontos);

            vGroup.addGroup(
                    layout.createParallelGroup(GroupLayout.Alignment.CENTER)
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

    public void updateSetsComponent() {
        this.setsViewerPanel.removeAll();
        for (GameSet set : getPartida().getGameSets()) {
            this.setsViewerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            final JPanel setsContPanel = new JPanel(new BorderLayout());
            setsContPanel.setBackground(BACKGROUND_DADOS_COLOR);
            if (set.isIniciado() && !set.isFinalizado()) {
                setsContPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 225, 255), 3));
            } else setsContPanel.setBorder(null);
            setsContPanel.add(new DefaultLabel(font, "" + set.getPontosTimeA(), 16), BorderLayout.WEST);
            setsContPanel.add(new DefaultLabel(font, "X", 14), BorderLayout.CENTER);
            setsContPanel.add(new DefaultLabel(font, "" + set.getPontosTimeB(), 16), BorderLayout.EAST);
            this.setsViewerPanel.add(setsContPanel);
        }
        this.setsContPanel.removeAll();

        this.setsContPanel.add(new DefaultLabel(font, "" + getPartida().getSetsA(), 28), BorderLayout.WEST);
        this.setsContPanel.add(new DefaultLabel(font, "X", 24), BorderLayout.CENTER);
        this.setsContPanel.add(new DefaultLabel(font, "" + getPartida().getSetsB(), 28), BorderLayout.EAST);
        this.revalidate();
        this.repaint();
    }


    private void createButton(JPanel panel, String text, ActionListener listener, boolean space) {
        if (space) panel.add(Box.createRigidArea(new Dimension(0, 50)));
        final DefaultButton button = new DefaultButton(text, listener);
        panel.add(button);
    }


    @Override
    public void updateComponents() {

        //Criando JPanels
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        timeAPanel = new JPanel(new BorderLayout());
        headerAPanel = new JPanel(new BorderLayout());
        headerAPanel.setBackground(BACKGROUND_COLOR);
        buttonsAPanel = new JPanel(new BorderLayout());
        buttonsAPanel.setBackground(BACKGROUND_COLOR);

        dadosPartidaPanel = new JPanel();
        dadosPartidaPanel.setBackground(BACKGROUND_DADOS_COLOR);
        dadosPartidaPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        headerDadosPanel = new JPanel(new BorderLayout());
        headerDadosPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        headerDadosPanel.setBackground(BACKGROUND_DADOS_COLOR);
        setsContPanel = new JPanel(new BorderLayout());
        setsContPanel.setBackground(BACKGROUND_DADOS_COLOR);
        setsViewerPanel = new JPanel();
        setsViewerPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 6));
        setsViewerPanel.setSize(new Dimension(200, 250));
        setsViewerPanel.setLayout(new BoxLayout(setsViewerPanel, BoxLayout.Y_AXIS));
        setsViewerPanel.setBackground(BACKGROUND_DADOS_COLOR);


        timeBPanel = new JPanel(new BorderLayout());
        headerBPanel = new JPanel(new BorderLayout());
        headerBPanel.setBackground(BACKGROUND_COLOR);
        buttonsBPanel = new JPanel(new BorderLayout());
        buttonsBPanel.setBackground(BACKGROUND_COLOR);


        //Layout
        dadosPartidaPanel.setLayout(new BoxLayout(dadosPartidaPanel, BoxLayout.Y_AXIS));


        //Adicionando Painéis ao Frame
        this.add(mainPanel, BorderLayout.CENTER);

        mainPanel.add(timeAPanel, BorderLayout.WEST);
        mainPanel.add(dadosPartidaPanel, BorderLayout.CENTER);
        mainPanel.add(timeBPanel, BorderLayout.EAST);

        timeAPanel.add(headerAPanel, BorderLayout.NORTH);
        timeAPanel.add(buttonsAPanel, BorderLayout.CENTER);

        dadosPartidaPanel.add(headerDadosPanel, BorderLayout.NORTH);
        headerDadosPanel.add(setsContPanel, BorderLayout.SOUTH);
        dadosPartidaPanel.add(setsViewerPanel, BorderLayout.CENTER);

        timeBPanel.add(headerBPanel, BorderLayout.NORTH);
        timeBPanel.add(buttonsBPanel, BorderLayout.SOUTH);

        //Painel Dados da Partida
        this.headerDadosPanel.add(new DefaultLabel(font, getPartida().getDateTime().toString()), BorderLayout.NORTH);
        this.headerDadosPanel.add(new DefaultLabel(font, getPartida().getLocal()), BorderLayout.CENTER);

        this.updateSetsComponent();


        //Painel do Time A
        this.headerAPanel.add(new DefaultLabel(font, getPartida().getTimeA().getNomeTime()));
        this.criarBotoesTime(getPartida().getTimeA(), buttonsAPanel);


        //Painel do Time B
        this.headerBPanel.add(new DefaultLabel(font, getPartida().getTimeB().getNomeTime()));
        this.criarBotoesTime(getPartida().getTimeB(), buttonsBPanel);
    }
}

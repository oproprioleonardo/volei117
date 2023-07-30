package com.magistrados.graph.screens.match;

import com.magistrados.graph.buttons.*;
import com.magistrados.graph.labels.DefaultLabel;
import com.magistrados.graph.notification.Notifications;
import com.magistrados.graph.screens.match.models.MatchManager;
import com.magistrados.models.GameSet;
import com.magistrados.models.Jogador;
import com.magistrados.models.MatchPlayerStats;
import com.magistrados.models.Time;
import com.magistrados.services.GameSetService;
import com.magistrados.services.MatchPlayerStatsService;
import com.magistrados.services.PartidaService;
import com.magistrados.services.TimeService;
import io.smallrye.mutiny.Uni;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class MatchManagerFrame extends MatchManager {

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
    private JPanel footerAPanel;
    private JPanel dadosPartidaPanel;
    private JPanel headerDadosPanel;
    private JPanel setsContPanel;
    private JPanel setsViewerPanel;
    private JPanel centeringPanel;
    private JPanel footerDadosPanel;
    private JPanel timeBPanel;
    private JPanel headerBPanel;
    private JPanel buttonsBPanel;
    private JPanel footerBPanel;


    public MatchManagerFrame(PartidaService partidaService, TimeService timeService, MatchPlayerStatsService statsService, GameSetService gameSetService) {
        super(partidaService, timeService, statsService, gameSetService);
    }

    @Override
    public void initComponents() {
        //Criando JPanels
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        timeAPanel = new JPanel(new BorderLayout());
        headerAPanel = new JPanel(new BorderLayout());
        headerAPanel.setBackground(BACKGROUND_COLOR);
        buttonsAPanel = new JPanel(new BorderLayout());
        buttonsAPanel.setBackground(BACKGROUND_COLOR);
        footerAPanel = new JPanel(new BorderLayout());
        footerAPanel.setBackground(BACKGROUND_COLOR);

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
        centeringPanel = new JPanel();
        centeringPanel.setBackground(BACKGROUND_DADOS_COLOR);
        footerDadosPanel = new JPanel();
        footerDadosPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0));
        footerDadosPanel.setBackground(BACKGROUND_DADOS_COLOR);

        timeBPanel = new JPanel(new BorderLayout());
        headerBPanel = new JPanel(new BorderLayout());
        headerBPanel.setBackground(BACKGROUND_COLOR);
        buttonsBPanel = new JPanel(new BorderLayout());
        buttonsBPanel.setBackground(BACKGROUND_COLOR);
        footerBPanel = new JPanel(new BorderLayout());
        footerBPanel.setBackground(BACKGROUND_COLOR);


        //Layout
        centeringPanel.setLayout(new BoxLayout(centeringPanel, BoxLayout.X_AXIS));
        footerDadosPanel.setLayout(new BoxLayout(footerDadosPanel, BoxLayout.Y_AXIS));
        footerDadosPanel.setMaximumSize(new Dimension(300, 200));
        footerDadosPanel.setMinimumSize(new Dimension(300, 200));
        dadosPartidaPanel.setLayout(new BoxLayout(dadosPartidaPanel, BoxLayout.Y_AXIS));


        centeringPanel.add(Box.createHorizontalGlue());
        centeringPanel.add(footerDadosPanel);
        centeringPanel.add(Box.createHorizontalGlue());


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
        dadosPartidaPanel.add(centeringPanel, BorderLayout.SOUTH);

        timeBPanel.add(headerBPanel, BorderLayout.NORTH);
        timeBPanel.add(buttonsBPanel, BorderLayout.CENTER);
        timeBPanel.add(footerBPanel, BorderLayout.SOUTH);

        //Painel Dados da Partida
        this.headerDadosPanel.add(new DefaultLabel(font, getPartida().getDateTime().toString()), BorderLayout.NORTH);
        this.headerDadosPanel.add(new DefaultLabel(font, getPartida().getLocal()), BorderLayout.CENTER);

        this.updateSetsComponent();

        operator = new OperatorButton("Somando", this);
        btnCancelar = new DefaultButton("Cancelar", e -> {
            travarTodosBotoes();

            Notifications.info("A partida está sendo cancelada, aguarde...");

            final Uni<String> emitter = Uni.createFrom().emitter((em) -> new Thread(() -> {
                cancelarPartida();
                em.complete("Partida cancelada com sucesso!");
            }).start());

            emitter.subscribe().with(s -> {
                Notifications.info(s);
                dispose();
            });
        });


        footerDadosPanel.add(operator);
        footerDadosPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        footerDadosPanel.add(btnCancelar);
        footerDadosPanel.add(Box.createRigidArea(new Dimension(0, 20)));


        //Painel do Time A
        this.headerAPanel.add(new DefaultLabel(font, getPartida().getTimeA().getNomeTime()));
        this.criarBotoesTime(getPartida().getTimeA(), buttonsAPanel);

        final Consumer<Runnable> confirmarAvancarSet = runnable -> {
            travarTodosBotoes();
            ativarBotoesEssenciais();

            btnConfirmarSet = new DefaultButton("Finalizar Set", e1 -> {
                runnable.run();
                this.updateSetsComponent();
                this.resetBtnFinalizarSet();
                if (this.getPartida().isFinalizada()) {
                    travarTodosBotoes();
                } else destravarTodosBotoes();
            });

            footerDadosPanel.add(btnConfirmarSet);

            this.revalidate();
            this.repaint();
        };

        this.createButton(footerAPanel, "Ponto", e -> {
            if (operator.isSomando())
                adicionarPontoTimeA().ifPresent(confirmarAvancarSet);
            else removerPontoTimeA();
            this.updateSetsComponent();
        }, false);

        //Painel do Time B
        this.headerBPanel.add(new DefaultLabel(font, getPartida().getTimeB().getNomeTime()));
        this.criarBotoesTime(getPartida().getTimeB(), buttonsBPanel);
        this.createButton(footerBPanel, "Ponto", e -> {
            if (operator.isSomando())
                adicionarPontoTimeB().ifPresent(confirmarAvancarSet);
            else removerPontoTimeB();
            this.updateSetsComponent();
        }, false);


    }

    @Override
    public void resetBtnFinalizarSet() {
        footerDadosPanel.remove(btnConfirmarSet);
        this.revalidate();
        this.repaint();
        btnConfirmarSet = null;
    }


    public void destravarTodosBotoes() {
        this.getButtonsFromFrame(this).forEach(btn -> btn.setEnabled(true));
        botoesTravados = false;
    }

    @Override
    public DefaultButton getBtnFinalizarSet() {
        return btnConfirmarSet;
    }

    public void ativarBotoesEssenciais() {
        operator.setEnabled(true);
        btnCancelar.setEnabled(true);
    }

    public void travarTodosBotoes() {
        this.getButtonsFromFrame(this).forEach(btn -> btn.setEnabled(false));
        botoesTravados = true;
    }

    private java.util.List<JButton> getButtonsFromFrame(Component component) {
        final java.util.List<JButton> buttonsList = new ArrayList<>();
        if (component instanceof JButton) {
            buttonsList.add((JButton) component);
        } else if (component instanceof Container) {
            final Component[] components = ((Container) component).getComponents();
            for (Component child : components) {
                buttonsList.addAll(getButtonsFromFrame(child));
            }
        }

        return buttonsList;
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
        this.pack();
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
        this.pack();
        this.setsContPanel.add(new DefaultLabel(font, "" + getPartida().getSetsA(), 28), BorderLayout.WEST);
        this.setsContPanel.add(new DefaultLabel(font, "X", 24), BorderLayout.CENTER);
        this.setsContPanel.add(new DefaultLabel(font, "" + getPartida().getSetsB(), 28), BorderLayout.EAST);
        this.pack();
    }


    private void createButton(JPanel panel, String text, ActionListener listener, boolean space) {
        if (space) panel.add(Box.createRigidArea(new Dimension(0, 50)));
        final DefaultButton button = new DefaultButton(text, listener);
        panel.add(button);
    }


}

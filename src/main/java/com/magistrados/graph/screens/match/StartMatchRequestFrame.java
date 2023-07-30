package com.magistrados.graph.screens.match;

import com.magistrados.api.validations.exceptions.ValidationException;
import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;
import com.magistrados.graph.notification.Notifications;
import com.magistrados.internal.validators.create.CreateMatchValidator;
import com.magistrados.models.create.CreateMatch;
import com.magistrados.services.GameSetService;
import com.magistrados.services.MatchPlayerStatsService;
import com.magistrados.services.PartidaService;
import com.magistrados.services.TimeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class StartMatchRequestFrame extends JFrame {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StartMatchRequestFrame.class);
    private final PartidaService partidaService;
    private final TimeService timeService;
    private final MatchPlayerStatsService statsService;
    private final GameSetService gameSetService;
    private final Font font = new Font("Roboto", Font.BOLD, 20);
    private JPanel inputPanel;
    private JPanel mainPanel;
    private JPanel buttonEmptySpacePanel;
    private JPanel buttonsPanel;
    private JPanel paddingPanel;
    private JLabel labelIdTime1;
    private JLabel labelIdTime2;
    private JLabel labelData;
    private JLabel labelHorario;
    private JLabel labelLocal;
    private JButton btnIniciar;
    private JButton btnVoltar;
    private JTextField campoIdTime1;
    private JTextField campoIdTime2;
    private JTextField campoData;
    private JTextField campoHorario;
    private JTextField campoLocal;

    public StartMatchRequestFrame(PartidaService partidaService, TimeService timeService, MatchPlayerStatsService statsService, GameSetService gameSetService) throws HeadlessException {
        super("Iniciar Partida - Requerimentos");
        this.partidaService = partidaService;
        this.timeService = timeService;
        this.statsService = statsService;
        this.gameSetService = gameSetService;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.initComponents();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void initComponents() {
        final Date dateNow = new Date();

        //Criando Jlabels
        labelIdTime1 = createLabel(font, "ID do 1º Time:");
        labelIdTime2 = createLabel(font, "ID do 2º Time:");
        labelData = createLabel(font, "Data:");
        labelHorario = createLabel(font, "Horário:");
        labelLocal = createLabel(font, "Local:");

        //Criando JTextField
        campoIdTime1 = createInput(300, 40);
        campoIdTime2 = createInput(300, 40);
        campoData = createInput(300, 40, new SimpleDateFormat("dd/MM/yyyy").format(dateNow));
        campoHorario = createInput(300, 40, new SimpleDateFormat("HH:mm").format(dateNow));
        campoLocal = createInput(300, 40, "IFSP CBT - Ginásio");

        //Criando Painéis
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.decode("#171717"));

        buttonEmptySpacePanel = new JPanel();
        buttonEmptySpacePanel.setBackground(Color.decode("#171717"));

        buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Color.decode("#171717"));

        paddingPanel = new JPanel();
        paddingPanel.setBackground(Color.decode("#171717"));

        inputPanel = new JPanel();
        inputPanel.setBackground(Color.decode("#171717"));


        // Configurações do Layout
        mainPanel.setLayout(new BorderLayout());

        buttonEmptySpacePanel.setLayout(new BoxLayout(buttonEmptySpacePanel, BoxLayout.Y_AXIS));

        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(150, 25, 125, 25));

        paddingPanel.setLayout(new BorderLayout());
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(100, 25, 100, 25));

        inputPanel.setLayout(new BorderLayout());


        // Configuração do GroupLayout
        final GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        final GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup(); // Configuração das horizontais
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(labelIdTime1)
                .addComponent(labelIdTime2)
                .addComponent(labelData)
                .addComponent(labelHorario)
                .addComponent(labelLocal));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(campoIdTime1)
                .addComponent(campoIdTime2)
                .addComponent(campoData)
                .addComponent(campoHorario)
                .addComponent(campoLocal));
        layout.setHorizontalGroup(hGroup);

        final GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup(); // Configuração das verticais
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelIdTime1)
                .addComponent(campoIdTime1));
        vGroup.addGap(50);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelIdTime2)
                .addComponent(campoIdTime2));
        vGroup.addGap(50);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelData)
                .addComponent(campoData));
        vGroup.addGap(50);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelHorario)
                .addComponent(campoHorario));
        vGroup.addGap(50);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelLocal)
                .addComponent(campoLocal));
        layout.setVerticalGroup(vGroup);


        // Criando botões
        btnIniciar = this.createButton(buttonEmptySpacePanel, "Iniciar", this.startMatch(), false);
        btnVoltar = this.createButton(buttonEmptySpacePanel, "Voltar", e -> this.dispose(), true);

        //Adicionando Paineis
        buttonsPanel.add(buttonEmptySpacePanel, BorderLayout.CENTER);

        paddingPanel.add(buttonsPanel, BorderLayout.EAST);
        paddingPanel.add(inputPanel, BorderLayout.WEST);

        mainPanel.add(paddingPanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);

    }

    private ActionListener startMatch() {
        return e -> {
            final CreateMatch createMatch = new CreateMatch(
                    this.campoIdTime1.getText(),
                    this.campoIdTime2.getText(),
                    this.campoData.getText(),
                    this.campoHorario.getText(),
                    this.campoLocal.getText()
            );

            try {
                new CreateMatchValidator().validate(createMatch);
            } catch (ValidationException ex) {
                ex.printOnFile();
            }

            this.btnIniciar.setEnabled(false);

            Notifications.info("A partida está sendo criada, aguarde alguns segundos.");
            new Thread(() -> {
                try {
                    final MatchManagerFrame matchManagerFrame = new MatchManagerFrame(partidaService, timeService, statsService, gameSetService);
                    matchManagerFrame.iniciarPartida(
                            createMatch.getIdTimeA(),
                            createMatch.getIdTimeB(),
                            createMatch.local(),
                            LocalDateTime.of(createMatch.getData(), createMatch.getHorario())
                    );
                    this.dispose();
                    this.btnIniciar.setEnabled(true);
                    matchManagerFrame.setVisible(true);
                    Notifications.info("A partida foi criada com sucesso.");
                } catch (Exception ex) {
                    Notifications.error("Não foi possível iniciar a partida entre os times " + this.campoIdTime1.getText() + " e " + this.campoIdTime2.getText());
                    log.error("Erro ao iniciar partida.", ex);
                }
            }).start();

        };
    }

    private JButton createButton(JPanel panel, String text, ActionListener listener, boolean space) {
        if (space) panel.add(Box.createRigidArea(new Dimension(0, 50)));
        final DefaultButton button = new DefaultButton(text, listener);
        panel.add(button);
        return button;
    }

    private DefaultInput createInput(int sizeX, int sizeY, String... text) {
        return new DefaultInput(sizeX, sizeY, text);
    }

    private DefaultLabel createLabel(Font font, String text) {
        return new DefaultLabel(font, text);
    }

}

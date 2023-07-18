package com.magistrados.graph.screens.managertimes;

import com.magistrados.api.validations.exceptions.ValidationException;
import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;
import com.magistrados.internal.validators.create.CreatePlayerValidator;
import com.magistrados.internal.validators.create.CreateTeamValidator;
import com.magistrados.internal.validators.edit.EditPlayerValidator;
import com.magistrados.internal.validators.edit.EditTeamValidator;
import com.magistrados.internal.validators.find.FindPlayerValidator;
import com.magistrados.internal.validators.find.FindTeamValidator;
import com.magistrados.internal.validators.remove.RemovePlayerValidator;
import com.magistrados.internal.validators.remove.RemoveTeamValidator;
import com.magistrados.models.Jogador;
import com.magistrados.models.Time;
import com.magistrados.models.create.CreatePlayer;
import com.magistrados.models.create.CreateTeam;
import com.magistrados.models.edit.EditPlayer;
import com.magistrados.models.edit.EditTeam;
import com.magistrados.models.find.FindPlayer;
import com.magistrados.models.find.FindTeam;
import com.magistrados.models.remove.RemovePlayer;
import com.magistrados.models.remove.RemoveTeam;
import com.magistrados.services.PartidaService;
import com.magistrados.services.TimeService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Optional;

public class ManagerTimesFrame extends JFrame {
    private JPanel inputPanel;
    private JPanel buttonsPanel;
    private TimeService timeService;
    private JTextField campoIdTime;
    private JTextField campoNomeTime;
    private JTextField campoVitorias;
    private JTextField campoDerrotas;
    Font font = new Font("Roboto", Font.BOLD, 20);

    public ManagerTimesFrame(TimeService timeService) throws HeadlessException {
        super("Gerenciador de Times");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.timeService = timeService;
        //buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        // Criando painel com box layout para ficar um botão debaixo do outro
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#171717"));

        // Criando botões CRUD
        this.createButton(buttonPanel, "Adicionar Time", adicionarTimeListener(), false);
        this.createButton(buttonPanel, "Visualizar Time", buscarTimeListener(), true);
        this.createButton(buttonPanel, "Editar Time", editarTimeListener(), true);
        this.createButton(buttonPanel, "Remover Time", removerTimeListener(), true);

        // Criando um painel de preenchimento com EmptyBorder
        JPanel paddingPanel = new JPanel(new BorderLayout());
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(100, 25, 100, 25));
        paddingPanel.add(buttonPanel, BorderLayout.CENTER);
        paddingPanel.setBackground(Color.decode("#171717"));

        // Adicionando o painel de preenchimento (com os botões) ao centro do painel principal
        buttonsPanel.add(paddingPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);

        //input
        // Criando o painel input com BorderLayout
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(Color.decode("#171717"));

        //declarando Jlabels
        JLabel labelIdTime =  createLabel(font,"ID do Time:");
        JLabel labelVitoriasTime = createLabel(font, "Nome do Time: ");
        JLabel labelVitorias = createLabel(font, "Número de Vitórias: ");
        JLabel labelDerrotas = createLabel(font,"Número de Derrotas:");

        //declarando JTextField
        campoIdTime = createInput(300,40);
        campoNomeTime =  createInput(300,40);
        campoVitorias = createInput(300,40);
        campoDerrotas = createInput(300,40);

        // Configuração do GroupLayout
        GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Configuração das horizontais
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(labelIdTime)
                .addComponent(labelVitoriasTime)
                .addComponent(labelVitorias)
                .addComponent(labelDerrotas));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(campoIdTime)
                .addComponent(campoNomeTime)
                .addComponent(campoVitorias)
                .addComponent(campoDerrotas));
        layout.setHorizontalGroup(hGroup);

        // Configuração das verticais
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(110);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelIdTime)
                .addComponent(campoIdTime));
        vGroup.addGap(50);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelVitoriasTime)
                .addComponent(campoNomeTime));
        vGroup.addGap(50);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelVitorias)
                .addComponent(campoVitorias));
        vGroup.addGap(50);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelDerrotas)
                .addComponent(campoDerrotas));
        layout.setVerticalGroup(vGroup);

        //adicionando inputPanel
        this.add(inputPanel, BorderLayout.WEST);

        //empilha tudo
        this.pack();
        this.setLocationRelativeTo(null);

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

    private ActionListener removerTimeListener(){
        return e -> {
            final RemoveTeam removeTeam = new RemoveTeam(
                    this.campoIdTime.getText(),
                    this.campoNomeTime.getText()
            );

            try {
                new RemoveTeamValidator().validate(removeTeam);
            } catch (ValidationException ex) {
                ex.printOnFile();
                return;
            }

            this.timeService.deletarTime(removeTeam);
            this.cleanFields();
        };
    }

    private ActionListener editarTimeListener(){
        return e -> {
            final EditTeam editTeam = new EditTeam(
                    this.campoIdTime.getText(),
                    this.campoNomeTime.getText(),
                    this.campoVitorias.getText(),
                    this.campoDerrotas.getText()
            );

            try {
                new EditTeamValidator().validate(editTeam);
            } catch (ValidationException ex) {
                ex.printOnFile();
                return;
            }

            this.timeService.editarTime(editTeam);
        };
    }

    private ActionListener adicionarTimeListener(){
        return e -> {
            final CreateTeam createTeam = new CreateTeam(
                    this.campoNomeTime.getText(),
                    this.campoVitorias.getText(),
                    this.campoDerrotas.getText()
            );
            try {
                new CreateTeamValidator().validate(createTeam);
            } catch (ValidationException ex) {
                ex.printOnFile();
                return;
            }


            final Time time = this.timeService.criarTime(createTeam);
            this.campoIdTime.setText(time.getId().toString());
        };
    }

    private ActionListener buscarTimeListener(){
        return e -> {
            final FindTeam findTeam = new FindTeam(
                    this.campoIdTime.getText(),
                    this.campoNomeTime.getText()
            );
            try {
                new FindTeamValidator().validate(findTeam);
            } catch (ValidationException ex) {
                ex.printOnFile();
                return;
            }

            Long idTime = Long.parseLong(findTeam.id());


            final Time time = Optional.ofNullable(this.timeService.buscarTime(idTime))
                    .orElse(this.timeService.buscarTime(findTeam.nome()));

            this.setFields(time);
        };
    }

    private void cleanFields() {
        this.campoIdTime.setText("");
        this.campoNomeTime.setText("");
        this.campoVitorias.setText("");
        this.campoDerrotas.setText("");
    }

    private void setFields(Time time){
        this.campoIdTime.setText(time.getId().toString());
        this.campoNomeTime.setText(time.getNomeTime());
        this.campoVitorias.setText(time.getVitorias().toString());
        this.campoDerrotas.setText(time.getDerrotas().toString());
    }
}

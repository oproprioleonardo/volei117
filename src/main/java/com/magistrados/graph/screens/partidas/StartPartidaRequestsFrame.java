package com.magistrados.graph.screens.partidas;

import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPartidaRequestsFrame extends JFrame {
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
    private JTextField campoIdTime1;
    private JTextField campoIdTime2;
    private JTextField campoData;
    private JTextField campoHorario;
    private JTextField campoLocal;
    Font font = new Font("Roboto", Font.BOLD, 20);

    public StartPartidaRequestsFrame() throws HeadlessException{
        super("Iniciar Partida - Requerimentos");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        initComponents();
        //empilha tudo
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public void initComponents(){
        //Criando Jlabels
        labelIdTime1 =  createLabel(font,"ID do 1º Time:");
        labelIdTime2 = createLabel(font, "ID do 2º Time:");
        labelData = createLabel(font, "Data:");
        labelHorario = createLabel(font,"Horário:");
        labelLocal = createLabel(font,"Local:");

        //Criando JTextField
        campoIdTime1 = createInput(300,40);
        campoIdTime2 =  createInput(300,40);
        campoData = createInput(300,40);
        campoHorario = createInput(300,40);
        campoLocal = createInput(300,40);

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
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(100, 25, 100, 25));

        paddingPanel.setLayout(new BorderLayout());
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(100, 25, 100, 25));

        inputPanel.setLayout(new BorderLayout());


        // Configuração do GroupLayout
        GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup(); // Configuração das horizontais
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

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup(); // Configuração das verticais
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
        this.createButton(buttonEmptySpacePanel, "Iniciar", e -> {
            final JFrame startPartidaFrame = new StartPartidaFrame();
            startPartidaFrame.setVisible(true);

        }, false);
        this.createButton(buttonEmptySpacePanel, "Voltar", e -> {
        this.dispose();

        }, true);


        //Adicionando Paineis
        buttonsPanel.add(buttonEmptySpacePanel, BorderLayout.CENTER);

        paddingPanel.add(buttonsPanel, BorderLayout.EAST);

        mainPanel.add(paddingPanel, BorderLayout.CENTER);
        this.add(mainPanel, BorderLayout.CENTER);

        paddingPanel.add(inputPanel, BorderLayout.WEST);
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

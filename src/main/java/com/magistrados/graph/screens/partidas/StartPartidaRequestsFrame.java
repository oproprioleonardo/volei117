package com.magistrados.graph.screens.partidas;

import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class StartPartidaRequestsFrame extends JFrame {
    private JPanel inputPanel;
    private JPanel buttonsPanel;

    Font font = new Font("Roboto", Font.BOLD, 20);
    public StartPartidaRequestsFrame() throws HeadlessException{
        super("Iniciar Partida - Requerimentos");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //declarando Jlabels
        JLabel labelIdTime1 =  createLabel(font,"ID do 1º Time:");
        JLabel labelIdTime2 = createLabel(font, "ID do 2º Time: ");
        JLabel labelData = createLabel(font, "Data: ");
        JLabel labelHorario = createLabel(font,"Horário: ");
        JLabel labelLocal = createLabel(font,"Local: ");

        //declarando JTextField
        JTextField campoIdTime1 = createInput(300,40);
        JTextField campoIdTime2 =  createInput(300,40);
        JTextField campoData = createInput(300,40);
        JTextField campoHorario = createInput(300,40);
        JTextField campoLocal = createInput(300,40);





        //buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        // Criando painel com box layout para ficar um botão debaixo do outro
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#171717"));

        JPanel eastAreaWithBorder = new JPanel(new BorderLayout());
        eastAreaWithBorder.add(buttonPanel, BorderLayout.CENTER);
        eastAreaWithBorder.setBorder(BorderFactory.createEmptyBorder(100, 25, 100, 25));
        eastAreaWithBorder.setBackground(Color.decode("#171717"));

        // Criando botões CRUD
        this.createButton(buttonPanel, "Iniciar", e -> {


        }, false);
        this.createButton(buttonPanel, "Voltar", e -> {


        }, true);

        // Criando um painel de preenchimento com EmptyBorder
        JPanel paddingPanel = new JPanel(new BorderLayout());
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(100, 25, 100, 25));
        paddingPanel.add(eastAreaWithBorder, BorderLayout.EAST);
        paddingPanel.setBackground(Color.decode("#171717"));

        // Adicionando o painel de preenchimento (com os botões) ao centro do painel principal
        buttonsPanel.add(paddingPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.CENTER);

        //input
        // Criando o painel input com BorderLayout
        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(Color.decode("#171717"));

        // Configuração do GroupLayout
        GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        // Criando um painel de preenchimento com EmptyBorder
        /*JPanel paddingPanel2 = new JPanel(new BorderLayout());
        paddingPanel2.setBorder(BorderFactory.createEmptyBorder(100, 25, 100, 25));
        paddingPanel2.add(inputPanel, BorderLayout.CENTER);
        paddingPanel2.setBackground(Color.decode("#171717"));

        formPanel.add(paddingPanel2, BorderLayout.CENTER);
        this.add(formPanel, BorderLayout.WEST);*/

        paddingPanel.add(inputPanel, BorderLayout.WEST);

        // Configuração das horizontais
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
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

        // Configuração das verticais
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
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

        //adicionando inputPanel
        // this.add(paddingPanel2, BorderLayout.WEST);

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

}

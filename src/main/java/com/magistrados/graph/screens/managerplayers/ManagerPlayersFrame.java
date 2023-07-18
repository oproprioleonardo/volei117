package com.magistrados.graph.screens.managerplayers;


import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerPlayersFrame extends JFrame {

    private JPanel inputPanel;
    private JPanel buttonsPanel;
    private Font font = new Font("Roboto", Font.BOLD, 20);


    public ManagerPlayersFrame() throws HeadlessException {
        super("Gerenciador de Jogadores");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());

        //buttons
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        // Criando painel com box layout para ficar um botão debaixo do outro
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#171717"));

        // Criando botões CRUD
        this.createButton(buttonPanel, "Adicionar Jogador(a)", e -> {


        }, false);
        this.createButton(buttonPanel, "Visualizar Jogador(a)", e -> {


        }, true);
        this.createButton(buttonPanel, "Editar Jogador(a)", e -> {


        }, true);
        this.createButton(buttonPanel, "Remover Jogador(a)", e -> {


        }, true);
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
        JLabel labelIdJogador = createLabel(font,"ID do Jogador(a):");
        JLabel labelIdTime =  createLabel(font,"ID do Time:");
        JLabel labelNome = createLabel(font, "Nome: ");
        JLabel labelNumeroJogador = createLabel(font, "Número do Jogador: ");
        JLabel labelBloqueiosFeitos = createLabel(font, "Número de Bloqueios Feitos: ");
        JLabel labelDefesasFeitas = createLabel(font, "Número de Defesas Feitas: ");
        JLabel labelSaquesFeitos = createLabel(font, "Número de Saques Feitos: ");
        JLabel labelPontosFeitos = createLabel(font, "Número de Pontos Feitos: ");

        //declarando JTextField
        JTextField campoIdJogador = createInput(300,40);
        JTextField campoIdTime =  createInput(300,40);
        JTextField campoNome = createInput(300,40);
        JTextField campoNumeroJogador = createInput(300,40);
        JTextField campoBloqueiosFeitos = createInput(300,40);
        JTextField campoDefesasFeitas = createInput(300,40);
        JTextField campoSaquesFeitos = createInput(300,40);
        JTextField campoPontosFeitos = createInput(300,40);

        // Configuração do GroupLayout
        GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Configuração das horizontais
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(labelIdJogador)
                .addComponent(labelIdTime)
                .addComponent(labelNome)
                .addComponent(labelNumeroJogador)
                .addComponent(labelBloqueiosFeitos)
                .addComponent(labelDefesasFeitas)
                .addComponent(labelSaquesFeitos)
                .addComponent(labelPontosFeitos));
        hGroup.addGroup(layout.createParallelGroup()
                .addComponent(campoIdJogador)
                .addComponent(campoIdTime)
                .addComponent(campoNome)
                .addComponent(campoNumeroJogador)
                .addComponent(campoBloqueiosFeitos)
                .addComponent(campoDefesasFeitas)
                .addComponent(campoSaquesFeitos)
                .addComponent(campoPontosFeitos));
        layout.setHorizontalGroup(hGroup);

        // Configuração das verticais
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGap(40);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelIdJogador)
                .addComponent(campoIdJogador));
        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelIdTime)
                .addComponent(campoIdTime));
        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelNome)
                .addComponent(campoNome));
        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelNumeroJogador)
                .addComponent(campoNumeroJogador));
        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelBloqueiosFeitos)
                .addComponent(campoBloqueiosFeitos));
        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelDefesasFeitas)
                .addComponent(campoDefesasFeitas));
        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelSaquesFeitos)
                .addComponent(campoSaquesFeitos));
        vGroup.addGap(20);
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelPontosFeitos)
                .addComponent(campoPontosFeitos));
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


}

package com.magistrados.graph.managerplayers;


import com.magistrados.graph.buttons.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerPlayersFrame extends JFrame {

    private JPanel inputPanel;
    private JPanel buttonsPanel;


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
        // Criando botões
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

        //declarando Jlabels
        JLabel labelIdJogador = new JLabel("ID do Jogador(a):");
        JLabel labelIdTime = new JLabel("ID do Time:");
        JLabel labelNome = new JLabel("Nome: ");
        JLabel labelNumeroJogador = new JLabel("Número do Jogador:");
        JLabel labelBloqueiosFeitos = new JLabel("Número de Bloqueios Feitos: ");
        JLabel labelDefesasFeitas = new JLabel("Número de Defesas Feitas: ");
        JLabel labelSaquesFeitos = new JLabel("Número de Saques Feitos: ");
        JLabel labelPontosFeitos = new JLabel("Número de Pontos Feitos: ");

        //declarando JTextField
        JTextField campoIdJogador = new JTextField();
        campoIdJogador.setPreferredSize(new Dimension(300, 40));
        JTextField campoIdTime = new JTextField();
        campoIdTime.setPreferredSize(new Dimension(300, 40));
        JTextField campoNome = new JTextField();
        campoNome.setPreferredSize(new Dimension(300, 40));
        JTextField campoNumeroJogador = new JTextField();
        campoNumeroJogador.setPreferredSize(new Dimension(300, 40));
        JTextField campoBloqueiosFeitos = new JTextField();
        campoBloqueiosFeitos.setPreferredSize(new Dimension(300, 40));
        JTextField campoDefesasFeitas = new JTextField();
        campoDefesasFeitas.setPreferredSize(new Dimension(300, 40));
        JTextField campoSaquesFeitos = new JTextField();
        campoSaquesFeitos.setPreferredSize(new Dimension(300, 40));
        JTextField campoPontosFeitos = new JTextField();
        campoPontosFeitos.setPreferredSize(new Dimension(300, 40));

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
        hGroup.addGroup(layout.createParallelGroup()  // <-- Adicionei esta linha
                .addComponent(campoIdJogador)
                .addComponent(campoIdTime)
                .addComponent(campoNome)
                .addComponent(campoNumeroJogador)
                .addComponent(campoBloqueiosFeitos)
                .addComponent(campoDefesasFeitas)
                .addComponent(campoSaquesFeitos)
                .addComponent(campoPontosFeitos));  // <-- Adicionei esta linha
        layout.setHorizontalGroup(hGroup);

        // Configuração das verticais
        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelIdJogador)
                .addComponent(campoIdJogador));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelIdTime)
                .addComponent(campoIdTime));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelNome)
                .addComponent(campoNome));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelNumeroJogador)
                .addComponent(campoNumeroJogador));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelBloqueiosFeitos)
                .addComponent(campoBloqueiosFeitos));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelDefesasFeitas)
                .addComponent(campoDefesasFeitas));
        vGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(labelSaquesFeitos)
                .addComponent(campoSaquesFeitos));
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
}

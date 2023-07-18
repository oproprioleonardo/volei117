package com.magistrados.graph.managertimes;

import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerTimesFrame extends JFrame {
    private JPanel inputPanel;
    private JPanel buttonsPanel;
    Font font = new Font("Roboto", Font.BOLD, 20);

    public ManagerTimesFrame() throws HeadlessException {
        super("Gerenciador de Times");
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
        this.createButton(buttonPanel, "Adicionar Time", e -> {


        }, false);
        this.createButton(buttonPanel, "Visualizar Time", e -> {


        }, true);
        this.createButton(buttonPanel, "Editar Time", e -> {


        }, true);
        this.createButton(buttonPanel, "Remover Time", e -> {


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
        JLabel labelIdTime =  createLabel(font,"ID do Time:");
        JLabel labelVitoriasTime = createLabel(font, "Nome do Time: ");
        JLabel labelVitorias = createLabel(font, "Número de Vitórias: ");
        JLabel labelDerrotas = createLabel(font,"Número de Derrotas:");

        //declarando JTextField
        JTextField campoIdTime = createInput(300,40);
        JTextField campoNomeTime =  createInput(300,40);
        JTextField campoVitorias = createInput(300,40);
        JTextField campoDerrotas = createInput(300,40);

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

}

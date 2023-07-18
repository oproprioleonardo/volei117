package com.magistrados.graph.screens.managertimes;

import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.inputs.DefaultInput;
import com.magistrados.graph.labels.DefaultLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ManagerTimesFrame extends JFrame {
    private JPanel inputPanel;
    private JPanel buttonsPanel;
    private JPanel buttonPanel;
    private JPanel paddingPanel;
    private JLabel labelIdTime;
    private JLabel labelVitoriasTime;
    private JLabel labelVitorias;
    private JLabel labelDerrotas;
    private JTextField campoIdTime;
    private JTextField campoNomeTime;
    private JTextField campoVitorias;
    private JTextField campoDerrotas;

    Font font = new Font("Roboto", Font.BOLD, 20);

    public ManagerTimesFrame() throws HeadlessException {
        super("Gerenciador de Times");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());


        //empilha tudo
        this.pack();
        this.setLocationRelativeTo(null);

    }

    public void initComponents(){
        //Criando Jlabels
        labelIdTime =  createLabel(font,"ID do Time:");
        labelVitoriasTime = createLabel(font, "Nome do Time: ");
        labelVitorias = createLabel(font, "Número de Vitórias: ");
        labelDerrotas = createLabel(font,"Número de Derrotas:");


        //Criando JTextField
        campoIdTime = createInput(300,40);
        campoNomeTime =  createInput(300,40);
        campoVitorias = createInput(300,40);
        campoDerrotas = createInput(300,40);


        //Criando Painéis com seus repectivos layouts
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBackground(Color.decode("#171717"));

        paddingPanel = new JPanel(new BorderLayout());
        paddingPanel.setBorder(BorderFactory.createEmptyBorder(100, 25, 100, 25));
        paddingPanel.setBackground(Color.decode("#171717"));

        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBackground(Color.decode("#171717"));


        // Adicionando o painel de preenchimento (com os botões) ao centro do painel principal
        this.add(buttonsPanel, BorderLayout.EAST);
        this.add(inputPanel, BorderLayout.WEST);
        buttonsPanel.add(paddingPanel, BorderLayout.CENTER);
        paddingPanel.add(buttonPanel, BorderLayout.CENTER);


        // Criando botões CRUD
        this.createButton(buttonPanel, "Adicionar Time", e -> {


        }, false);
        this.createButton(buttonPanel, "Visualizar Time", e -> {


        }, true);
        this.createButton(buttonPanel, "Editar Time", e -> {


        }, true);
        this.createButton(buttonPanel, "Remover Time", e -> {


        }, true);


        // Configuração do GroupLayout
        GroupLayout layout = new GroupLayout(inputPanel);
        inputPanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup(); // Configuração das horizontais
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

        GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup(); // Configuração das verticais
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

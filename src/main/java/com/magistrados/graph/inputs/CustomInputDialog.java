package com.magistrados.graph.inputs;

import com.magistrados.graph.buttons.DefaultButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*public class CustomInputDialog extends JDialog {
        private static final Font font = new Font("Roboto", Font.BOLD, 15);
        private JTextField textField;
        private JButton okButton;
        private JButton cancelButton;
        private String inputText;
        private JPanel mainPanel;
        private JPanel buttonPanel;
        private JPanel inputPanel;

        public CustomInputDialog(JFrame parentFrame, String title) {
            super(parentFrame, title, true); // Set modal to block input to other windows
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setSize(300, 150);
            setLocationRelativeTo(parentFrame);

            initComponents();
        }

        private void initComponents() {
            textField = new JTextField(20);

            okButton = new DefaultButton("OK", e -> {
                inputText = textField.getText();
                dispose(); // Close the dialog
            });
            cancelButton = new DefaultButton("Cancel", e -> {
                inputText = null;
                dispose(); // Close the dialog
            });

            //Instanciando Paineis
            buttonPanel = new JPanel();
            buttonPanel.setBackground(Color.decode("#171717"));

            inputPanel = new JPanel();

            mainPanel = new JPanel(new BorderLayout());

            this.setBackground(Color.decode("#171717"));

            //Adicionando Paineis
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            inputPanel.add(textField);

            mainPanel.add(inputPanel, BorderLayout.NORTH);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            add(mainPanel, BorderLayout.CENTER);

            //Layout
            setLayout(new BorderLayout());
        }

        public String getInputText() {
            return inputText;
        }



}*/
public class CustomInputDialog extends JDialog {

    private JTextField textField;
    private JButton okButton;
    private JButton cancelButton;
    private String inputValue;

    public CustomInputDialog(JFrame parent) {
        super(parent, "Visualizar partida", true);

        textField = new JTextField(20);

        okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            inputValue = textField.getText();
            dispose();
        });

        cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> {
            inputValue = null; // Define o valor como null caso o usuário cancele
            dispose();
        });

        JPanel panel = new JPanel();
        panel.add(new JLabel("Digite o ID da partida:"));
        panel.add(textField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        getContentPane().add(panel);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    // Método para obter o valor digitado pelo usuário
    public String getInputValue() {
        return inputValue;
    }
}


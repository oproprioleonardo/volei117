package com.magistrados.graph.inputs;

import com.magistrados.graph.buttons.DefaultButton;
import com.magistrados.graph.listeners.SmoothColorTransitionMouseListener;

import javax.swing.*;
import javax.swing.border.Border;
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

            okButton.setBackground(Color.decode("#171717"));

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
    private final Font font = new Font("Roboto", Font.PLAIN, 12);
    public final Color BTN_COLOR = new Color(59, 89, 152);
    public final Color BTN_HOVER_COLOR = new Color(91, 155, 213);
    private final Border defaultBorder = BorderFactory.createLineBorder(new Color(74, 76, 80));

    public CustomInputDialog(JFrame parent) {
        super(parent, "Visualizar partida", true);

        textField = new JTextField(20);
        textField.setFont(font);
        textField.setFont(font);
        textField.setForeground(Color.WHITE);
        textField.setBackground(new Color(74, 76, 80));
        textField.setOpaque(true);
        textField.setBorder(defaultBorder);

        okButton = new JButton("OK");
        okButton.setForeground(Color.WHITE);
        okButton.setBackground(BTN_COLOR);
        okButton.setFocusPainted(false);
        okButton.setOpaque(true);
        okButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        okButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        okButton.addMouseListener(new SmoothColorTransitionMouseListener(okButton, BTN_HOVER_COLOR, BTN_COLOR));
        okButton.addActionListener(e -> {
            inputValue = textField.getText();
            dispose();
        });

        cancelButton = new JButton("Cancelar");
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(BTN_COLOR);
        cancelButton.setFocusPainted(false);
        cancelButton.setOpaque(true);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cancelButton.addMouseListener(new SmoothColorTransitionMouseListener(cancelButton, BTN_HOVER_COLOR, BTN_COLOR));
        cancelButton.addActionListener(e -> {
            inputValue = null; // Define o valor como null caso o usuário cancele
            dispose();
        });

        JPanel panel = new JPanel();
        panel.setBackground(new Color(0,0,0));
        JLabel idPartida = new JLabel("Digite o ID da partida:");
        idPartida.setForeground(new Color(255,255,255));
        panel.add(idPartida);
        textField.setCaretColor(new Color(0, 178,255));
        panel.add(textField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0,0,0));
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


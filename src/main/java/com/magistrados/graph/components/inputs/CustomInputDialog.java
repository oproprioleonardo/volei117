package com.magistrados.graph.components.inputs;

import com.magistrados.graph.listeners.SmoothColorTransitionMouseListener;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CustomInputDialog extends JDialog {

    public final Color BTN_COLOR = new Color(59, 89, 152);
    public final Color BTN_HOVER_COLOR = new Color(91, 155, 213);
    private String inputValue;

    public CustomInputDialog(JFrame parent) {
        super(parent, "Visualizar partida", true);

        final JTextField textField = new JTextField(20);
        final Font font = new Font("Roboto", Font.PLAIN, 12);
        textField.setFont(font);
        textField.setFont(font);
        textField.setForeground(Color.WHITE);
        textField.setBackground(new Color(74, 76, 80));
        textField.setOpaque(true);
        final Border defaultBorder = BorderFactory.createLineBorder(new Color(74, 76, 80));
        textField.setBorder(defaultBorder);

        final JButton okButton = new JButton("OK");
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

        final JButton cancelButton = new JButton("Cancelar");
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

        final JPanel panel = new JPanel();
        panel.setBackground(new Color(0, 0, 0));
        final JLabel idPartida = new JLabel("Digite o ID da partida:");
        idPartida.setForeground(new Color(255, 255, 255));
        panel.add(idPartida);
        textField.setCaretColor(new Color(0, 178, 255));
        panel.add(textField);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 0, 0));
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


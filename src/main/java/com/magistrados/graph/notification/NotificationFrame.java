package com.magistrados.graph.notification;

import com.magistrados.graph.buttons.DefaultButton;

import javax.swing.*;
import java.awt.*;

public class NotificationFrame extends JDialog {

    public NotificationFrame(String title, String message) {
        setTitle(title);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Create a custom message
        final JLabel messageLabel = new JLabel("<html><p style='font-size: 14pt;font-family: 'Roboto';>" + message + "</p></html>");
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create the OK button
        final JButton okButton = new DefaultButton("OK", e -> dispose(),
                new Font("Roboto", Font.PLAIN, 15),
                new Dimension(100, 50),
                BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Panel to hold the message
        final JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(214, 214, 214)); // Set custom background color
        mainPanel.add(messageLabel, BorderLayout.CENTER);

        // Panel to hold the OK button
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(214, 214, 214)); // Set custom background color
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        buttonPanel.add(okButton);

        // Add everything to the main dialog
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Set the preferred size of the dialog (adjust as needed)
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

}

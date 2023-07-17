package com.magistrados.graph.buttons;

import com.magistrados.graph.listeners.SmoothColorTransitionMouseListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DefaultButton extends JButton {

    public final Color BTN_COLOR = new Color(59, 89, 152);
    public final Color BTN_HOVER_COLOR = new Color(91, 155, 213);

    public DefaultButton(String text, ActionListener listener) {
        Font font = new Font("Roboto", Font.BOLD, 20);
        this.setText(text);
        this.setFont(font);
        this.setMinimumSize(new Dimension(300, 100));
        this.setMaximumSize(new Dimension(300, 100));
        this.setForeground(Color.WHITE);
        this.setBackground(BTN_COLOR);
        this.setFocusPainted(false);
        this.setOpaque(true);
        this.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));

        this.addMouseListener(new SmoothColorTransitionMouseListener(this, BTN_HOVER_COLOR, BTN_COLOR));
        this.addActionListener(listener);

    }
}

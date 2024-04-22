package com.magistrados.graph.components.labels;

import javax.swing.*;
import java.awt.*;

public class DefaultLabel extends JLabel {

    public DefaultLabel(Font font, String text) {
        this.setText(text);
        this.setFont(font);
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }

    public DefaultLabel(Font font, String text, int size) {
        this.setText(text);
        this.setFont(new Font(font.getFontName(), font.getStyle(), size));
        this.setForeground(Color.WHITE);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }

    public DefaultLabel(Font font, String text, Dimension dimension, int alignment) {
        this.setText(text);
        this.setHorizontalAlignment(alignment);
        this.setMaximumSize(dimension);
        this.setMinimumSize(dimension);
        this.setFont(font);
        this.setForeground(Color.WHITE);
    }

}

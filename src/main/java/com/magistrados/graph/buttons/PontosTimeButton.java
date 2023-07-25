package com.magistrados.graph.buttons;

import com.magistrados.graph.screens.match.MatchManager;
import com.magistrados.graph.screens.match.MatchManagerFrame;
import com.magistrados.graph.screens.match.enums.TeamID;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PontosTimeButton extends DefaultButton {

    private static final Font btnFont = new Font("Roboto", Font.BOLD, 14);

    public PontosTimeButton(String text, MatchManagerFrame matchFrame, TeamID teamID, OperatorButton operatorButton) {
        super(text, null);
        this.setFont(btnFont);
        this.setMinimumSize(new Dimension(100, 45));
        this.setMaximumSize(new Dimension(100, 45));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.addActionListener(e -> {

        });
    }

    public PontosTimeButton(String text, Font font, MatchManager match, OperatorButton operatorButton) {
        super(text, null, font);
        this.setMinimumSize(new Dimension(100, 45));
        this.setMaximumSize(new Dimension(100, 45));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.addActionListener(e -> {

        });
    }

    public PontosTimeButton(String text, Font font, Dimension dimension, MatchManager match, OperatorButton operatorButton) {
        super(text, null, font, dimension);
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        this.addActionListener(e -> {

        });
    }

    public PontosTimeButton(String text, Font font, Dimension dimension, Border border, MatchManager match, OperatorButton operatorButton) {
        super(text, null, font, dimension, border);
        this.addActionListener(e -> {

        });
    }
}

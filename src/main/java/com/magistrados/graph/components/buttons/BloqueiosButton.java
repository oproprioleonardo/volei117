package com.magistrados.graph.components.buttons;

import com.magistrados.models.MatchPlayerStats;

import javax.swing.*;
import java.awt.*;

public class BloqueiosButton extends DefaultButton {

    private static final Font btnFont = new Font("Roboto", Font.BOLD, 14);

    public BloqueiosButton(String text, MatchPlayerStats stats, OperatorButton operatorButton) {
        super(text, null);
        this.setFont(btnFont);
        this.setMinimumSize(new Dimension(100, 45));
        this.setMaximumSize(new Dimension(100, 45));
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.addActionListener(e -> {
            if (operatorButton.isSomando())
                stats.addBloqueio();
            else stats.removeBloqueio();
            this.setText("(" + stats.getQuantidadeBloqueios() + ")");
        });
    }

}

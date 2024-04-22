package com.magistrados.graph.components.buttons;

import javax.swing.*;
import java.awt.*;

public class ViewerButton extends DefaultButton{
    private static final Font btnFont = new Font("Roboto", Font.BOLD, 14);

    public ViewerButton(String text) {
        super(text, null);
        this.setFont(btnFont);
        this.setMinimumSize(new Dimension(100, 45));
        this.setMaximumSize(new Dimension(100, 45));
        this.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
    }
}

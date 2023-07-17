package com.magistrados.graph.labels;

import javax.swing.*;
import java.awt.*;

public class DefaultLabel extends JLabel {
    public DefaultLabel(Font font, String text){
        this.setText(text);
        this.setFont(font);
        this.setForeground(Color.WHITE);
    }


}

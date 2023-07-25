package com.magistrados.graph.buttons;

import javax.swing.border.Border;
import java.awt.*;

public class OperatorButton extends DefaultButton {

    private boolean somando = true;

    public OperatorButton(String text) {
        super(text, null);
        this.addActionListener(e -> {
            somando = !somando;
            this.setText(somando ? "Somando" : "Subtraindo");
        });
    }

    public OperatorButton(String text, Font font) {
        super(text, null, font);
        this.addActionListener(e -> {
            somando = !somando;
            this.setText(somando ? "Somando" : "Subtraindo");
        });
    }

    public OperatorButton(String text, Font font, Dimension dimension) {
        super(text, null, font, dimension);
        this.addActionListener(e -> {
            somando = !somando;
            this.setText(somando ? "Somando" : "Subtraindo");
        });
    }

    public OperatorButton(String text, Font font, Dimension dimension, Border border) {
        super(text, null, font, dimension, border);
        this.addActionListener(e -> {
            somando = !somando;
            this.setText(somando ? "Somando" : "Subtraindo");
        });
    }

    public boolean isSomando() {
        return somando;
    }
}

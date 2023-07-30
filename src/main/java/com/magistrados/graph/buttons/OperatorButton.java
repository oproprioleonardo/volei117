package com.magistrados.graph.buttons;

import com.magistrados.graph.screens.match.MatchManagerFrame;

import javax.swing.*;
import java.awt.*;

public class OperatorButton extends DefaultButton {

    private boolean somando = true;

    public OperatorButton(String text, MatchManagerFrame matchManagerFrame) {
        super(text, null);
        this.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setVerticalAlignment(SwingConstants.CENTER);
        this.setMaximumSize(new Dimension(230, 60));
        this.setMinimumSize(new Dimension(230, 60));
        this.addActionListener(e -> {
            somando = !somando;
            this.setText(somando ? "Somando" : "Subtraindo");
            if (!somando) matchManagerFrame.destravarTodosBotoes();
            if (somando && matchManagerFrame.btnConfirmarSet != null) {
                matchManagerFrame.travarTodosBotoes();
                matchManagerFrame.ativarBotoesEssenciais();
                matchManagerFrame.btnConfirmarSet.setEnabled(true);
            }
        });
    }

    public boolean isSomando() {
        return somando;
    }
}

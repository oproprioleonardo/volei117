package com.magistrados.graph.buttons;

import com.magistrados.graph.screens.match.MatchManagerFrame;

public class OperatorButton extends DefaultButton {

    private boolean somando = true;

    public OperatorButton(String text, MatchManagerFrame matchManagerFrame) {
        super(text, null);
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

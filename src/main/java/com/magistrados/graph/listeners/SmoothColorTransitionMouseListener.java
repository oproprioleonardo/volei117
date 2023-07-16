package com.magistrados.graph.listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SmoothColorTransitionMouseListener extends MouseAdapter {

    private static final int TRANSITION_DURATION = 200;
    private final JButton button;
    private final Timer timerEnter;
    private final Timer timerExit;
    private final int steps;
    private int currentStep;

    public SmoothColorTransitionMouseListener(JButton button, Color targetColor, Color originalColor) {
        this.button = button;
        this.steps = TRANSITION_DURATION / 20; // 20 milissegundos por passo
        this.currentStep = 0;

        this.timerEnter = new Timer(TRANSITION_DURATION / steps, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateColorTransition(originalColor, targetColor, timerEnter);
            }
        });

        this.timerExit = new Timer(TRANSITION_DURATION / steps, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateColorTransition(targetColor, originalColor, timerExit);
            }
        });
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        timerExit.stop();
        timerEnter.start();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        timerEnter.stop();
        timerExit.start();
    }

    private void updateColorTransition(Color fromColor, Color toColor, Timer timer) {
        float alpha = (float) currentStep / (float) steps;
        int red = (int) (fromColor.getRed() + alpha * (toColor.getRed() - fromColor.getRed()));
        int green = (int) (fromColor.getGreen() + alpha * (toColor.getGreen() - fromColor.getGreen()));
        int blue = (int) (fromColor.getBlue() + alpha * (toColor.getBlue() - fromColor.getBlue()));
        button.setBackground(new Color(red, green, blue));

        currentStep++;
        if (currentStep >= steps) {
            timer.stop();
            currentStep = 0;
        }
    }
}

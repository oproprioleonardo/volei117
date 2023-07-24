package com.magistrados.graph.notification;

import java.awt.*;

public final class TrayDisplay {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TrayDisplay.class);

    private static boolean display(String title, String message, TrayIcon.MessageType messageType) {
        if (!SystemTray.isSupported()) {
            log.warn("SystemTray is not supported on this platform.");
            return false;
        }

        final Image image = Toolkit.getDefaultToolkit().createImage("default_icon.png");
        final SystemTray tray = SystemTray.getSystemTray();

        final TrayIcon trayIcon = new TrayIcon(image, "VôleiApp");

        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            log.warn("TrayIcon could not be added to the system tray.");
            e.printStackTrace();
            return false;
        }

        trayIcon.displayMessage(title, message, messageType);
        return true;
    }

    public static boolean none(String title, String message) {
        return display(title, message, TrayIcon.MessageType.NONE);
    }

    public static boolean info(String title, String message) {
        return display(title, message, TrayIcon.MessageType.INFO);
    }

    public static boolean error(String title, String message) {
        return display(title, message, TrayIcon.MessageType.ERROR);
    }

    public static boolean warning(String title, String message) {
        return display(title, message, TrayIcon.MessageType.WARNING);
    }

}

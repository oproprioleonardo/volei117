package com.magistrados.graph.screens.notification;

public final class Notifications {

    public static void info(String title, String message) {
        if (!TrayDisplay.info(title, message)) new NotificationFrame(title, message);
    }

    public static void warning(String title, String message) {
        if (!TrayDisplay.warning(title, message)) new NotificationFrame(title, message);
    }

    public static void error(String title, String message) {
        if (!TrayDisplay.error(title, message)) new NotificationFrame(title, message);
    }

    public static void none(String title, String message) {
        if (!TrayDisplay.none(title, message)) new NotificationFrame(title, message);
    }

    public static void info(String message) {
        if (!TrayDisplay.info("VôleiApp", message)) new NotificationFrame("VôleiApp", message);
    }

    public static void warning(String message) {
        if (!TrayDisplay.warning("VôleiApp", message)) new NotificationFrame("VôleiApp", message);
    }

    public static void error(String message) {
        if (!TrayDisplay.error("VôleiApp", message)) new NotificationFrame("VôleiApp", message);
    }

    public static void none(String message) {
        if (!TrayDisplay.none("VôleiApp", message)) new NotificationFrame("VôleiApp", message);
    }

}

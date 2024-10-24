package com.spectrasonic.NetherTeleport;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessageUtils {
    private MessageUtils() {
        // Private constructor to prevent instantiation
    }

    public static void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(colorize(MessageConstants.PREFIX + message));
    }

    public static void sendMessage(CommandSender sender, String message, Object... args) {
        sender.sendMessage(colorize(MessageConstants.PREFIX + String.format(message, args)));
    }

    public static void sendConsoleMessage(String message) {
        Bukkit.getConsoleSender().sendMessage(colorize(MessageConstants.PREFIX + message));
    }

    public static void sendStartupMessage(JavaPlugin plugin) {
        String[] messages = {
                MessageConstants.DIVIDER,
                MessageConstants.PREFIX + "&6" + plugin.getDescription().getName() + "&a Plugin Enabled!",
                MessageConstants.PREFIX + "&d" + "Version: &b" + plugin.getDescription().getVersion(),
                MessageConstants.PREFIX + "&6" + "Developed by: &c" + "Spectrasonic",
                MessageConstants.DIVIDER
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(colorize(message));
        }
    }

    public static void sendShutdownMessage(JavaPlugin plugin) {
        String[] messages = {
                MessageConstants.DIVIDER,
                MessageConstants.PREFIX + "&c" + plugin.getDescription().getName() + " plugin Disabled!",
                MessageConstants.DIVIDER
        };

        for (String message : messages) {
            Bukkit.getConsoleSender().sendMessage(colorize(message));
        }
    }

    private static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
package com.spectrasonic.NetherTeleport;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandManager implements CommandExecutor {

    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            MessageUtils.sendMessage(sender, "&cUse &6/ntp <reload/version>");
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.getConfigManager().reloadConfig();
            MessageUtils.sendMessage(sender, "&aConfig reloaded!");
            return true;
        } else if (args[0].equalsIgnoreCase("version")) {
            MessageUtils.sendMessage(sender, "&aVersion: &b" + "&d" + plugin.getDescription().getVersion());
            MessageUtils.sendMessage(sender, "&aDeveloped by: &c" + plugin.getDescription().getAuthors());
            return true;
        }

        MessageUtils.sendMessage(sender, "&cUse &6/ntp <reload/version>");
        return false;
    }
}

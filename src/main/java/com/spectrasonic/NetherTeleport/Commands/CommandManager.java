package com.spectrasonic.NetherTeleport.Commands;

import com.spectrasonic.NetherTeleport.Main;
import com.spectrasonic.NetherTeleport.Utils.MessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandManager implements CommandExecutor {

    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        switch (args[0]) {
            case "reload":
                plugin.getConfigManager().reloadConfig();
                MessageUtils.sendMessage(sender, "&aConfig reloaded!");
                break;
            case "version":
                MessageUtils.sendMessage(sender, "&aVersion: &b" + "&d" + plugin.getDescription().getVersion());
                MessageUtils.sendMessage(sender, "&aDeveloped by: &c" + plugin.getDescription().getAuthors());
                break;
            case "setspawn":
                if (sender instanceof Player player) {
                    plugin.getConfigManager().getConfig().set("nether_respawn.x", player.getLocation().getX());
                    plugin.getConfigManager().getConfig().set("nether_respawn.y", player.getLocation().getY());
                    plugin.getConfigManager().getConfig().set("nether_respawn.z", player.getLocation().getZ());
                    MessageUtils.sendMessage(sender, "&aRespawn point set!");
                    break;
                }
            default:
                MessageUtils.sendMessage(sender, "&cUse &6/ntp <reload/version>");
                break;
        }
        return false;
    }
}

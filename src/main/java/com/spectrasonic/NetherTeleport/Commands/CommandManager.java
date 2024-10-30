package com.spectrasonic.NetherTeleport.Commands;

import com.spectrasonic.NetherTeleport.Main;
import com.spectrasonic.NetherTeleport.Utils.MessageUtils;
import com.spectrasonic.NetherTeleport.Utils.SoundUtils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class CommandManager implements CommandExecutor {

    private final Main plugin;

    public CommandManager(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            // Check if no arguments were provided
            if (args.length == 0) {
                showHelp(sender);
                return true;
            }

            switch (args[0].toLowerCase()) {
                case "reload":
                    SoundUtils.playerSound((Player) sender, Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f, 1.0f);
                    handleReload(sender);

                    break;
                case "version":
                    SoundUtils.playerSound((Player) sender, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    handleVersion(sender);

                    break;
                case "setspawn":
                    SoundUtils.playerSound((Player) sender, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    handleSetSpawn(sender);
                    break;
                default:
                    showHelp(sender);
                    break;
            }
            return true;
        } catch (Exception e) {
            MessageUtils.sendMessage(sender, "&cAn error occurred while executing the command: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private void handleReload(CommandSender sender) {
        if (!sender.hasPermission("netherteleport.reload")) {
            MessageUtils.sendMessage(sender, "&cYou don't have permission to use this command!");
            return;
        }
        try {
            plugin.getConfigManager().reloadConfig();
            MessageUtils.sendMessage(sender, "&aConfiguration reloaded successfully!");
        } catch (Exception e) {
            MessageUtils.sendMessage(sender, "&cError reloading configuration: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void handleVersion(CommandSender sender) {
        if (!sender.hasPermission("netherteleport.version")) {
            MessageUtils.sendMessage(sender, "&cYou don't have permission to use this command!");
            return;
        }
        MessageUtils.sendMessage(sender, "&dVersion: &b" + plugin.getDescription().getVersion());
        MessageUtils.sendMessage(sender, "&aDeveloped by: &c" + String.join(", ", plugin.getDescription().getAuthors()));
    }

    private void handleSetSpawn(CommandSender sender) {
        if (!sender.hasPermission("netherteleport.setspawn")) {
            MessageUtils.sendMessage(sender, "&cYou don't have permission to use this command!");
            return;
        }

        if (!(sender instanceof Player player)) {
            MessageUtils.sendMessage(sender, "&cThis command can only be used by players!");
            return;
        }

        try {
            plugin.getConfigManager().getConfig().set("nether_respawn.x", player.getLocation().getX());
            plugin.getConfigManager().getConfig().set("nether_respawn.y", player.getLocation().getY());
            plugin.getConfigManager().getConfig().set("nether_respawn.z", player.getLocation().getZ());
            plugin.getConfigManager().getConfig().set("nether_respawn.world", Objects.requireNonNull(player.getLocation().getWorld()).getName());
            plugin.getConfigManager().saveConfig(); // Add this method to ConfigManager
            MessageUtils.sendMessage(sender, "&aRespawn point set successfully!");
        } catch (Exception e) {
            MessageUtils.sendMessage(sender, "&cError setting spawn point: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showHelp(CommandSender sender) {
        MessageUtils.sendMessage(sender, "&6=== NetherTeleport Help ===");
        MessageUtils.sendMessage(sender, "&6/ntp reload &7- Reload the configuration");
        MessageUtils.sendMessage(sender, "&6/ntp version &7- Show plugin version");
        MessageUtils.sendMessage(sender, "&6/ntp setspawn &7- Set the nether respawn point");
    }
}
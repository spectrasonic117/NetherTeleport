package com.spectrasonic.NetherTeleport;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.ChatColor;

public class PlayerDeathListener implements Listener {

    private final Main plugin;

    public PlayerDeathListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        plugin.getServer().broadcastMessage( ChatColor.GOLD + player.getName() + ChatColor.RED +  " has died and will be teleported to the Nether.");

        // Set player to spectator mode
        player.setGameMode(GameMode.SPECTATOR);

        // Wait 3 seconds before teleporting
        Bukkit.getScheduler().runTaskLater(plugin, () -> teleportToNether(player), 60L); // 60 ticks = 3 seconds
    }

    private void teleportToNether(Player player) {
        World nether = Bukkit.getWorld("world_nether");
        if (nether != null) {
            double x = plugin.getConfigManager().getConfig().getDouble("nether_coordinates.x");
            double y = plugin.getConfigManager().getConfig().getDouble("nether_coordinates.y");
            double z = plugin.getConfigManager().getConfig().getDouble("nether_coordinates.z");
            Location netherLocation = new Location(nether, x, y, z);
            player.teleport(netherLocation);
            player.setGameMode(GameMode.SURVIVAL);
        }
    }
}

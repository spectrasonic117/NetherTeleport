package com.spectrasonic.NetherTeleport.Listeners;

import com.spectrasonic.NetherTeleport.Main;
import com.spectrasonic.NetherTeleport.Utils.SoundUtils;
import com.spectrasonic.NetherTeleport.Utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    int seconds= 3;
    int mcTick = 20;

    int delayRespawn = seconds * mcTick;

    private final Main plugin;

    public PlayerDeathListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        MessageUtils.broadcastMessage("&6" + player.getName() + "&c has died and will be teleported to the Nether.");


        player.setGameMode(GameMode.SPECTATOR);

        Bukkit.getScheduler().runTaskLater(plugin, () -> teleportToNether(player), delayRespawn);
    }

    private void teleportToNether(Player player) {
        World nether = Bukkit.getWorld("world_nether");
        if (nether != null) {
            double x = plugin.getConfigManager().getConfig().getDouble("nether_respawn.x");
            double y = plugin.getConfigManager().getConfig().getDouble("nether_respawn.y");
            double z = plugin.getConfigManager().getConfig().getDouble("nether_respawn.z");
            Location netherLocation = new Location(nether, x, y, z);
            SoundUtils.broadcastPlayerSound(Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.5f);
            player.teleport(netherLocation);
            player.setGameMode(GameMode.SURVIVAL);
        }
    }
}

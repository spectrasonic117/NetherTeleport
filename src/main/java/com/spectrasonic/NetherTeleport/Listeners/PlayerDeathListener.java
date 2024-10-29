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
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerDeathListener implements Listener {

    int seconds= 3;
    int mcTick = 20;

    int delayRespawnSound = 24;

    int delayRespawn = seconds * mcTick;

    private final Main plugin;

    public PlayerDeathListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        // event.setKeepInventory(true);
        // event.setKeepLevel(true);
        // event.getDrops().clear();

        event.setDeathMessage(null);
        player.spigot().respawn();

        player.setGameMode(GameMode.SPECTATOR);
        Bukkit.getScheduler().runTaskLater(plugin, () -> teleportToNether(player), delayRespawn);
        MessageUtils.broadcastMessage("&6" + player.getName() + "&c has died and will be teleported to the Nether.");

        SoundUtils.broadcastPlayerSound(Sound.ENTITY_BLAZE_DEATH, 1.0f, 0.1f);
        new BukkitRunnable() {
            @Override
            public void run() {
                SoundUtils.broadcastPlayerSound(Sound.ENTITY_ZOMBIE_HORSE_DEATH, 1.0f, 0.7f);
            }
        }.runTaskLater(plugin, delayRespawnSound);

    }

    private void teleportToNether(Player player) {
        World nether = Bukkit.getWorld("world_nether");
        if (nether != null) {
            double x = plugin.getConfigManager().getConfig().getDouble("nether_respawn.x");
            double y = plugin.getConfigManager().getConfig().getDouble("nether_respawn.y");
            double z = plugin.getConfigManager().getConfig().getDouble("nether_respawn.z");
            Location netherLocation = new Location(nether, x, y, z);
            player.teleport(netherLocation);
            player.setGameMode(GameMode.SURVIVAL);
            SoundUtils.broadcastPlayerSound(Sound.ENTITY_ENDERMAN_TELEPORT, 1.0f, 0.5f);
        }
    }
}

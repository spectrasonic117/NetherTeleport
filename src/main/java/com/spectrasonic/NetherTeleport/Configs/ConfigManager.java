package com.spectrasonic.NetherTeleport.Configs;

import com.spectrasonic.NetherTeleport.Utils.MessageUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
// import java.io.IOException;

public class ConfigManager {

    private final JavaPlugin plugin;
    private File configFile;
    private FileConfiguration config;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void createConfig() {
        configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.getDataFolder().mkdirs();
            plugin.saveResource("config.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
        MessageUtils.sendMessage((CommandSender) plugin, "&aConfig reloaded!");
    }
}

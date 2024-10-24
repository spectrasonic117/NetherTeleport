package com.spectrasonic.NetherTeleport;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

    private ConfigManager configManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        configManager = new ConfigManager(this);
        configManager.createConfig();

        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);

        registerCommands();
        MessageUtils.sendStartupMessage(this);
    }

    @Override
    public void onDisable() {
        MessageUtils.sendShutdownMessage(this);
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    private void registerCommands() {
        Objects.requireNonNull(this.getCommand("ntp")).setExecutor(new CommandManager(this));
    }

}



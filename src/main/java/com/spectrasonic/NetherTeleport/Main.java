package com.spectrasonic.NetherTeleport;

import com.spectrasonic.NetherTeleport.Commands.CommandManager;
import com.spectrasonic.NetherTeleport.Commands.CommandTabCompleter;
import com.spectrasonic.NetherTeleport.Configs.ConfigManager;
import com.spectrasonic.NetherTeleport.Listeners.PlayerDeathListener;
import com.spectrasonic.NetherTeleport.Utils.MessageUtils;
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
        Objects.requireNonNull(this.getCommand("ntp")).setTabCompleter(new CommandTabCompleter());
        Objects.requireNonNull(this.getCommand("ntp")).setExecutor(new CommandManager(this));
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
    }

}



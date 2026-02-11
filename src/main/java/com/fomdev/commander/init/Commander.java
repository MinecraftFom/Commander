package com.fomdev.commander.init;

import com.fomdev.sasm.api.PluginClassUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class Commander extends JavaPlugin {
    @Override
    public void onEnable() {
        PluginClassUtil.rescanCache();
        try {
            com.fomdev.commander.api.Commander.syncCommand();
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, "An error occurred while loading commander " + e.getMessage());
        }
        getServer().getPluginManager().registerEvents
                (
                        new com.fomdev.commander.api.Commander(),
                        this
                );
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
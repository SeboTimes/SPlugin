package com.sebotimes;

import com.sebotimes.commands.ping;
import com.sebotimes.commands.sparticle;

import org.bukkit.plugin.java.JavaPlugin;

public class SPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("SPlugin loaded!");
        getServer().getPluginManager().registerEvents(new SEvents(this), this);

        getCommand("ping").setExecutor(new ping());
        getCommand("sparticle").setExecutor(new sparticle(this));
    }
}

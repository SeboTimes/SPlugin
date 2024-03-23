package com.sebotimes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class sparticle implements CommandExecutor {
    JavaPlugin plugin;
    public sparticle(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.hasPermission("splugin.sparticle") && strings.length > 0) {
            if (strings[0].equals("set") && strings.length == 3) {
                plugin.getConfig().set(strings[1], strings[2]);
                plugin.saveConfig();
                plugin.reloadConfig();
                return true;
            } else if (strings[0].equals("remove") && strings.length == 2) {
                plugin.getConfig().set(strings[1], null);
                plugin.saveConfig();
                plugin.reloadConfig();
                return true;
            }
        }
        return false;
    }
}

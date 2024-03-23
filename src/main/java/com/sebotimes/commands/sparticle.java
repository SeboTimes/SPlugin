package com.sebotimes.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class sparticle implements CommandExecutor {
    private final JavaPlugin plugin;
    public sparticle(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) return true;
        Player player = (Player) commandSender;

        if (strings[0].equals("set") && strings.length == 2) {
            plugin.getConfig().set("sparticle."+player.getName(), strings[1]);
            plugin.saveConfig();
            plugin.reloadConfig();
            return true;
        } else if (strings[0].equals("remove") && strings.length == 1) {
            plugin.getConfig().set("sparticle."+player.getName(), null);
            plugin.saveConfig();
            plugin.reloadConfig();
            return true;
        }
        return false;
    }
}

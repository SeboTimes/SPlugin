package com.sebotimes.commands;

import com.sebotimes.SFunctions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

public class smap implements CommandExecutor {
    JavaPlugin plugin;
    public smap(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player player = (Player) commandSender;
        if (strings.length == 1) {
            MapView mapView = player.getServer().createMap(player.getWorld());
            ItemStack map = SFunctions.createWebMap(mapView, strings[0]);
            player.getWorld().dropItem(player.getLocation(), map);
            return true;
        }
        return false;
    }
}

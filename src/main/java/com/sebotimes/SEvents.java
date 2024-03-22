package com.sebotimes;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class SEvents implements Listener {
    JavaPlugin plugin;
    public SEvents(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        e.getPlayer().setCompassTarget(e.getPlayer().getLastDeathLocation());

        String playerName = e.getPlayer().getName();
        if (plugin.getConfig().isSet(playerName)) {
            e.getPlayer().getWorld().spawnParticle(
                    Particle.valueOf((String)plugin.getConfig().get(playerName)),
                    e.getPlayer().getLocation().add(0, 0.1, 0),
                    0
            );
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.getEntity().getWorld().dropItemNaturally(
                e.getEntity().getLocation(),
                SFunctions.createPlayerHead(e.getEntity())
        );
    }
    @EventHandler
    public void onPlayerLevelChange(FurnaceSmeltEvent e) {
        e.getBlock().getWorld().playSound(e.getBlock().getLocation(), Sound.ENTITY_GENERIC_BURN, 1, 0);
    }

    @EventHandler
    public void onPlayerDropEvent(PlayerDropItemEvent e) {
        Item item = e.getItemDrop();
        ItemStack itemStack = item.getItemStack();
        if (itemStack.getType() == Material.PLAYER_HEAD) {
            SkullMeta itemMeta = (SkullMeta) itemStack.getItemMeta();
            Player owner = itemMeta.getOwningPlayer().getPlayer();
            ItemStack head = SFunctions.createPlayerHead(owner);
            item.setItemStack(head);
        }
    }
}

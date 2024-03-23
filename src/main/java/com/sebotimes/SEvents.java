package com.sebotimes;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.Configuration;
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

public class SEvents implements Listener {
    private final Configuration config;
    public SEvents(Configuration config) {
        this.config = config;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        e.getPlayer().setCompassTarget(e.getPlayer().getLastDeathLocation());

        String playerName = e.getPlayer().getName();
        if (config.isSet("sparticle."+playerName)) {
            e.getPlayer().getWorld().spawnParticle(
                Particle.valueOf(config.getString("sparticle."+playerName)),
                e.getPlayer().getLocation().add(0, 0.1, 0),
                0
            );
        }
    }
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player killer = e.getEntity().getKiller();
        if (killer != null) {
            e.setDeathMessage(String.format("%s beheaded %s", killer.getName(), e.getEntity().getName()));

            e.getEntity().getWorld().dropItemNaturally(
                    e.getEntity().getLocation(),
                    SFunctions.createPlayerHead(e.getEntity())
            );
        }
    }
    @EventHandler
    public void onFurnaceSmelt(FurnaceSmeltEvent e) {
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

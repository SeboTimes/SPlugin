package com.sebotimes;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.map.MapView;

public class SFunctions {
    public static ItemStack createPlayerHead(Player player) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta headMeta = (SkullMeta)head.getItemMeta();
        headMeta.setOwnerProfile(player.getPlayerProfile());
        head.setItemMeta(headMeta);

        return head;
    }

    public static ItemStack createWebMap(MapView mapView, String url) {
        mapView.getRenderers().clear();
        mapView.addRenderer(new urlRenderer(url));

        ItemStack map = new ItemStack(Material.FILLED_MAP);
        MapMeta mapMeta = (MapMeta) map.getItemMeta();
        mapMeta.setMapView(mapView);
        map.setItemMeta(mapMeta);

        return map;
    }
}


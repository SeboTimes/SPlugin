package com.sebotimes;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class urlRenderer extends MapRenderer {
    String url;

    public urlRenderer(String url) {
        this.url = url;
    }

    @Override
    public void render(MapView mapView, MapCanvas mapCanvas, Player player) {
        try {
            Image image = ImageIO.read(new URL(url));
            image = image.getScaledInstance(128, 128, Image.SCALE_FAST);
            mapCanvas.drawImage(0, 0, image);
        } catch (IOException e) {
            player.sendMessage("Invalid URL!");
        }
    }
}

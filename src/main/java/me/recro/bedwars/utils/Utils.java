package me.recro.bedwars.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;

public class Utils {

    private Utils() {

    }

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static String serializeLocation(Location location) {
        return location.getX() + " " + location.getY() + " " + location.getZ() + " " + location.getYaw() + " " + location.getPitch();
    }

}

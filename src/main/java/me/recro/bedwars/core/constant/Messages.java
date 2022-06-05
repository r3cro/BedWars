package me.recro.bedwars.core.constant;

import me.recro.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public enum Messages {
    GAME_NOT_RUNNING("&cGame is not currently running"),
    GAME_OVER("&cGame is over"),
    RELOADING("&aReloading...");

    private final String message;

    Messages(String message) {
        this.message = Utils.color(message);
    }

    public String toString() {
        return message;
    }

    public String toString(Object... parts) {
        return String.format(message, parts);
    }

    public void send(Player player) {
        player.sendMessage(message);
    }

    public void send(Player player, String replacement) {
        player.sendMessage(message.replace("%s", replacement));
    }

    public void send(Player player, Object... replacements) {
        player.sendMessage(String.format(message, replacements));
    }

    public void broadcast() {
        Bukkit.broadcastMessage(message);
    }

    public void broadcast(Object... replacements) {
        Bukkit.broadcastMessage(String.format(message, replacements));
    }
}
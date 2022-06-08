package me.recro.bedwars.listeners;

import lombok.AllArgsConstructor;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

@AllArgsConstructor
public class PlayerQuit implements Listener {

    private BedWars plugin;

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        final Player player = event.getPlayer();
        final GameArena gameArena = plugin.getGameArena();
        gameArena.purgePlayer(player);
    }

}

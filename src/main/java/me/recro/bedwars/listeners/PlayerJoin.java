package me.recro.bedwars.listeners;

import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerJoin implements Listener {

    private BedWars plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.setJoinMessage(null);
        final Player player = event.getPlayer();
        final GameArena gameArena = plugin.getGameArena();

        if(gameArena.isGameRunning()) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    //force spectator
                }
            }.runTaskLater(plugin, 10);
        } else {
            if(gameArena.shouldStart()) {
                gameArena.startCountdown();
            }
         }
    }
}

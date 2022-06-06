package me.recro.bedwars.listeners;

import lombok.AllArgsConstructor;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

@AllArgsConstructor
public class PlayerDeath implements Listener {

    private BedWars PLUGIN;

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(null);
        event.setDroppedExp(0);
        final Player player = event.getEntity();
        player.setHealth(20); // Skips respawn screen
        player.getInventory().clear();

        final GameArena gameArena = PLUGIN.getGameArena();
        if(!gameArena.isGameRunning()) {
            //send to lobby location
            return;
        }

        if(gameArena.isGameRunning()) {
            if(gameArena.shouldEnd()) {
                gameArena.endGame();
            }
         }
    }
}

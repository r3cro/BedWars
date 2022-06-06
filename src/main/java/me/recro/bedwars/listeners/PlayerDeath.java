package me.recro.bedwars.listeners;

import lombok.AllArgsConstructor;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

@AllArgsConstructor
public class PlayerDeath implements Listener {

    private BedWars PLUGIN;

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        final GameArena gameArena = PLUGIN.getGameArena();

        if(!gameArena.isGameRunning()) {
            event.setCancelled(true);
            return;
        }

        if (!(entity instanceof Player)) {
            return;
        }
        Player player = (Player) entity;
        if(event.getDamage() >= player.getHealth()) {
            if(!gameArena.isGameRunning()) {
                return;
            }
            if (gameArena.isGameRunning()) {
                event.setCancelled(true);
                //TODO check if bed is broken
                gameArena.respawn(player);

                //TODO if bed is broken add to spectator

                gameArena.addSpectator(player);
                Bukkit.broadcastMessage("" + gameArena.getBedPlayerCount());

                if(gameArena.shouldEnd()) {
                    gameArena.endGame();
                    return;
                }
            }
        }
    }
}

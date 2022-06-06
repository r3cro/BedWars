package me.recro.bedwars.listeners;

import lombok.AllArgsConstructor;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import me.recro.bedwars.core.constant.GameState;
import me.recro.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.scheduler.BukkitRunnable;

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
                player.setHealth(20);
                player.setFireTicks(0);
                player.setSaturation(20);

                player.setGameMode(GameMode.SPECTATOR);
                player.sendTitle(Utils.color("&cYou have died..."), Utils.color("&7Respawning in 5..."));
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.setGameMode(GameMode.SURVIVAL);
                    }
                }.runTaskLater(PLUGIN, 20*5);

                gameArena.purgePlayer(player);
                Bukkit.broadcastMessage("" + gameArena.getBedPlayerCount());

                if(gameArena.shouldEnd()) {
                    gameArena.endGame();
                    return;
                }
            }
        }
    }
}

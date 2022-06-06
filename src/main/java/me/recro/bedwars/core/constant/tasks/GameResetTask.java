package me.recro.bedwars.core.constant.tasks;

import lombok.AllArgsConstructor;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import me.recro.bedwars.core.constant.GameState;
import me.recro.bedwars.core.constant.Messages;
import me.recro.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

@AllArgsConstructor
public class GameResetTask extends BukkitRunnable {

    private BedWars plugin;
    private GameArena gameArena;

    @Override
    public void run() {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&aGame reset task, resetting"));
        Messages.GAME_OVER.broadcast();
        Player winner = Bukkit.getPlayer(gameArena.getLastAlive());

        Bukkit.broadcastMessage("Winner: " + winner.getName());

        new BukkitRunnable() {
            @Override
            public void run() {
                gameArena.setGameState(GameState.WAITING);
                if (gameArena.shouldStart()) {
                    gameArena.startCountdown();
                } else {
                    Bukkit.getConsoleSender().sendMessage(Utils.color("&aGame reset task, waiting (shouldn't start)"));
                }
            }
        }.runTaskLater(plugin, 20 * 15);
    }
}

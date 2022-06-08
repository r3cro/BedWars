package me.recro.bedwars.core.constant.tasks;

import lombok.AllArgsConstructor;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import me.recro.bedwars.core.constant.GameState;
import me.recro.bedwars.core.constant.Messages;
import me.recro.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Map;

@AllArgsConstructor
public class GameResetBlocksTask extends BukkitRunnable {
    private BedWars plugin;
    private GameArena gameArena;

    @Override
    public void run() {
        Bukkit.getConsoleSender().sendMessage(Utils.color("&aGame reset blocks task"));

        new BukkitRunnable() {
            @Override
            public void run() {
                for(Block block : gameArena.placed) {
                    block.setType(Material.AIR);
//                    Bukkit.broadcastMessage(block.getType().toString());
                }
                gameArena.placed.clear();
            }
        }.runTaskLater(plugin, 40);
    }
}
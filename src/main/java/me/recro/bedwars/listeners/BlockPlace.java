package me.recro.bedwars.listeners;

import lombok.AllArgsConstructor;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

@AllArgsConstructor
public class BlockPlace implements Listener {

    private BedWars plugin;

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        final GameArena gameArena = plugin.getGameArena();
        if(!gameArena.isGameRunning()) return;
        gameArena.placed.add(event.getBlockPlaced());
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        final GameArena gameArena = plugin.getGameArena();

        if(!gameArena.isGameRunning()) return;

        if(gameArena.placed.contains(event.getBlock())) {
            gameArena.placed.remove(event.getBlock());
            event.setCancelled(false);

        } else {
            if(event.getBlock().getType() == Material.RED_BED) {
                event.setCancelled(false);
                Bukkit.broadcastMessage("Bed broken");
                return;
            }
            event.setCancelled(true);
        }
    }
}

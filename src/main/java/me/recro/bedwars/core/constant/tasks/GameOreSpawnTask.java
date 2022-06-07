package me.recro.bedwars.core.constant.tasks;

import me.recro.bedwars.BedWars;
import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class GameOreSpawnTask extends BukkitRunnable {

    private BedWars plugin;

    public GameOreSpawnTask(BedWars plugin) {
        this.plugin = plugin;
    }

    public static BukkitTask task;

    private Integer counter = 0;

    private void spawnItem(String worldName, ItemStack itemStack) {
        Location oreSpawnLocation = new Location(Bukkit.getWorld("world"), 0,66,0);
        Bukkit.getWorld(worldName).dropItem(oreSpawnLocation, itemStack);
    }

    @Override
    public void run() {
        task = new BukkitRunnable() {
            @Override
            public void run() {
                counter++;
                if(counter % 2 == 0) {
                    spawnItem("world", new ItemStack(Material.IRON_INGOT));
                }
                if(counter % 8 == 0) {
                    spawnItem("world", new ItemStack(Material.GOLD_INGOT));
                }
                if(counter % 16 == 0) {
                    spawnItem("world", new ItemStack(Material.DIAMOND));
                }
                if(counter % 32 == 0) {
                    spawnItem("world", new ItemStack(Material.EMERALD));
                }
            }
        }.runTaskTimer(plugin, 20, 20);
    }
}

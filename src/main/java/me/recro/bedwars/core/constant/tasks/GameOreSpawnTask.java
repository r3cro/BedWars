package me.recro.bedwars.core.constant.tasks;

import me.recro.bedwars.BedWars;
import org.bukkit.*;
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
        for(String key : plugin.getConfigFile().getConfigurationSection("generators", false)) {

            int x = plugin.getConfigFile().getInt("generators." + key + ".x");
            int y = plugin.getConfigFile().getInt("generators." + key + ".y");
            int z = plugin.getConfigFile().getInt("generators." + key + ".z");

            Location ore = new Location(Bukkit.getWorld(worldName), x,y,z);
            Bukkit.getWorld(worldName).dropItem(ore, itemStack);
        }
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

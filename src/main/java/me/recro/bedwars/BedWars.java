package me.recro.bedwars;

import lombok.Getter;
import me.recro.bedwars.core.GameArena;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class BedWars extends JavaPlugin {

    private GameArena gameArena;

    @Override
    public void onEnable() {
        // Plugin startup logic
        gameArena = new GameArena(this);

        World world = Bukkit.getWorlds().get(0);
        world.setSpawnFlags(false, false);
        world.setGameRuleValue("doMobSpawning", "false");

        for (Entity entity : world.getEntities()) {
            if(!(entity instanceof Player) && entity instanceof LivingEntity) {
                entity.remove();
            }
        }
        registerListeners();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
    }

}

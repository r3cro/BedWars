package me.recro.bedwars;

import lombok.Getter;
import me.recro.bedwars.commands.DevCommand;
import me.recro.bedwars.core.GameArena;
import me.recro.bedwars.core.constant.GameState;
import me.recro.bedwars.listeners.*;
import me.recro.bedwars.utils.DataFile;
import me.recro.bedwars.utils.menus.MenuManager;
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

    private DataFile configFile;

    @Override
    public void onEnable() {
        // Plugin startup logic
        gameArena = new GameArena(this);
        gameArena.setGameState(GameState.WAITING);

        World world = Bukkit.getWorlds().get(0);
        world.setSpawnFlags(false, false);
        world.setGameRuleValue("doMobSpawning", "false");

        for (Entity entity : world.getEntities()) {
            if(!(entity instanceof Player) && entity instanceof LivingEntity) {
                entity.remove();
            }
        }
        registerListeners();

        getCommand("dev").setExecutor(new DevCommand(this));

        this.configFile = new DataFile(this, "config");
        getConfigFile().save();
        MenuManager.setup(getServer(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerDeath(this), this);
        pluginManager.registerEvents(new PlayerJoin(this), this);
        pluginManager.registerEvents(new PlayerQuit(this), this);
        pluginManager.registerEvents(new ServerListener(), this);
    }

    public DataFile getConfigFile() {
        return this.configFile;
    }

}

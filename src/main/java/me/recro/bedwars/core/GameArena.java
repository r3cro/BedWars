package me.recro.bedwars.core;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.Setter;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.constant.GameState;
import me.recro.bedwars.core.constant.events.GameEndEvent;
import me.recro.bedwars.core.constant.events.GameResetEvent;
import me.recro.bedwars.core.constant.events.GameStartEvent;
import me.recro.bedwars.core.constant.tasks.GameResetTask;
import me.recro.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static org.bukkit.Bukkit.getWorlds;

public class GameArena {

    /*
    TODO:
        Color Teams
        Bed location
        kits
        generator/location/shop
     */

    private BedWars PLUGIN;

    @Getter
    @Setter
    private GameState gameState = GameState.WAITING;

    List<String> colorList = new ArrayList<>();

    //Not sure how to handle respawns yet
    private HashMap<UUID, Color> BED_PLAYER = new HashMap<>();
    private HashSet<UUID> DEAD_PLAYER = new HashSet<>();

    private int MINIMUM_PLAYERS = 4;

    public GameArena(BedWars plugin) {
        colorList.add("BLUE");
        colorList.add("BLACK");
        colorList.add("RED");
        colorList.add("GREEN");
    }

    public boolean isMinimumMet() {
        return Bukkit.getOnlinePlayers().size() >= MINIMUM_PLAYERS;
    }

    public boolean isGameRunning() {
        switch(gameState) {
            case RUNNING:
                return true;
            default:
                return false;
        }
    }

    public boolean shouldStart() {
        return gameState == GameState.WAITING && isMinimumMet();
    }

    public boolean shouldEnd() {
        return false;
    }

    public void purgePlayer(Player player) {

    }

    public void startCountdown() {
        gameState = GameState.STARTING;

        new BukkitRunnable() {
            int countdown = 30;
            @Override
            public void run() {
                if (countdown != 0) {
                    if(countdown == 30 || countdown == 20 || countdown == 10 || countdown <= 5 && countdown > 0) {
                      Bukkit.broadcastMessage(Utils.color("&eGame will start in &c" + countdown + " &eseconds."));
                    }
                    countdown--;
                } else {
                    cancel();
                    if(gameState == GameState.STARTING && isMinimumMet()) {
                        startGame();
                    } else {
                        gameState = GameState.WAITING;
                    }
                }
            }
        }.runTaskTimer(PLUGIN, 0, 20);
    }

    public void startGame() {
        Bukkit.getPluginManager().callEvent(new GameStartEvent());
        this.handleStart();
        Bukkit.getConsoleSender().sendMessage(Utils.color("&aStart game method"));
    }

    public void endGame() {
        if(!isGameRunning()) {
            Bukkit.getConsoleSender().sendMessage(Utils.color("&aFired end game (not running)"));
            return;
        }

        Bukkit.getConsoleSender().sendMessage(Utils.color("&aFired end game."));

        Bukkit.getPluginManager().callEvent(new GameEndEvent());

        new GameResetTask(PLUGIN, this).runTaskLater(PLUGIN, 20 * 5);
    }

    public void resetGame() {
        Bukkit.getPluginManager().callEvent(new GameResetEvent());
        this.handleReset();
    }

    public void handleStart() {
        if(!isMinimumMet()) {
            Bukkit.getConsoleSender().sendMessage(Utils.color("&aFired handle start (min not met)"));
            gameState = GameState.WAITING;
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.getInventory().clear();
                player.getInventory().setArmorContents(null);
            }
            return;
        }
        gameState = GameState.STARTING;

        Bukkit.getConsoleSender().sendMessage(Utils.color("&aFired handle start"));

        int index = 0; // use to loop through spawn points

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.setFireTicks(0);
            player.setHealth(20);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if(gameState==GameState.STARTING) {
                    gameState = GameState.RUNNING;
                } else {
                    Bukkit.getConsoleSender().sendMessage(Utils.color("&aFired handle start (game not working)"));
                }
            }
        }.runTaskLater(PLUGIN, 20 * 15);
    }

    public void handleReset() {

    }

}

package me.recro.bedwars.core;

import lombok.Getter;
import lombok.Setter;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.constant.GameState;
import me.recro.bedwars.core.constant.events.GameEndEvent;
import me.recro.bedwars.core.constant.events.GameResetEvent;
import me.recro.bedwars.core.constant.events.GameStartEvent;
import me.recro.bedwars.core.constant.tasks.GameResetTask;
import me.recro.bedwars.utils.ItemStackBuilder;
import me.recro.bedwars.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;


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

    public int MINIMUM_PLAYERS = 4;
    private HashSet<UUID> bedplayers = new HashSet<>();
    private ArrayList<Location> spawns = new ArrayList<>();

    public GameArena(BedWars plugin) {
        this.PLUGIN = plugin;
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
        return (bedplayers.size() == 1 && isGameRunning());
    }

    public void purgePlayer(Player player) {
        bedplayers.remove(player.getUniqueId());
    }

    public void addBedPlayer(Player player) {
        bedplayers.add(player.getUniqueId());

        player.setHealth(20);
        player.setFireTicks(0);

        for(PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
    }

    public int getBedPlayerCount() {
        return bedplayers.size();
    }

    private UUID WINNER;
    public UUID getLastAlive() {
        for(UUID val : bedplayers) {
            WINNER = val;
            break;
        }
        return WINNER;
    }

    public void giveDefaults(Player player) {
        PlayerInventory playerInventory = player.getInventory();

        playerInventory.clear();
        playerInventory.setArmorContents(null);
        playerInventory.addItem(new ItemStackBuilder(Material.WOODEN_SWORD).withEnchantment(Enchantment.DAMAGE_ALL, 50).build());
    }

    public void startCountdown() {
        gameState = GameState.STARTING;

        new BukkitRunnable() {
            int countdown = 15;
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
                        Bukkit.broadcastMessage(" " + isMinimumMet());
                    } else {
                        gameState = GameState.WAITING;
                        Bukkit.broadcastMessage(" " + isMinimumMet());
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
        MINIMUM_PLAYERS = 4;

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
        Bukkit.getConsoleSender().sendMessage(Utils.color(("&aGameState: " + gameState)));

        int index = 0; // use to loop through spawn point


        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
            player.setFireTicks(0);
            player.setHealth(20);

            addBedPlayer(player);
            giveDefaults(player);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if(gameState==GameState.STARTING) {
                    gameState = GameState.RUNNING;
                    Bukkit.getConsoleSender().sendMessage(Utils.color("&aGameState: " + gameState));
                } else {
                    Bukkit.getConsoleSender().sendMessage(Utils.color("&aFired handle start (game not working)"));
                    Bukkit.getConsoleSender().sendMessage(Utils.color("&aGameState: " + gameState));
                }
            }
        }.runTaskLater(PLUGIN, 20 * 3);

    }

    public void handleReset() {

    }

}

package me.recro.bedwars.core;

import lombok.Getter;
import lombok.Setter;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.constant.GameState;
import me.recro.bedwars.core.constant.tasks.GameOreSpawnTask;
import me.recro.bedwars.core.constant.tasks.GameResetTask;
import me.recro.bedwars.core.constant.events.*;
import me.recro.bedwars.utils.ItemStackBuilder;
import me.recro.bedwars.utils.Utils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
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

    private final BedWars PLUGIN;

    @Getter
    @Setter
    private GameState gameState = GameState.WAITING;

    public int MINIMUM_PLAYERS = 4;
    private final HashSet<UUID> bedplayers = new HashSet<>();
    private final HashSet<UUID> spectators = new HashSet<>();
    private final ArrayList<Location> spawns = new ArrayList<>();

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
        return (bedplayers.size() == 1 || bedplayers.size() == 0 && isGameRunning());
    }

    public void purgePlayer(Player player) {
        bedplayers.remove(player.getUniqueId());
        spectators.remove(player.getUniqueId());
    }

    public void addBedPlayer(Player player) {
        bedplayers.add(player.getUniqueId());

        player.setHealth(20);
        player.setFireTicks(0);

        for(PotionEffect potionEffect : player.getActivePotionEffects()) {
            player.removePotionEffect(potionEffect.getType());
        }
    }

    public void addSpectator(Player player) {
        spectators.add(player.getUniqueId());
        bedplayers.remove(player.getUniqueId());
    }

    public int getBedPlayerCount() {
        return bedplayers.size();
    }

    public void respawn(Player player) {

        Location previous = player.getLocation();
        Location respawn_point = new Location(player.getWorld(), 0, 255, 0);
        Location spawn = new Location(player.getWorld(),0, 66,0);

        player.setHealth(20);
        player.setFireTicks(0);
        player.setSaturation(20);

        player.teleport(respawn_point);
        player.setGameMode(GameMode.SPECTATOR);
        player.sendTitle(Utils.color("&cYou have died..."), Utils.color("&7Respawning in 5..."));
        new BukkitRunnable() {
            @Override
            public void run() {
                player.setGameMode(GameMode.SURVIVAL);
                if(!isGameRunning()) {
                    player.teleport(spawn);
                    return;
                }
                player.teleport(previous);
            }
        }.runTaskLater(PLUGIN, 20*5);
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
            int countdown = 6;
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
        GameOreSpawnTask.task.cancel();
        for(Entity entity : Bukkit.getWorld("world").getEntities()) {
            if (!(entity instanceof Player)) {
                entity.remove();
            }
        }

        new GameResetTask(PLUGIN, this).runTaskLater(PLUGIN, 20);
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

        for(String key : PLUGIN.getConfigFile().getConfigurationSection("shops", false)) {

            int x = PLUGIN.getConfigFile().getInt("shops." + key + ".x");
            int y = PLUGIN.getConfigFile().getInt("shops." + key + ".y");
            int z = PLUGIN.getConfigFile().getInt("shops." + key + ".z");

            World world = Bukkit.getWorld("world");
            Location loc = new Location(world ,x,y,z);
            Villager villager = (Villager) world.spawnEntity(loc, EntityType.VILLAGER);
            villager.setAware(false);
            villager.setCustomName(Utils.color("&aItem Shop"));
            villager.setCustomNameVisible(true);
        }

        new GameOreSpawnTask(PLUGIN).runTaskLater(PLUGIN, 20);
    }
}

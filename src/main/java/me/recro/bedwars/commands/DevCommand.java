package me.recro.bedwars.commands;

import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import me.recro.bedwars.core.constant.Messages;
import me.recro.bedwars.core.constant.menu.ShopMenu;
import me.recro.bedwars.core.constant.tasks.GameOreSpawnTask;
import me.recro.bedwars.utils.Utils;
import me.recro.bedwars.utils.execeptions.MenuManagerException;
import me.recro.bedwars.utils.execeptions.MenuManagerNotSetupException;
import me.recro.bedwars.utils.menus.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DevCommand implements CommandExecutor {

    private BedWars plugin;
    private boolean running = false;

    public DevCommand(final BedWars plugin) {
        this.plugin = plugin;
    }

    public static void cancelTestRunnable(GameOreSpawnTask testRunnable) {
        testRunnable.cancel();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player)) {
            return false;
        }

        Player player = (Player) sender;
        GameArena gameArena = plugin.getGameArena();

        if(args.length!=1){
            player.sendMessage("/dev start");
            player.sendMessage("/dev end");
            player.sendMessage("/dev menu");
            player.sendMessage("/dev oregen");
            player.sendMessage(Utils.color("&eGameState: &c" + gameArena.getGameState()));
            return true;
        }

        if(args[0].equalsIgnoreCase("start")) {
            if(!gameArena.isGameRunning()) {
                gameArena.MINIMUM_PLAYERS = 0;
                gameArena.startCountdown();
                Messages.GAME_STARTING.send(player);
                return true;
            }
        }
        if(args[0].equalsIgnoreCase("end")) {
            if(!gameArena.isGameRunning()) {
                Messages.GAME_NOT_RUNNING.send(player);
                return true;
            }
            gameArena.endGame();
            gameArena.MINIMUM_PLAYERS = 4;
            Messages.GAME_OVER.send(player);
            return true;
        }
        if(args[0].equalsIgnoreCase("menu")) {
            try {
                MenuManager.openMenu(ShopMenu.class, player);
                return true;
            } catch (MenuManagerException | MenuManagerNotSetupException e) {
                throw new RuntimeException(e);
            }
        }
        if(args[0].equalsIgnoreCase("oregen")) {

            if(running == false) {
                player.sendMessage(Utils.color("&eStarting Ore Gen"));
                new GameOreSpawnTask(plugin).runTaskLater(plugin, 20);
                running = true;
            } else if(running == true) {
                running = false;
                GameOreSpawnTask.task.cancel();
                player.sendMessage(ChatColor.RED + "Stopping Ore Gen");
            }
            return true;
        }
        return false;
    }
}

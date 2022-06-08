package me.recro.bedwars.commands;

import me.recro.bedwars.BedWars;
import me.recro.bedwars.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class GameCommand implements CommandExecutor {

    private BedWars plugin;
    private boolean running = false;

    public GameCommand(final BedWars plugin) {
        this.plugin = plugin;
    }

    private void addGenerator(String color, Player player) {
        plugin.getConfigFile().getConfiguration().set("generators." + color + ".x", player.getLocation().getX());
        plugin.getConfigFile().getConfiguration().set("generators." + color + ".y", player.getLocation().getY()+1);
        plugin.getConfigFile().getConfiguration().set("generators." + color + ".z", player.getLocation().getZ());
        plugin.getConfigFile().save();
        player.sendMessage(Utils.color("&eSet Generator " + color));
    }

    private void addShop(String color, Player player) {
        plugin.getConfigFile().getConfiguration().set("shops." + color + ".x", player.getLocation().getX());
        plugin.getConfigFile().getConfiguration().set("shops." + color + ".y", player.getLocation().getY());
        plugin.getConfigFile().getConfiguration().set("shops." + color + ".z", player.getLocation().getZ());
        plugin.getConfigFile().save();
        player.sendMessage(Utils.color("&eSet Shop " + color));
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if(args.length==1) {
            switch(args[0].toLowerCase()) {
                case "white":
                    addGenerator("white", player);
                    break;
                case "pink":
                    addGenerator("pink", player);
                    break;
                case "grey":
                    addGenerator("grey", player);
                    break;
                case "red":
                    addGenerator("red", player);
                    break;
                case "blue":
                    addGenerator("blue", player);
                    break;
                case "green":
                    addGenerator("green", player);
                    break;
                case "yellow":
                    addGenerator("yellow", player);
                    break;
                case "cyan":
                    addGenerator("cyan", player);
                    break;
                case "whiteshop":
                    addShop("white", player);
                    break;
                case "pinkshop":
                    addShop("pink", player);
                    break;
                case "greyshop":
                    addShop("grey", player);
                    break;
                case "redshop":
                    addShop("red", player);
                    break;
                case "blueshop":
                    addShop("blue", player);
                    break;
                case "greenshop":
                    addShop("green", player);
                    break;
                case "yellowshop":
                    addShop("yello", player);
                    break;
                case "cyanshop":
                    addShop("cyan", player);
                    break;
                default:
                    player.sendMessage("unknown arg");
                    break;
            }
        }

        return true;
    }
}

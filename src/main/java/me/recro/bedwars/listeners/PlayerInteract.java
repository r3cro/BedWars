package me.recro.bedwars.listeners;

import lombok.AllArgsConstructor;
import me.recro.bedwars.BedWars;
import me.recro.bedwars.core.GameArena;
import me.recro.bedwars.core.constant.menu.ShopMenu;
import me.recro.bedwars.utils.Utils;
import me.recro.bedwars.utils.execeptions.MenuManagerException;
import me.recro.bedwars.utils.execeptions.MenuManagerNotSetupException;
import me.recro.bedwars.utils.menus.MenuManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;


public class PlayerInteract implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent event) {

        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();

        if(entity.getName().equalsIgnoreCase(Utils.color("&aItem Shop"))) {
            try {
                MenuManager.openMenu(ShopMenu.class, player);
                return;
            } catch (MenuManagerException | MenuManagerNotSetupException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if(!(entity instanceof Player)) {
            event.setCancelled(true);
        }
    }

}

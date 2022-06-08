package me.recro.bedwars.core.constant.menu;

import me.recro.bedwars.utils.ItemStackBuilder;
import me.recro.bedwars.utils.Utils;
import me.recro.bedwars.utils.execeptions.MenuManagerException;
import me.recro.bedwars.utils.execeptions.MenuManagerNotSetupException;
import me.recro.bedwars.utils.menus.Menu;
import me.recro.bedwars.utils.menus.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ShopMenu extends Menu {

    public ShopMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }
    @Override
    public String getMenuName() {
        return "Shop";
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent event) throws MenuManagerNotSetupException, MenuManagerException {
        switch (event.getCurrentItem().getType()) {
            case STONE_SWORD:
                player.sendMessage("sword");
                break;
            case WHITE_WOOL:
                purcahseItems(Material.WHITE_WOOL, 16, Material.IRON_INGOT, 4, player);
                break;
            default:

                player.sendMessage(Utils.color("&cItem not found. Contact administration."));
        }
    }

    private void purcahseItems(Material purchaseItem, Integer amount, Material sellItem, Integer cost, Player player) {
        if(player.getInventory().contains(sellItem, cost)) {
            player.getInventory().removeItem(new ItemStackBuilder(sellItem).withAmount(cost).build());
            player.getInventory().addItem(new ItemStackBuilder(purchaseItem).withAmount(amount).build());
            player.sendMessage(Utils.color("&ePurchased &c" + amount + " &e" + purchaseItem.toString()));
        } else {
            player.sendMessage(Utils.color("&eYou do not have &c" + cost + " &e" + sellItem));
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack sword = new ItemStackBuilder(Material.STONE_SWORD).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack wool = new ItemStackBuilder(Material.WHITE_WOOL).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack shear = new ItemStackBuilder(Material.SHEARS).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack pickaxe = new ItemStackBuilder(Material.WOODEN_PICKAXE).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack axe = new ItemStackBuilder(Material.WOODEN_AXE).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack milk = new ItemStackBuilder(Material.MILK_BUCKET).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack water = new ItemStackBuilder(Material.WATER_BUCKET).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack ironsword = new ItemStackBuilder(Material.IRON_SWORD).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack wood = new ItemStackBuilder(Material.OAK_PLANKS).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack tnt = new ItemStackBuilder(Material.TNT).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack goldenapple = new ItemStackBuilder(Material.GOLDEN_APPLE).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack firecharge = new ItemStackBuilder(Material.FIRE_CHARGE).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack spawnegg = new ItemStackBuilder(Material.SKELETON_SPAWN_EGG).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack ironboots = new ItemStackBuilder(Material.IRON_BOOTS).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack enderpearl = new ItemStackBuilder(Material.ENDER_PEARL).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack endstone = new ItemStackBuilder(Material.END_STONE).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack glass = new ItemStackBuilder(Material.GLASS).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack potionone = new ItemStackBuilder(Material.POTION).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack potiontwo = new ItemStackBuilder(Material.POTION).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack snowball = new ItemStackBuilder(Material.SNOWBALL).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();
        ItemStack diamondboots = new ItemStackBuilder(Material.DIAMOND_BOOTS).withName("&aWool").withLore("&7Cost: &f4 Iron").withLore(" ").withLore("&7Great for bridging across").withLore("&7islands. Turns into your team's").withLore("&7color").withLore(" ").withLore("&eClick to purchase!").withAmount(1).build();

        inventory.setItem(19, sword);
        inventory.setItem(20, wool);
        inventory.setItem(21, shear);
        inventory.setItem(22, pickaxe);
        inventory.setItem(23, axe);
        inventory.setItem(24, milk);
        inventory.setItem(25, water);

        inventory.setItem(28, ironsword);
        inventory.setItem(29, wood);
        inventory.setItem(30, tnt);
        inventory.setItem(31, goldenapple);
        inventory.setItem(32, firecharge);
        inventory.setItem(33, spawnegg);
        inventory.setItem(34, ironboots);

        inventory.setItem(37, enderpearl);
        inventory.setItem(38, endstone);
        inventory.setItem(39, glass);
        inventory.setItem(40, potionone);
        inventory.setItem(41, potiontwo);
        inventory.setItem(42, snowball);
        inventory.setItem(43, diamondboots);
    }
}

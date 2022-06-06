package me.recro.bedwars.core.constant.menu;

import me.recro.bedwars.utils.ItemStackBuilder;
import me.recro.bedwars.utils.execeptions.MenuManagerException;
import me.recro.bedwars.utils.execeptions.MenuManagerNotSetupException;
import me.recro.bedwars.utils.menus.Menu;
import me.recro.bedwars.utils.menus.PlayerMenuUtility;
import org.bukkit.Material;
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
                player.sendMessage("white wool");
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack sword = new ItemStackBuilder(Material.STONE_SWORD).withName("balls").withAmount(1).build();
        ItemStack wool = new ItemStackBuilder(Material.WHITE_WOOL).withName("balls").withAmount(1).build();
        ItemStack shear = new ItemStackBuilder(Material.SHEARS).withName("balls").withAmount(1).build();
        ItemStack pickaxe = new ItemStackBuilder(Material.WOODEN_PICKAXE).withName("balls").withAmount(1).build();
        ItemStack axe = new ItemStackBuilder(Material.WOODEN_AXE).withName("balls").withAmount(1).build();
        ItemStack milk = new ItemStackBuilder(Material.MILK_BUCKET).withName("balls").withAmount(1).build();
        ItemStack water = new ItemStackBuilder(Material.WATER_BUCKET).withName("balls").withAmount(1).build();
        ItemStack ironsword = new ItemStackBuilder(Material.IRON_SWORD).withName("balls").withAmount(1).build();
        ItemStack wood = new ItemStackBuilder(Material.OAK_PLANKS).withName("balls").withAmount(1).build();
        ItemStack tnt = new ItemStackBuilder(Material.TNT).withName("balls").withAmount(1).build();
        ItemStack goldenapple = new ItemStackBuilder(Material.GOLDEN_APPLE).withName("balls").withAmount(1).build();
        ItemStack firecharge = new ItemStackBuilder(Material.FIRE_CHARGE).withName("balls").withAmount(1).build();
        ItemStack spawnegg = new ItemStackBuilder(Material.SKELETON_SPAWN_EGG).withName("balls").withAmount(1).build();
        ItemStack ironboots = new ItemStackBuilder(Material.IRON_BOOTS).withName("balls").withAmount(1).build();
        ItemStack enderpearl = new ItemStackBuilder(Material.ENDER_PEARL).withName("balls").withAmount(1).build();
        ItemStack endstone = new ItemStackBuilder(Material.END_STONE).withName("balls").withAmount(1).build();
        ItemStack glass = new ItemStackBuilder(Material.GLASS).withName("balls").withAmount(1).build();
        ItemStack potionone = new ItemStackBuilder(Material.POTION).withName("balls").withAmount(1).build();
        ItemStack potiontwo = new ItemStackBuilder(Material.POTION).withName("balls").withAmount(1).build();
        ItemStack snowball = new ItemStackBuilder(Material.SNOWBALL).withName("balls").withAmount(1).build();
        ItemStack diamondboots = new ItemStackBuilder(Material.DIAMOND_BOOTS).withName("balls").withAmount(1).build();

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

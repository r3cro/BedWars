package me.recro.bedwars.utils.menus;

import me.recro.bedwars.utils.ItemStackBuilder;
import me.recro.bedwars.utils.execeptions.MenuManagerException;
import me.recro.bedwars.utils.execeptions.MenuManagerNotSetupException;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public abstract class Menu implements InventoryHolder {

    protected PlayerMenuUtility playerMenuUtility;
    protected Player player;
    protected Inventory inventory;
    protected ItemStack FILLER_GLASS = new ItemStackBuilder(Material.BLACK_STAINED_GLASS_PANE).withName(" ").withAmount(1).build();

    public Menu(PlayerMenuUtility playerMenuUtility) {
        this.playerMenuUtility = playerMenuUtility;
        this.player = playerMenuUtility.getOwner();
    }

    public abstract String getMenuName();
    public abstract int getSlots();

    public abstract boolean cancelAllClicks();
    public abstract void handleMenu(InventoryClickEvent event) throws MenuManagerNotSetupException, MenuManagerException;

    public abstract void setMenuItems();

    public void open() {
        inventory = Bukkit.createInventory(this, getSlots(), getMenuName());

        this.setMenuItems();

        playerMenuUtility.getOwner().openInventory(inventory);
        playerMenuUtility.pushMenu(this);
    }

    public void back() throws MenuManagerException, MenuManagerNotSetupException {
        MenuManager.openMenu(playerMenuUtility.lastMenu().getClass(), playerMenuUtility.getOwner());
    }

    protected void reloadItems() {
        for (int i = 0; i < inventory.getSize(); i++) {
            inventory.setItem(i, null);
        }
        setMenuItems();
    }

    protected void reload() throws MenuManagerException, MenuManagerNotSetupException {
        player.closeInventory();
        MenuManager.openMenu(this.getClass(), player);
    }

    @Override
    public @NotNull Inventory getInventory() {
        return inventory;
    }

    public void setFillerGlass() {
        for (int i = 0; i < getSlots(); i++) {
            if(inventory.getItem(i) == null) {
                inventory.setItem(i, FILLER_GLASS);
            }
        }
    }

    public void setFillerGlass(ItemStack itemStack) {
        for(int i = 0; i < getSlots(); i++) {
            if(inventory.getItem(i) == null) {
                inventory.setItem(i, itemStack);
            }
        }
    }

}

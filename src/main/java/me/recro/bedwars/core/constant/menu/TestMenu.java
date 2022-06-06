package me.recro.bedwars.core.constant.menu;

import me.recro.bedwars.utils.ItemStackBuilder;
import me.recro.bedwars.utils.execeptions.MenuManagerException;
import me.recro.bedwars.utils.execeptions.MenuManagerNotSetupException;
import me.recro.bedwars.utils.menus.Menu;
import me.recro.bedwars.utils.menus.PlayerMenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class TestMenu extends Menu {

    public TestMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Test Menu";
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
    public void handleMenu(InventoryClickEvent event) throws MenuManagerException, MenuManagerNotSetupException {
        switch (event.getCurrentItem().getType()) {
            case PACKED_ICE:
                player.sendMessage("balls");
                break;
            case LAVA_BUCKET:
                player.sendMessage("cock");
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack balls = new ItemStackBuilder(Material.PACKED_ICE).withName("balls").withAmount(1).build();
        ItemStack cock = new ItemStackBuilder(Material.LAVA_BUCKET).withName("cock").withAmount(1).build();

        inventory.setItem(3, balls);
        inventory.setItem(5, cock);
        setFillerGlass();

    }

}

package me.recro.bedwars.utils.menus;

import me.recro.bedwars.utils.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public abstract class PaginatedMenu extends Menu{

    protected List<Object> data;

    protected int page = 0;
    protected int maxItemsPerPage = 28;
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    public abstract List<?> getData();
    public abstract void loopCode(Object object);

    @Nullable
    public abstract HashMap<Integer, ItemStack> getCustomMenuBorderItems();

    protected void addMenuBorder() {
        inventory.setItem(48, new ItemStackBuilder(Material.DARK_OAK_BUTTON).withName("&aLeft").withAmount(1).build());
        inventory.setItem(49, new ItemStackBuilder(Material.BARRIER).withName("&cClose").withAmount(1).build());
        inventory.setItem(50, new ItemStackBuilder(Material.DARK_OAK_BUTTON).withName("&cRight").withAmount(1).build());

        for(int i = 0; i < 10; i++) {
            if(inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }

        inventory.setItem(17, super.FILLER_GLASS);
        inventory.setItem(18, super.FILLER_GLASS);
        inventory.setItem(26, super.FILLER_GLASS);
        inventory.setItem(27, super.FILLER_GLASS);
        inventory.setItem(35, super.FILLER_GLASS);
        inventory.setItem(36, super.FILLER_GLASS);

        for (int i = 44; i < 54; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }

        if (getCustomMenuBorderItems() != null) {
            getCustomMenuBorderItems().forEach((integer, itemStack) -> inventory.setItem(integer, itemStack));
        }
    }

    @Override
    public void setMenuItems() {
        addMenuBorder();

        List<Object> data = (List<Object>) getData();

        if (data != null && !data.isEmpty()) {
            for (int i = 0; i < getMaxItemsPerPage(); i++) {
                index = getMaxItemsPerPage() * page + i;
                System.out.println(index);
                if (index >= data.size()) break;
                if (data.get(index) != null) {
                    loopCode(data.get(index)); //run the code defined by the user
                }
            }
        }
    }

    public boolean prevPage() {
        if (page == 0) {
            return false;
        } else {
            page = page - 1;
            reloadItems();
            return true;
        }
    }

    public boolean nextPage() {
        if (!((index + 1) >= getData().size())) {
            page = page + 1;
            reloadItems();
            return true;
        } else {
            return false;
        }
    }
    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }

}

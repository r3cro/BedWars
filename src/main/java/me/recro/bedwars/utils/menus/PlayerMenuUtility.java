package me.recro.bedwars.utils.menus;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Stack;

public class PlayerMenuUtility {

    private final Player owner;
    private final HashMap<String, Object> dataMap = new HashMap<>();
    private final Stack<Menu> history = new Stack<>();

    public PlayerMenuUtility(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public void setData(String identifier, Object data) {
        this.dataMap.put(identifier, data);
    }

    public void setData(Enum identifier, Object data){
        this.dataMap.put(identifier.toString(), data);
    }

    public Object getData(String identifier) {
        return this.dataMap.get(identifier);
    }

    public Object getData(Enum identifier) {
        return this.dataMap.get(identifier.toString());
    }

    public <T> T getData(String identifier, Class<T> classRef) {
        Object obj = this.dataMap.get(identifier);

        if(obj==null) {
            return null;
        } else {
            return classRef.cast(obj);
        }
    }

    public Menu lastMenu() {
        this.history.pop();
        return this.history.pop();
    }

    public void pushMenu(Menu menu) {
        this.history.push(menu);
    }

}

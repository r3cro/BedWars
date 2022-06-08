package me.recro.bedwars.listeners;

import lombok.AllArgsConstructor;
import me.recro.bedwars.BedWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

@AllArgsConstructor
public class ServerListener implements Listener {

    private BedWars plugin;

    @EventHandler
    public void onStarve(FoodLevelChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

}

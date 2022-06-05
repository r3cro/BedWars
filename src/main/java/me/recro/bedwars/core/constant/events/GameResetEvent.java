package me.recro.bedwars.core.constant.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.logging.Handler;

public final class GameResetEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

}

package top.jingwenmc.mcdndc.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Custom Event Framework
 */
public class MCDNDCEvent extends Event {
    private static final HandlerList handlers = new HandlerList();
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}

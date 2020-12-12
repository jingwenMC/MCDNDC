package top.jingwenmc.mcdndc.events;

import org.bukkit.event.Cancellable;
import top.jingwenmc.mcdndc.objects.GamePlayer;
/**
 * Custom Event that will call on a player changing his word
 */
public class NewWordEvent extends MCDNDCEvents implements Cancellable{
    Boolean canceled = false;
    boolean isNew = false;
    GamePlayer gamePlayer;

    public NewWordEvent(GamePlayer gamePlayer,boolean isNew) {
        this.gamePlayer = gamePlayer;
        this.isNew = isNew;
    }

    /**
     * Get player that changed the word
     * @return {@link GamePlayer} that changed the word
     */
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }

    public boolean isNew() {
        return isNew;
    }

    @Override
    public boolean isCancelled() {
        return canceled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.canceled = cancel;
    }
}
package top.jingwenmc.mcdndc.events;

import org.bukkit.event.Cancellable;
import top.jingwenmc.mcdndc.managers.GameManager;
import top.jingwenmc.mcdndc.util.GamePlayer;
/**
 * Custom Event that will call on a player changing his word
 */
public class NewWordEvent extends MCDNDCEvent {
    GamePlayer gamePlayer;

    public NewWordEvent(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    /**
     * Get player that changed the word
     * @return GamePlayer that changed the word
     */
    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}

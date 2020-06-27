package top.jingwenmc.mcdndc.events;

import top.jingwenmc.mcdndc.managers.GameManager;
import top.jingwenmc.mcdndc.util.GamePlayer;

public class NewWordEvent extends MCDNDCEvent {
    GamePlayer gamePlayer;
    public NewWordEvent(GamePlayer gamePlayer)
    {
        this.gamePlayer=gamePlayer;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
    }
}

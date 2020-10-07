package top.jingwenmc.mcdndc.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.jingwenmc.mcdndc.events.NewGameEvent;
import top.jingwenmc.mcdndc.util.UpdateUtil;

public class GameListener implements Listener {
    @EventHandler
    public void onNewGame(NewGameEvent event)
    {
        UpdateUtil.checkUpdateAsync();
    }
}

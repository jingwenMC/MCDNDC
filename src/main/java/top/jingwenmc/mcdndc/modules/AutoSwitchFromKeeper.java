package top.jingwenmc.mcdndc.modules;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.jingwenmc.mcdndc.events.NewWordEvent;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.util.GamePlayer;

import java.util.Objects;

public class AutoSwitchFromKeeper implements Listener {
    @EventHandler
    public void onNewWord(NewWordEvent newWordEvent)
    {
        if(main.getInstance().getConfigAccessor().getConfig().getBoolean("modules.auto_switch_from_keeper.enabled"))
        {
            if(newWordEvent.getGamePlayer().getScore()==main.getInstance().getConfigAccessor().getConfig().getInt("modules.auto_switch_from_keeper.required_score"))
            {
                GamePlayer gp = newWordEvent.getGamePlayer();
                newWordEvent.setCancelled(true);
                Bukkit.dispatchCommand(gp.getPlayer(),"wordkeeper get "+gp.getPlayer().getName());
                Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&',
                        Objects.requireNonNull(main.getInstance().getConfigAccessor().getConfig().getString("modules.auto_switch_from_keeper.msg_switch"))
                .replaceAll("%player",gp.getPlayer().getName())));
                gp.setScore(gp.getScore()-1);
            }
        }
    }
}

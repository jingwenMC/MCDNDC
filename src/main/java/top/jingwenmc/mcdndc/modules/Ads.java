package top.jingwenmc.mcdndc.modules;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import top.jingwenmc.mcdndc.events.NewGameEvent;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class Ads implements Listener {
    @EventHandler
    public void onNewGame(NewGameEvent newGameEvent)
    {
        if(main.getInstance().getConfigAccessor().getConfig().getBoolean("modules.ads.enabled"))
        {
            MessageUtil.sendConsole("ads.msg_1");
            Bukkit.broadcastMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("ads.msg_2")+"jingwenMC");
            Bukkit.broadcastMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("ads.msg_3")+"我是jingwen");
            Bukkit.broadcastMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("ads.msg_4")+"https://github.com/jingwenMC/MCDNDC/");
        }
    }
}

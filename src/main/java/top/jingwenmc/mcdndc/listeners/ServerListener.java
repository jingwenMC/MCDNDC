package top.jingwenmc.mcdndc.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.GamePlayer;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.HashMap;
import java.util.Map;

public class ServerListener implements Listener {
    private Map<String, BukkitTask> offline = new HashMap<>();
    @EventHandler
    public void onJoin(PlayerJoinEvent evt)
    {
        if(!offline.containsKey(evt.getPlayer().getName()))
            Main.INSTANCE.getPlayerManager().createGamePlayer(evt.getPlayer());
        else
        {
            offline.get(evt.getPlayer().getName()).cancel();
            GamePlayer gp = Main.INSTANCE.getPlayerManager().getGamePlayer(evt.getPlayer());
            gp.setPlayer(evt.getPlayer());
            Main.INSTANCE.getPlayerManager().getMap().put(evt.getPlayer().getName(),gp);
            Main.INSTANCE.providerManager.requestWordChange(gp,gp.getTopic());
        }
        evt.setJoinMessage(ChatColor.YELLOW+evt.getPlayer().getName()+" "+ MessageUtil.getMessage("server.join"));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent evt)
    {
        evt.setQuitMessage(ChatColor.YELLOW+evt.getPlayer().getName()+" "+MessageUtil.getMessage("server.quit"));
        String name = evt.getPlayer().getName();
        offline.put(evt.getPlayer().getName(), new BukkitRunnable() {
            @Override
            public void run() {
                GamePlayer g = Main.INSTANCE.getPlayerManager().getMap().get(name);
                String word = g.getTopic();
                Main.INSTANCE.getGameManager().words.add(word);
                Main.INSTANCE.getPlayerManager().removeGamePlayer(name);
                offline.remove(name);
            }
        }.runTaskLater(Main.INSTANCE,Main.config.getConfig().getLong("wait",1200)));
    }
}

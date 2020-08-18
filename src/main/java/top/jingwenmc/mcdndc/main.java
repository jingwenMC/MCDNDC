package top.jingwenmc.mcdndc;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import top.jingwenmc.mcdndc.commands.wordkeeper;
import top.jingwenmc.mcdndc.managers.GameManager;
import top.jingwenmc.mcdndc.managers.PlayerManager;
import top.jingwenmc.mcdndc.modules.AutoSwitchFromKeeper;
import top.jingwenmc.mcdndc.modules.ItemSwitch;
import top.jingwenmc.mcdndc.tabcomplete.mcdndctab;
import top.jingwenmc.mcdndc.tabcomplete.wordkeepertab;
import top.jingwenmc.mcdndc.util.*;
import top.jingwenmc.mcdndc.commands.mcdndc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * Main class of the project
 */
public final class main extends JavaPlugin implements Listener {
    private static main instance = null;
    private ConfigAccessor configAccessor = null;
    private ConfigAccessor langAccessor = null;
    private static PlayerManager playerManager = null;
    private GameManager gameManager = null;
    private Map<String,BukkitTask> offline = new HashMap<>();
    BukkitTask task;
    public static final int cv = 5;
    @Override
    public void onEnable() {
        instance=this;
        configAccessor = new ConfigAccessor(this,"config.yml");
        langAccessor = new ConfigAccessor(this,"lang.yml");
        configAccessor.saveDefaultConfig();
        langAccessor.saveDefaultConfig();
        playerManager = new PlayerManager();
        gameManager = new GameManager();
        // Plugin startup logic
        MessageUtil.sendConsole("console.check_version");
        gameManager.resetList();
        MessageUtil.sendConsole("console.during_load");
        Objects.requireNonNull(getCommand("dndc")).setExecutor(new mcdndc());
        Objects.requireNonNull(getCommand("dndc")).setTabCompleter(new mcdndctab());
        Objects.requireNonNull(getCommand("wordkeeper")).setExecutor(new wordkeeper());
        Objects.requireNonNull(getCommand("wordkeeper")).setTabCompleter(new wordkeepertab());
        task = new BukkitRunnable()
        {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers())
                {
                    ScoreboardUtil.updateOneScoreboard(p);
                }
            }
        }.runTaskTimer(this,configAccessor.getConfig().getInt("interval"),configAccessor.getConfig().getInt("interval"));
        getServer().getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(new GuiUtil(),this);
        getServer().getPluginManager().registerEvents(new UpdateUtil(),this);
        for(Player p : Bukkit.getOnlinePlayers())
        {
            //in case of reload confirm
            getServer().getPluginManager().callEvent(new PlayerJoinEvent(p,""));
        }
        registerModules();
        MessageUtil.sendConsole("console.post_load");
        UpdateUtil.checkUpdateAsync();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MessageUtil.sendConsole("console.unload");
        for(BukkitTask t : offline.values())
        {
            t.cancel();
        }
    }

    public static main getInstance()
    {
        return instance;
    }

    public ConfigAccessor getConfigAccessor() {
        return configAccessor;
    }
    public ConfigAccessor getLangAccessor() {
        return langAccessor;
    }
    public BukkitTask getTask()
    {
        return task;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }

    /**
     * Get The Plugin's PlayerManager
     * @return PlayerManager
     */
    public static PlayerManager getPlayerManager() {
        return playerManager;
    }
    public GameManager getGameManager() {
        return gameManager;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent evt)
    {
        if(!offline.containsKey(evt.getPlayer().getName()))
        playerManager.createGamePlayer(evt.getPlayer());
        else
        {
            offline.get(evt.getPlayer().getName()).cancel();
            GamePlayer gp = playerManager.getGamePlayer(evt.getPlayer());
            gp.setPlayer(evt.getPlayer());
            playerManager.getMap().put(evt.getPlayer().getName(),gp);
            TABAPI.setValueTemporarily(evt.getPlayer().getUniqueId(), EnumProperty.TAGPREFIX,"["+gp.getTopic()+"]");
        }
        evt.setJoinMessage(ChatColor.YELLOW+evt.getPlayer().getName()+" "+MessageUtil.getMessage("server.join"));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent evt)
    {
        evt.setQuitMessage(ChatColor.YELLOW+evt.getPlayer().getName()+" "+MessageUtil.getMessage("server.quit"));
        String name = evt.getPlayer().getName();
        offline.put(evt.getPlayer().getName(), new BukkitRunnable() {
            @Override
            public void run() {
                GamePlayer g = playerManager.getMap().get(name);
                String word = g.getTopic();
                gameManager.words.add(word);
                playerManager.removeGamePlayer(name);
                offline.remove(name);
            }
        }.runTaskLater(this,getConfigAccessor().getConfig().getLong("wait",1200)));
    }
    private void registerModules()
    {
        getServer().getPluginManager().registerEvents(new AutoSwitchFromKeeper(),this);
        getServer().getPluginManager().registerEvents(new ItemSwitch(),this);
    }
}

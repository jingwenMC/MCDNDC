package top.jingwenmc.mcdndc;

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
import top.jingwenmc.mcdndc.modules.Ads;
import top.jingwenmc.mcdndc.modules.AutoSwitchFromKeeper;
import top.jingwenmc.mcdndc.tabcomplete.mcdndctab;
import top.jingwenmc.mcdndc.tabcomplete.wordkeepertab;
import top.jingwenmc.mcdndc.util.ConfigAccessor;
import top.jingwenmc.mcdndc.util.MessageUtil;
import top.jingwenmc.mcdndc.commands.mcdndc;
import top.jingwenmc.mcdndc.util.ScoreboardUtil;

import javax.naming.ConfigurationException;
import java.util.List;
import java.util.Objects;

public final class main extends JavaPlugin implements Listener {
    private static main instance = null;
    private ConfigAccessor configAccessor = null;
    private ConfigAccessor langAccessor = null;
    private PlayerManager playerManager = null;
    private GameManager gameManager = null;
    public static int cv = 5;

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
        BukkitTask task = new BukkitRunnable()
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
        for(Player p : Bukkit.getOnlinePlayers())
        {
            //in case of reload confirm
            getServer().getPluginManager().callEvent(new PlayerJoinEvent(p,""));
        }
        registerModules();
        MessageUtil.sendConsole("console.post_load");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        MessageUtil.sendConsole("console.unload");
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

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent evt)
    {
        playerManager.createGamePlayer(evt.getPlayer());
        evt.setJoinMessage(ChatColor.YELLOW+evt.getPlayer().getName()+" "+MessageUtil.getMessage("server.join"));
    }
    @EventHandler
    public void onQuit(PlayerQuitEvent evt)
    {
        playerManager.removeGamePlayer(evt.getPlayer().getName());
        evt.setQuitMessage(ChatColor.YELLOW+evt.getPlayer().getName()+" "+MessageUtil.getMessage("server.quit"));
    }
    public void registerModules()
    {
        getServer().getPluginManager().registerEvents(new Ads(),this);
        getServer().getPluginManager().registerEvents(new AutoSwitchFromKeeper(),this);
    }
}

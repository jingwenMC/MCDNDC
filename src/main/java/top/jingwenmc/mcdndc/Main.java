package top.jingwenmc.mcdndc;

import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.mcdndc.commands.dndc.*;
import top.jingwenmc.mcdndc.listeners.GameListener;
import top.jingwenmc.mcdndc.listeners.ServerListener;
import top.jingwenmc.mcdndc.managers.GameManager;
import top.jingwenmc.mcdndc.managers.PlayerManager;
import top.jingwenmc.mcdndc.managers.ProviderManager;
import top.jingwenmc.mcdndc.managers.SubCommandManager;
import top.jingwenmc.mcdndc.provider.TABProvider;
import top.jingwenmc.mcdndc.util.ConfigAccessor;
import top.jingwenmc.mcdndc.util.ConfigUtil;
import top.jingwenmc.mcdndc.util.MessageUtil;
import top.jingwenmc.mcdndc.util.UpdateUtil;

import java.io.File;

/**
 * Main class of the project
 */
public final class Main extends JavaPlugin{
    public final static String cv = "1A";
    public static Main INSTANCE;

    public static ConfigAccessor config;
    public static ConfigAccessor lang;

    private SubCommandManager dndcCM;
    private GameManager gameManager;
    private PlayerManager playerManager;
    public ProviderManager providerManager;
    @Override
    public void onEnable() {
        INSTANCE = this;
        config = new ConfigAccessor(this,"config.yml");
        lang = new ConfigAccessor(this,"lang.yml");
        gameManager = new GameManager();
        playerManager = new PlayerManager();
        providerManager = new ProviderManager();
        config.saveDefaultConfig();
        lang.saveDefaultConfig();

        MessageUtil.sendConsole("console.check_version");
        ConfigUtil.checkConfigVersion();

        MessageUtil.sendConsole("console.during_load");
        dndcCM = new SubCommandManager();
        getCommand("dndc").setExecutor(dndcCM);
        dndcCM.register(new help(),null);
        dndcCM.register(new help(),"help");
        dndcCM.register(new next(),"next");
        dndcCM.register(new reload(),"reload");
        dndcCM.register(new restart(),"restart");
        dndcCM.register(new set(),"set");
        registerDefaultProviders();
        startMCDNDCVersionCheck();
        initWords();
        registerListeners();

        MessageUtil.sendConsole("console.post_load");
        MessageUtil.sendConsole("server.metrics");
        Metrics metrics =  new Metrics(this , 8607);
    }
    @Override
    public void onDisable() {
        MessageUtil.sendConsole("console.unload");
    }
    public static Main getInstance() {
        return INSTANCE;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
    public PlayerManager getPlayerManager()
    {
        return this.playerManager;
    }
    private void registerDefaultProviders()
    {
        providerManager.registerProvider(new TABProvider(),"TAB");
        providerManager.loadProvider();
    }
    private void initWords()
    {
        gameManager.resetList();
    }
    private void registerListeners()
    {
        Bukkit.getPluginManager().registerEvents(new ServerListener(),this);
    }
    private void startMCDNDCVersionCheck()
    {
        Bukkit.getPluginManager().registerEvents(new GameListener(),this);
        UpdateUtil.checkUpdateAsync();
    }
}

package top.jingwenmc.mcdndc;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.mcdndc.commands.dndc.help;
import top.jingwenmc.mcdndc.commands.dndc.next;
import top.jingwenmc.mcdndc.managers.GameManager;
import top.jingwenmc.mcdndc.managers.ProviderManager;
import top.jingwenmc.mcdndc.managers.SubCommandManager;
import top.jingwenmc.mcdndc.provider.TABProvider;
import top.jingwenmc.mcdndc.util.ConfigAccessor;
import top.jingwenmc.mcdndc.util.MessageUtil;

import javax.naming.ConfigurationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * Main class of the project
 */
public final class main extends JavaPlugin{
    public final static String cv = "1A";
    private static main instance;

    public static ConfigAccessor config;
    public static ConfigAccessor lang;

    private SubCommandManager dndcCM;
    private GameManager gameManager;
    public ProviderManager providerManager;
    @Override
    public void onEnable() {
        instance = this;
        config = new ConfigAccessor(this,"config.yml");
        lang = new ConfigAccessor(this,"lang.yml");
        gameManager = new GameManager();
        providerManager = new ProviderManager();
        config.saveDefaultConfig();
        lang.saveDefaultConfig();

        MessageUtil.sendConsole("console.check_version");
        //TODO:Version Check

        MessageUtil.sendConsole("console.during_load");
        dndcCM = new SubCommandManager();
        getCommand("dndc").setExecutor(dndcCM);
        dndcCM.register(new help(),null);
        dndcCM.register(new help(),"help");
        dndcCM.register(new next(),"next");

        MessageUtil.sendConsole("console.post_load");
        registerDefaultProviders();
        MessageUtil.sendConsole("server.metrics");
        Metrics metrics =  new Metrics(this , 8607);
    }
    @Override
    public void onDisable() {
        MessageUtil.sendConsole("console.unload");
    }
    public static main getInstance() {
        return instance;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
    private void registerDefaultProviders()
    {
        providerManager.registerProvider(new TABProvider(),"TAB");
        providerManager.loadProvider();
    }
}

package top.jingwenmc.mcdndc;

import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.mcdndc.commands.dndc.help;
import top.jingwenmc.mcdndc.commands.dndc.next;
import top.jingwenmc.mcdndc.managers.GameManager;
import top.jingwenmc.mcdndc.managers.SubCommandManager;
import top.jingwenmc.mcdndc.util.ConfigAccessor;
import top.jingwenmc.mcdndc.util.ExceptionUtil;

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
    @Override
    public void onEnable() {
        instance = this;
        config = new ConfigAccessor(this,"config.yml");
        lang = new ConfigAccessor(this,"lang.yml");
        gameManager = new GameManager();
        config.saveDefaultConfig();
        lang.saveDefaultConfig();

        dndcCM = new SubCommandManager();
        getCommand("dndc").setExecutor(dndcCM);
        dndcCM.register(new help(),null);
        dndcCM.register(new help(),"help");
        dndcCM.register(new next(),"next");
    }
    @Override
    public void onDisable() {
    }
    public static main getInstance() {
        return instance;
    }

    public GameManager getGameManager() {
        return gameManager;
    }
}

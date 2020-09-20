package top.jingwenmc.mcdndc;

import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.mcdndc.util.ConfigAccessor;

import javax.naming.ConfigurationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/**
 * Main class of the project
 */
public final class main extends JavaPlugin{
    private static main instance;
    public static ConfigAccessor config;
    public static ConfigAccessor lang;
    @Override
    public void onEnable() {
        instance = this;
        config = new ConfigAccessor(this,"config.yml");
        lang = new ConfigAccessor(this,"lang.yml");
    }
    @Override
    public void onDisable() {
    }
    public static main getInstance() {
        return instance;
    }
}

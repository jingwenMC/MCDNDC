package top.jingwenmc.mcdndc.util;

import org.bukkit.configuration.ConfigurationSection;
import top.jingwenmc.mcdndc.main;

import java.util.List;

public class ConfigUtil {
    public static Object get(String path)
    {
        if(!main.config.getConfig().isSet(path))confError(path);
        return main.config.getConfig().get(path);
    }
    public static String getString(String path)
    {
        if(!main.config.getConfig().isSet(path))confError(path);
        return main.config.getConfig().getString(path);
    }
    public static List<String> getStringList(String path)
    {
        if(!main.config.getConfig().isSet(path))confError(path);
        return main.config.getConfig().getStringList(path);
    }
    public static long getLong(String path)
    {
        if(!main.config.getConfig().isSet(path))confError(path);
        return main.config.getConfig().getLong(path);
    }
    public static int getInt(String path)
    {
        if(!main.config.getConfig().isSet(path))confError(path);
        return main.config.getConfig().getInt(path);
    }
    public static boolean getBoolean(String path)
    {
        if(!main.config.getConfig().isSet(path))confError(path);
        return main.config.getConfig().getBoolean(path);
    }
    public static ConfigurationSection getConfigurationSection(String path)
    {
        if(!main.config.getConfig().isSet(path))confError(path);
        return main.config.getConfig().getConfigurationSection(path);
    }
    private static void confError(String path)
    {
        System.out.println("MCDNDC发生配置文件异常: 未找到的键:"+path);
        System.out.println("MCDNDC Config File Error: Key Not Found:"+path);
        System.out.println("已经将当前的配置文件保存至\"config_backup_"+System.currentTimeMillis()+".yml\"");
        System.out.println("The current config file has been saved to\"config_backup_"+System.currentTimeMillis()+".yml\"");
        main.config.forceRename("config_backup_"+System.currentTimeMillis()+".yml");
        main.config = new ConfigAccessor(main.getInstance(),"config.yml");
        main.config.saveDefaultConfig();
    }
}

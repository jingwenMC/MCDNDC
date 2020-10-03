package top.jingwenmc.mcdndc.util;

import org.bukkit.configuration.ConfigurationSection;
import top.jingwenmc.mcdndc.Main;

import java.util.List;

public class ConfigUtil {
    public static Object get(String path)
    {
        if(!Main.config.getConfig().isSet(path))confError(path);
        return Main.config.getConfig().get(path);
    }
    public static String getString(String path)
    {
        if(!Main.config.getConfig().isSet(path))confError(path);
        return Main.config.getConfig().getString(path);
    }
    public static List<String> getStringList(String path)
    {
        if(!Main.config.getConfig().isSet(path))confError(path);
        return Main.config.getConfig().getStringList(path);
    }
    public static long getLong(String path)
    {
        if(!Main.config.getConfig().isSet(path))confError(path);
        return Main.config.getConfig().getLong(path);
    }
    public static int getInt(String path)
    {
        if(!Main.config.getConfig().isSet(path))confError(path);
        return Main.config.getConfig().getInt(path);
    }
    public static boolean getBoolean(String path)
    {
        if(!Main.config.getConfig().isSet(path))confError(path);
        return Main.config.getConfig().getBoolean(path);
    }
    public static ConfigurationSection getConfigurationSection(String path)
    {
        if(!Main.config.getConfig().isSet(path))confError(path);
        return Main.config.getConfig().getConfigurationSection(path);
    }
    public static void checkConfigVersion()
    {
        String ver = Main.config.getConfig().getString("config_version");
        assert ver != null;
        if(!ver.equals(Main.cv))
        {
            System.out.println("MCDNDC发生配置文件异常: 不正确的配置文件版本:"+ver+"(Expected:"+ Main.cv+")");
            System.out.println("MCDNDC Config File Error: Incorrect config version:"+ver+"(Expected:"+ Main.cv+")");
            saveNewConfig();
        }
    }

    private static void saveNewConfig() {
        System.out.println("已经将当前的配置文件保存至\"config_backup_"+System.currentTimeMillis()+".yml\"");
        System.out.println("The current config file has been saved to\"config_backup_"+System.currentTimeMillis()+".yml\"");
        System.out.println("并释放了一个新的配置文件");
        System.out.println("And released a new config file");
        Main.config.forceRename("config_backup_"+System.currentTimeMillis()+".yml");
        Main.config = new ConfigAccessor(Main.getInstance(),"config.yml");
        Main.config.saveDefaultConfig();
    }

    private static void confError(String path)
    {
        System.out.println("MCDNDC发生配置文件异常: 未找到的键:"+path);
        System.out.println("MCDNDC Config File Error: Key Not Found:"+path);
        saveNewConfig();
    }
}

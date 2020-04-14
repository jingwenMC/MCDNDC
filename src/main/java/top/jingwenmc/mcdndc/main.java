package top.jingwenmc.mcdndc;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import top.jingwenmc.mcdndc.commands.dndc;

import java.util.List;

public final class main extends JavaPlugin {
    public static List<String> words;
    Plugin plugin = top.jingwenmc.mcdndc.main.getPlugin(top.jingwenmc.mcdndc.main.class);
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[MCDNDC]加载插件...");
        System.out.println("[MCDNDC]正在加载指令:dndc...");
        getCommand("dndc").setExecutor(new dndc());
        System.out.println("[MCDNDC]dndc指令加载成功!");
        System.out.println("[MCDNDC]正在加载配置文件:config.yml...");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        words = plugin.getConfig().getStringList("words");
        if(words.size()==0) System.out.println("[MCDNDC]读取config.yml时遇到致命错误,请检查你的配置文件.");
        else System.out.println("[MCDNDC]config.yml配置文件加载成功!");
        System.out.println("[MCDNDC]插件加载成功!");
        System.out.println("[MCDNDC]=====MCDNDC v0.1=====");
        System.out.println("[MCDNDC]作者:jingwenMC");
        System.out.println("[MCDNDC]版本:v0.1(ALPHA)");
        System.out.println("[MCDNDC]许可:开源,GPLv3");
        System.out.println("[MCDNDC]启动成功,祝君愉快!");
        System.out.println("[MCDNDC]=====MCDNDC v0.1=====");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[MCDNDC]插件关闭...");
        System.out.println("[MCDNDC]=====MCDNDC v0.1=====");
        System.out.println("[MCDNDC]作者:jingwenMC");
        System.out.println("[MCDNDC]版本:v0.1(ALPHA)");
        System.out.println("[MCDNDC]许可:开源,GPLv3");
        System.out.println("[MCDNDC]关闭成功,下次再见!");
        System.out.println("[MCDNDC]=====MCDNDC v0.1=====");
    }
}

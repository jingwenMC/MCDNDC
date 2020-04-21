package top.jingwenmc.mcdndc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import top.jingwenmc.mcdndc.commands.dndc;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public final class main extends JavaPlugin {
    public static List<String> words;
    public static String notify_player_after_next_word = null;
    //Plugin plugin = top.jingwenmc.mcdndc.main.getPlugin(top.jingwenmc.mcdndc.main.class);
    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println(ChatColor.GREEN+"[MCDNDC]加载插件...");
        Plugin plugin = top.jingwenmc.mcdndc.main.getPlugin(top.jingwenmc.mcdndc.main.class);
        System.out.println(ChatColor.GREEN+"[MCDNDC]正在注册计分板:MCDNDCScore...");
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        scoreboard.registerNewObjective("MCDNDCScore","dummy",ChatColor.GOLD+"Score");
        System.out.println(ChatColor.GREEN+"[MCDNDC]正在强制清空计分板:MCDNDCScore...");
        for (String setsub : scoreboard.getEntries())
        {
            scoreboard.resetScores(setsub);
        }
        System.out.println(ChatColor.GREEN+"[MCDNDC]计分板MCDNDCScore注册完成!");
        System.out.println(ChatColor.GREEN+"[MCDNDC]正在加载指令:dndc...");
        Objects.requireNonNull(getCommand("dndc")).setExecutor(new dndc());
        System.out.println(ChatColor.GREEN+"[MCDNDC]dndc指令加载完成!");
        System.out.println(ChatColor.GREEN+"[MCDNDC]正在加载配置文件:config.yml...");
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        int cv=plugin.getConfig().getInt("config_version");
        notify_player_after_next_word = plugin.getConfig().getString("notify_player_after_next_word");
        boolean isntRightConfig = !(cv==2);
        words = plugin.getConfig().getStringList("words");
        if(words.size()==0||isntRightConfig) {
            System.out.println(ChatColor.RED+"[MCDNDC]读取config.yml时遇到错误,请检查你的配置文件后进行重载.错误信息:");
            if(words.size()==0) System.out.println(ChatColor.RED+"配置文件错误:Config-Words-Matches-0.");
            if(isntRightConfig) System.out.println(ChatColor.RED+"配置文件版本错误:Config-Version-Expected-2-Got-"+cv+".");
        }
        else System.out.println(ChatColor.GREEN+"[MCDNDC]config.yml配置文件加载完成!");
        System.out.println(ChatColor.GREEN+"[MCDNDC]插件加载完成!");
        System.out.println(ChatColor.AQUA+"[MCDNDC]=======MCDNDC=======");
        System.out.println(ChatColor.AQUA+"[MCDNDC]作者:jingwenMC");
        System.out.println(ChatColor.AQUA+"[MCDNDC]版本:v0.1.2(ALPHA)");
        System.out.println(ChatColor.AQUA+"[MCDNDC]许可:开源,GPLv3");
        System.out.println(ChatColor.AQUA+"[MCDNDC]启动完成,祝君愉快!");
        System.out.println(ChatColor.AQUA+"[MCDNDC]=======MCDNDC=======");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println(ChatColor.AQUA+"[MCDNDC]插件关闭...");
        System.out.println(ChatColor.AQUA+"[MCDNDC]=======MCDNDC=======");
        System.out.println(ChatColor.AQUA+"[MCDNDC]作者:jingwenMC");
        System.out.println(ChatColor.AQUA+"[MCDNDC]版本:v0.1.2(ALPHA)");
        System.out.println(ChatColor.AQUA+"[MCDNDC]许可:开源,GPLv3");
        System.out.println(ChatColor.AQUA+"[MCDNDC]关闭完成,下次再见!");
        System.out.println(ChatColor.AQUA+"[MCDNDC]=======MCDNDC=======");
    }
}

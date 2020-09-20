package top.jingwenmc.mcdndc.legacy.util;

import org.bukkit.command.CommandSender;
import top.jingwenmc.mcdndc.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Objects;

public class MessageUtil {
    public static void sendConsole(String path)
    {
        Bukkit.getLogger().info(getPrefix()+getMessage(path));
    }
    public static void sendServer(String path)
    {
        Bukkit.broadcastMessage(getPrefix()+getMessage(path));
    }
    public static void sendPlayer(CommandSender player, String path)
    {
        player.sendMessage(getPrefix()+getMessage(path));
    }
    public static String getPrefix()
    {
        return ChatColor.translateAlternateColorCodes('&',main.getInstance().getLangAccessor().getConfig().getString(getLanguage()+".prefix"));
    }

    public static String getLanguage()
    {
        return main.getInstance().getConfigAccessor().getConfig().getString("lang");
    }
    public static String getMessage(String path)
    {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(main.getInstance().getLangAccessor().getConfig().getString(getLanguage() + "." + path))).replaceAll("\n", "\r\n");
    }
}

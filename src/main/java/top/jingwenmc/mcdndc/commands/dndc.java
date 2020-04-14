package top.jingwenmc.mcdndc.commands;

import me.neznamy.tab.api.TABAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class dndc implements CommandExecutor {
    Plugin plugin = top.jingwenmc.mcdndc.main.getPlugin(top.jingwenmc.mcdndc.main.class);
    private void errmsg(CommandSender sender,String str)
    {
        //In Stance Of
        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            player.sendMessage(ChatColor.RED+"[MCDNDC]执行的指令发生异常,错误信息:");
            System.out.println(ChatColor.RED+str);
        }
        else
        {
            System.out.println("[MCDNDC]执行的指令发生异常,错误信息:");
            System.out.println(str);
        }
    }
    private boolean checkPerm (CommandSender sender,String perm)
    {
        //In Stance Of
        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            if(player.hasPermission(perm))return true;
            else return false;
        }
        else
        {
            return true;
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(args.length<1||args.length>1)
        {
            errmsg(sender,"太少的条件!请输入指令\"/dndc help\"获得帮助!");
            return false;
        }
        if(args[0]=="reload")
        {
            if(checkPerm(sender,"dndc.reload"))
            {
                if(sender instanceof Player)player.sendMessage(ChatColor.GOLD+"[MCDNDC]正在重载配置文件...");
                System.out.println("[MCDNDC]正在重载配置文件...");
                top.jingwenmc.mcdndc.main.words = plugin.getConfig().getStringList("words");
                if(top.jingwenmc.mcdndc.main.words.size()==0) errmsg(sender,"读取config.yml时遇到致命错误,请检查你的配置文件.");
                else {if(sender instanceof Player)player.sendMessage(ChatColor.GOLD+"[MCDNDC]配置文件已重载.");
                System.out.println("[MCDNDC]配置文件已重载.");}
            }
            else errmsg(sender,"权限不足.");
        }
        if(args[0]=="next")
        {
            if(checkPerm(sender,"dndc.play"))
            {

            }
            else
            {
                errmsg(sender,"权限不足.");
            }
        }
        return false;
    }
}
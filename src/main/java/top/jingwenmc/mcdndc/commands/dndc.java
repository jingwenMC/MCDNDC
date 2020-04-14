package top.jingwenmc.mcdndc.commands;

import me.neznamy.tab.api.TABAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class dndc implements CommandExecutor {
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
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(args.length<1)
        {
            errmsg(sender,"太少的条件!请输入指令\"/dndc help\"获得帮助!");
            return false;
        }
        return false;
    }
}
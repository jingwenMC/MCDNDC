package top.jingwenmc.mcdndc.commands.dndc;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class help extends JCommand {
    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        for(String s : main.lang.getConfig().getStringList(MessageUtil.getLanguage()+".help"))
        {
            s = ChatColor.translateAlternateColorCodes('&',s);
            sender.sendMessage(s);
        }
        return true;
    }
}

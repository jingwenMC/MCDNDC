package top.jingwenmc.mcdndc.commands.dndc.extensions;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.List;

public class help extends JCommand {
    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        if(args.length!=0)return false;
        MessageUtil.sendPlayer(sender,"extension.line");
        MessageUtil.sendPlayer(sender,"extension.about_1");
        MessageUtil.sendPlayer(sender,"extension.about_2");
        for(String s : (List<String>) MessageUtil.get("extension.help"))
        {
            s = ChatColor.translateAlternateColorCodes('&',s);
            sender.sendMessage(s);
        }
        MessageUtil.sendPlayer(sender,"extension.line");
        return true;
    }
}

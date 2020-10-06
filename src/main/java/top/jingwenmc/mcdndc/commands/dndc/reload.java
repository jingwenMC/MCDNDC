package top.jingwenmc.mcdndc.commands.dndc;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.util.ConfigUtil;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class reload extends JCommand {
    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        if(args.length!=0)return false;
        //Perm
        if(!(sender.hasPermission("dndc.reload") && sender.hasPermission("dndc.restart")))
        {
            MessageUtil.sendPlayer(sender,"server.no_perm");
            return true;
        }

        MessageUtil.sendPlayer(sender,"game.reload");
        ConfigUtil.checkConfigVersion();
        Main.config.reloadConfig();
        Main.lang.reloadConfig();
        Main.getInstance().providerManager.loadProvider(false);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"dndc restart");
        return true;
    }
}
//TODO:WordKeeper
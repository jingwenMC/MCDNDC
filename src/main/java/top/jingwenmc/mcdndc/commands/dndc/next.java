package top.jingwenmc.mcdndc.commands.dndc;

import com.sun.deploy.security.SelectableSecurityManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.GamePlayer;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class next extends JCommand {
    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        if(sender instanceof Player)
        {
            //Noting to do
        }
        else
        {
            MessageUtil.sendPlayer(sender,"server.not_player");
            return false;
        }
        GamePlayer gp = Main.INSTANCE.getPlayerManager().getGamePlayer(sender.getName());
        return true;
    }
}

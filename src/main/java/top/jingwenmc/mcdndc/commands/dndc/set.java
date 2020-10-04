package top.jingwenmc.mcdndc.commands.dndc;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.GamePlayer;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class set extends JCommand {
    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        if(!sender.hasPermission("dndc.set"))
        {
            MessageUtil.sendPlayer(sender,"server.no_perm");
            return true;
        }
        if(args.length!=2)return false;
        Player player;
        player = Bukkit.getPlayerExact(args[0]);
        if(player==null)
        {
            MessageUtil.sendPlayer(sender,"server.not_player");
            return false;
        }
        GamePlayer gamePlayer = Main.INSTANCE.getPlayerManager().getGamePlayer(player);
        try {
            gamePlayer.setScore(Integer.parseInt(args[1]));
        }catch (NumberFormatException e)
        {
            MessageUtil.sendPlayer(sender,"server.not_int");
            return false;
        }
        MessageUtil.sendPlayer(sender,"game.score_set");
        return true;
    }
}

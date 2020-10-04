package top.jingwenmc.mcdndc.commands.dndc;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.enums.CallResult;
import top.jingwenmc.mcdndc.objects.GamePlayer;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.util.ExceptionUtil;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class next extends JCommand {
    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        if(args.length!=0)return false;
        if(!sender.hasPermission("dndc.play"))
        {
            MessageUtil.sendPlayer(sender,"server.no_perm");
            return true;
        }
        Player player;
        if(sender instanceof Player)
        {
            player = (Player) sender;
        }
        else
        {
            MessageUtil.sendPlayer(sender,"server.not_player");
            return true;
        }
        GamePlayer gp = Main.INSTANCE.getPlayerManager().getGamePlayer(player.getName());
        boolean isNew = gp.getTopic() == null;
        CallResult callResult = gp.setNewTopic();
        if(callResult.equals(CallResult.CANCELED))return true;
        if(callResult.equals(CallResult.SUCCESS))
        {
            if(!isNew)
            Bukkit.broadcastMessage(MessageUtil.convert(MessageUtil.getMessage("game.new_word").replaceAll("%player", player.getName())
            .replaceAll("%word",gp.getTopic())));
            else
                Bukkit.broadcastMessage(MessageUtil.convert(MessageUtil.getMessage("game.first_word").replaceAll("%player", player.getName())));
            return true;
        }
        else if(callResult.equals(CallResult.NO_WORD))
        {
            MessageUtil.sendServer("game.no_word");
            return true;
        }
        ExceptionUtil.print(new IllegalStateException("New Word Setting Returns Null"));
        return true;
    }
}

package top.jingwenmc.mcdndc.commands.dndc;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.events.NewGameEvent;
import top.jingwenmc.mcdndc.objects.GamePlayer;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class restart extends JCommand {
    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        if(args.length!=0)return false;
        if(sender.hasPermission("dndc.restart"))
        {
            MessageUtil.sendServer("game.restart");
            Main.INSTANCE.getGameManager().resetList();
            for(GamePlayer gamePlayer : Main.INSTANCE.getPlayerManager().getMap().values())
            {
                gamePlayer.setScore(0);
                gamePlayer.setTopic(null);
                Main.INSTANCE.providerManager.requestWordChange(gamePlayer,null);
                Main.INSTANCE.getGameManager().words.clear();
            }
            for(Player p : Bukkit.getOnlinePlayers())
            {
                Bukkit.dispatchCommand(p,"dndc next");
            }
            Main.INSTANCE.getServer().getPluginManager().callEvent(new NewGameEvent());
        }
        else
        {
            MessageUtil.sendPlayer(sender,"server.no_perm");
        }
        return true;
    }
}

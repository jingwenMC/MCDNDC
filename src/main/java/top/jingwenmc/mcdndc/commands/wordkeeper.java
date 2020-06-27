package top.jingwenmc.mcdndc.commands;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.util.GamePlayer;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.HashMap;
import java.util.Map;

public class wordkeeper implements CommandExecutor {
    Map<String,String> map = new HashMap<>();
    public boolean sendCommandError(CommandSender sender)
    {
        MessageUtil.sendPlayer(sender,"server.no_cmd");
        return false;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==0)return sendCommandError(sender);
        if(args[0].equalsIgnoreCase("set"))
        {
            if(args.length==3)
            {
                if(sender.hasPermission("dndc.keep.add"))
                {
                    map.put(args[1],args[2]);
                    MessageUtil.sendPlayer(sender,"keeper.success");
                }
                else MessageUtil.sendPlayer(sender,"server.no_perm");
            }
            else return sendCommandError(sender);
            return true;
        }
        if(args[0].equalsIgnoreCase("get"))
        {
            if(args.length==2)
            {
                if(sender.hasPermission("dndc.keep.use"))
                {
                    if(map.containsKey(args[1]))
                    {
                        if(sender instanceof Player) {
                            Player player = (Player) sender;
                            GamePlayer gamePlayer = main.getInstance().getPlayerManager().getGamePlayer(player);
                            MessageUtil.sendPlayer(player,"keeper.get");
                            if(gamePlayer.getTopic()==null) Bukkit.broadcastMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("game.first_word").replaceAll("%player",player.getName()).replaceAll("%word",gamePlayer.getTopic()));
                            else Bukkit.broadcastMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("game.new_word").replaceAll("%player",player.getName()).replaceAll("%word",gamePlayer.getTopic()));
                            gamePlayer.setTopic(map.get(args[1]));
                            TABAPI.setValueTemporarily(player.getUniqueId(), EnumProperty.TAGPREFIX, "[" + gamePlayer.getTopic() + "]");
                            if (gamePlayer.getTopic() != null) gamePlayer.setScore(gamePlayer.getScore() + 1);
                        }
                        else
                        {
                            MessageUtil.sendPlayer(sender,"server.not_player");
                        }
                    }
                    else
                    {
                        MessageUtil.sendPlayer(sender,"keeper.not_found");
                    }
                }
                else MessageUtil.sendPlayer(sender,"server.no_perm");
            }
            else return sendCommandError(sender);
            return true;
        }
        return sendCommandError(sender);
    }
}

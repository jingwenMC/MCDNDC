package top.jingwenmc.mcdndc.commands;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.managers.GameManager;
import top.jingwenmc.mcdndc.util.GamePlayer;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class mcdndc implements CommandExecutor {
    public boolean sendCommandError(CommandSender sender)
    {
        MessageUtil.sendPlayer((Player) sender,"server.no_cmd");
        return false;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==0)return sendCommandError(sender);
        if(args[0].equalsIgnoreCase("reload"))
        {
            if(args.length!=1)return sendCommandError(sender);
            if(sender.hasPermission("dndc.reload"))
            {
                MessageUtil.sendPlayer((Player) sender,"game.reload");
                main.getInstance().getConfigAccessor().reloadConfig();
                main.getInstance().getLangAccessor().reloadConfig();
                MessageUtil.sendServer("game.restart");
                main.getInstance().getGameManager().resetList();
                for(GamePlayer gamePlayer : main.getInstance().getPlayerManager().map.values())
                {
                    gamePlayer.setScore(0);
                    gamePlayer.setTopic(null);
                    TABAPI.setValueTemporarily(gamePlayer.getPlayer().getUniqueId(), EnumProperty.TAGPREFIX,null);
                }
            }
            else
            {
                MessageUtil.sendPlayer((Player) sender,"server.no_perm");
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("restart"))
        {
            if(args.length!=1)return sendCommandError(sender);
            if(sender.hasPermission("dndc.restart"))
            {
                MessageUtil.sendServer("game.restart");
                main.getInstance().getGameManager().resetList();
                for(GamePlayer gamePlayer : main.getInstance().getPlayerManager().map.values())
                {
                    gamePlayer.setScore(0);
                    gamePlayer.setTopic(null);
                    TABAPI.setValueTemporarily(gamePlayer.getPlayer().getUniqueId(), EnumProperty.TAGPREFIX,null);
                }
            }
            else
            {
                MessageUtil.sendPlayer((Player) sender,"server.no_perm");
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("next"))
        {
            if(!sender.hasPermission("dndc.play"))
            {
                MessageUtil.sendPlayer((Player) sender,"server.no_perm");
                return true;
            }
            Player player;
            if(args.length==1)
            {
                if(sender instanceof Player)
                    player = (Player) sender;
                else
                {
                    MessageUtil.sendPlayer((Player) sender,"server.not_player");
                    return false;
                }
            }
            else if(args.length==2)
            {
                player = Bukkit.getPlayerExact(args[1]);
                if(player==null)
                {
                    MessageUtil.sendPlayer((Player) sender,"server.not_player");
                    return false;
                }
            }
            else return sendCommandError(sender);
            GamePlayer gamePlayer = main.getInstance().getPlayerManager().getGamePlayer(player);
            if(gamePlayer.getTopic()==null)Bukkit.broadcastMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("game.first_word").replaceAll("%player",player.getName()).replaceAll("%word",gamePlayer.getTopic()));
            else Bukkit.broadcastMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("game.new_word").replaceAll("%player",player.getName()).replaceAll("%word",gamePlayer.getTopic()));
            if(!gamePlayer.setNewTopic())
            {
                MessageUtil.sendPlayer((Player)sender,"game.no_word");
                return false;
            }
            TABAPI.setValueTemporarily(player.getUniqueId(), EnumProperty.TAGPREFIX,"["+gamePlayer.getTopic()+"]");
            if(gamePlayer.getTopic()!=null)gamePlayer.setScore(gamePlayer.getScore()+1);
            return true;
        }
        if(args[0].equalsIgnoreCase("set"))
        {
            if(!sender.hasPermission("dndc.set"))
            {
                MessageUtil.sendPlayer((Player) sender,"server.no_perm");
                return true;
            }
            if(args.length!=3)return sendCommandError(sender);
            Player player;
            player = Bukkit.getPlayerExact(args[1]);
            if(player==null)
            {
                MessageUtil.sendPlayer((Player) sender,"server.not_player");
                return false;
            }
            GamePlayer gamePlayer = main.getInstance().getPlayerManager().getGamePlayer(player);
            gamePlayer.setScore(Integer.parseInt(args[2]));
            MessageUtil.sendPlayer((Player) sender,"game.score_set");
            return true;
        }
        if(args[0].equalsIgnoreCase("help"))
        {
             for(String s : main.getInstance().getLangAccessor().getConfig().getStringList(MessageUtil.getLanguage()+".help"))
             {
                 s = ChatColor.translateAlternateColorCodes('&',s);
                 sender.sendMessage(s);
             }
             return true;
        }
        return sendCommandError(sender);
    }
}

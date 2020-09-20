package top.jingwenmc.mcdndc.legacy.commands;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import top.jingwenmc.mcdndc.legacy.events.NewGameEvent;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.legacy.util.*;

public class mcdndc implements CommandExecutor {
    public static void reloadPluginConf()
    {
        wordkeeper.map.clear();
        main.getInstance().getConfigAccessor().saveDefaultConfig();
        main.getInstance().getLangAccessor().saveDefaultConfig();
        main.getInstance().getConfigAccessor().reloadConfig();
        main.getInstance().getLangAccessor().reloadConfig();
        main.getInstance().getTask().cancel();
        main.getInstance().setTask(new BukkitRunnable()
        {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers())
                {
                    ScoreboardUtil.updateOneScoreboard(p);
                }
            }
        }.runTaskTimer(main.getInstance(),main.getInstance().getConfigAccessor().getConfig().getInt("interval"),main.getInstance().getConfigAccessor().getConfig().getInt("interval")));
        GuiUtil.map.clear();
    }
    public boolean sendCommandError(CommandSender sender)
    {
        MessageUtil.sendPlayer(sender,"server.no_cmd");
        return false;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length==0)return sendCommandError(sender);
        if(args[0].equalsIgnoreCase("reload"))
        {
            if(args.length!=1)return sendCommandError(sender);
            if(sender.hasPermission("dndc.reload")&&sender.hasPermission("dndc.restart"))
            {
                MessageUtil.sendPlayer(sender,"game.reload");
                reloadPluginConf();
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"dndc restart");
            }
            else
            {
                MessageUtil.sendPlayer(sender,"server.no_perm");
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
                for(GamePlayer gamePlayer : main.getInstance().getPlayerManager().getMap().values())
                {
                    gamePlayer.setScore(0);
                    gamePlayer.setTopic(null);
                    TABAPI.setValueTemporarily(gamePlayer.getPlayer().getUniqueId(), EnumProperty.TAGPREFIX,null);
                }
                for(Player p : Bukkit.getOnlinePlayers())
                {
                    Bukkit.dispatchCommand(p,"dndc next");
                }
                main.getInstance().getServer().getPluginManager().callEvent(new NewGameEvent());
            }
            else
            {
                MessageUtil.sendPlayer(sender,"server.no_perm");
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("next"))
        {
            if(!sender.hasPermission("dndc.play"))
            {
                MessageUtil.sendPlayer(sender,"server.no_perm");
                return true;
            }
            Player player;
            if(args.length==1)
            {
                if(sender instanceof Player)
                    player = (Player) sender;
                else
                {
                    MessageUtil.sendPlayer(sender,"server.not_player");
                    return true;
                }
            }
            else return sendCommandError(sender);
            GamePlayer gamePlayer = main.getInstance().getPlayerManager().getGamePlayer(player);
            boolean flag = true;
            String str = gamePlayer.getTopic();
            CallResult callResult = gamePlayer.setNewTopic();
            if(callResult== CallResult.CANCELED)return true;
            if(str==null)
            {
                flag = false;
            }
            if(callResult== CallResult.NO_WORD)
            {
                MessageUtil.sendPlayer(sender,"game.no_word");
                return false;
            }
            TABAPI.setValueTemporarily(player.getUniqueId(), EnumProperty.TAGPREFIX,"["+gamePlayer.getTopic()+"]");
            if(flag)
            {
                Bukkit.broadcastMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("game.new_word").replaceAll("%player",player.getName()).replaceAll("%word",str));
                gamePlayer.setScore(gamePlayer.getScore()+1);
            }
            else {
                assert false;
                Bukkit.broadcastMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("game.first_word").replaceAll("%player",player.getName()).replaceAll("%word",str));
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("set"))
        {
            if(!sender.hasPermission("dndc.set"))
            {
                MessageUtil.sendPlayer(sender,"server.no_perm");
                return true;
            }
            if(args.length!=3)return sendCommandError(sender);
            Player player;
            player = Bukkit.getPlayerExact(args[1]);
            if(player==null)
            {
                MessageUtil.sendPlayer(sender,"server.not_player");
                return false;
            }
            GamePlayer gamePlayer = main.getInstance().getPlayerManager().getGamePlayer(player);
            try {
                gamePlayer.setScore(Integer.parseInt(args[2]));
            }catch (NumberFormatException e)
            {
                MessageUtil.sendPlayer(sender,"server.not_int");
                return false;
            }
            MessageUtil.sendPlayer(sender,"game.score_set");
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
        if(args[0].equalsIgnoreCase("words"))
        {
            if(!sender.hasPermission("dndc.words"))
            {
                MessageUtil.sendPlayer(sender,"server.no_perm");
                return true;
            }
            Player player;
            if(args.length==1)
            {
                if(sender instanceof Player)
                    player = (Player) sender;
                else
                {
                    MessageUtil.sendPlayer(sender,"server.not_player");
                    return true;
                }
                GuiUtil.showWordsGui(player , 1);
            }
            else if(args.length==2)
            {
                if(sender instanceof Player)
                    player = (Player) sender;
                else
                {
                    MessageUtil.sendPlayer(sender,"server.not_player");
                    return true;
                }
                GuiUtil.showWordsGui(player , Integer.valueOf(args[1]));
            }
            else return sendCommandError(sender);
            return true;
        }
        return sendCommandError(sender);
    }
}
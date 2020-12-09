package top.jingwenmc.mcdndc.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.*;

public class SubCommandManager implements CommandExecutor, TabCompleter {
    Map<String, JCommand> map = new HashMap<>();
    List<String> strings = new ArrayList<>();
    public boolean sendCommand(String[] args, CommandSender sender)
    {
        if(args.length==0 || !map.containsKey(args[0])) {
            if (map.containsKey(null)) {
                return map.get(null).onCommand(null, sender);
            }
            else return false;
        }
        for(String s : map.keySet())
        {
            if(args[0].equalsIgnoreCase(s))
            {
                List<String> rt = new ArrayList<>();
                for(int i=1;i<args.length;i++)
                {
                    rt.add(args[i]);
                }
                return map.get(s).onCommand(rt.toArray(new String[0]),sender);
            }
        }
        return false;
    }
    public void register(JCommand command,String root)
    {
        if(isRegistered(root)) throw new IllegalArgumentException("Already Registered");
        map.put(root,command);
        if(root!=null)strings.add(root);
    }
    public void unregister(String root)
    {
        if(isRegistered(root)) {
            if(root!=null)map.remove(root);
            strings.remove(root);
        }
        else throw new IllegalArgumentException("Not Registered");
    }
    public boolean isRegistered(String root)
    {
        return map.containsKey(root);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        boolean isSuccess = sendCommand(args,sender);
        if(!isSuccess) MessageUtil.sendPlayer(sender,"server.no_cmd");
        return true;
    }

    public boolean onCommand(CommandSender sender, String[] args) {
        boolean isSuccess = sendCommand(args,sender);
        if(!isSuccess) MessageUtil.sendPlayer(sender,"server.no_cmd");
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length==1) {
            List<String> completions = new ArrayList<>();
            StringUtil.copyPartialMatches(args[0], strings, completions);
            Collections.sort(completions);
            return completions;
        }
        return null;
    }
}
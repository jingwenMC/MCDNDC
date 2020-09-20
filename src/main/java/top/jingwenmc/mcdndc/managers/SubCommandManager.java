package top.jingwenmc.mcdndc.managers;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import top.jingwenmc.mcdndc.objects.JCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubCommandManager implements CommandExecutor {
    Map<String, JCommand> map = new HashMap<>();
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
                return map.get(s).onCommand(rt.toArray(new String[args.length-1]),sender);
            }
        }
        return false;
    }
    public void register(JCommand command,String root)
    {
        map.put(root,command);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return sendCommand(args,sender);
    }
}
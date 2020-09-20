package top.jingwenmc.mcdndc.legacy.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import top.jingwenmc.mcdndc.legacy.commands.wordkeeper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class wordkeepertab implements TabCompleter {
    List<String> comp = new ArrayList<>();
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length==1) {
            List<String> completions = new ArrayList<>();
            List<String> strings = new ArrayList<>();
            if(sender.hasPermission("dndc.keep.add"))
                strings.add("set");
            if(sender.hasPermission("dndc.keep.use"))
                strings.add("get");
            StringUtil.copyPartialMatches(args[0], strings, completions);
            Collections.sort(completions);
            return completions;
        }
        if(args.length==2)
            if(args[0].equalsIgnoreCase("get"))
                return new ArrayList<>(wordkeeper.map.keySet());
            else if(args[0].equalsIgnoreCase("set"))
                return null;
        if(args.length==3&&args[0].equalsIgnoreCase("set"))
        {
            List<String> completions = new ArrayList<>();
            completions.add(args[2]);
            return completions;
        }
        comp.add("");
        return comp;
    }
}

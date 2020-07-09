package top.jingwenmc.mcdndc.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class mcdndctab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length==1) {
            List<String> completions = new ArrayList<>();
            List<String> strings = new ArrayList<>();
            if (sender.hasPermission("dndc.restart"))
                strings.add("restart");
            if (sender.hasPermission("dndc.reload"))
                strings.add("reload");
            if (sender.hasPermission("dndc.play"))
                strings.add("next");
            if (sender.hasPermission("dndc.set"))
                strings.add("set");
            strings.add("help");
            StringUtil.copyPartialMatches(args[0], strings, completions);
            Collections.sort(completions);
            return completions;
        }
        return null;
    }
}

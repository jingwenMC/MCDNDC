package top.jingwenmc.mcdndc.objects;

import org.bukkit.command.CommandSender;

public abstract class JCommand {
    public abstract boolean onCommand(String[] args, CommandSender sender);
}

package top.jingwenmc.mcdndc.commands.dndc;

import org.bukkit.command.CommandSender;
import top.jingwenmc.mcdndc.commands.dndc.extensions.disable;
import top.jingwenmc.mcdndc.commands.dndc.extensions.enable;
import top.jingwenmc.mcdndc.commands.dndc.extensions.help;
import top.jingwenmc.mcdndc.commands.dndc.extensions.list;
import top.jingwenmc.mcdndc.managers.SubCommandManager;
import top.jingwenmc.mcdndc.objects.JCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class extension extends JCommand {
    SubCommandManager subCommandManager;
    public extension() {
        subCommandManager = new SubCommandManager();
        registerSubCommands();
    }

    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        if(args.length==0)return subCommandManager.onCommand(sender,new String[]{});
        return subCommandManager.onCommand(sender,args);
    }

    private void registerSubCommand(String subCommand,JCommand jCommand) {
        subCommandManager.register(jCommand,subCommand);
    }

    public void registerSubCommands() {
        registerSubCommand(null, new help());
        registerSubCommand("help", new help());
        registerSubCommand("enable",new enable());
        registerSubCommand("disable", new disable());
        registerSubCommand("list", new list());
    }
}

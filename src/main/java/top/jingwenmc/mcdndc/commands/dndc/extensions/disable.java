package top.jingwenmc.mcdndc.commands.dndc.extensions;

import org.bukkit.command.CommandSender;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.util.ExceptionUtil;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class disable extends JCommand {
    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        if(args.length!=1) return false;
        try {
            boolean isEnabled = Main.getInstance().getExtensionManager().disableExtension(args[0]);
            if(isEnabled) MessageUtil.sendPlayer(sender,"extension.on_disable",new String[]{"%name"},args[0]);
            MessageUtil.sendPlayer(sender,"extension.disabled");
        } catch (Throwable e) {
            if(e.getMessage().contains("404")) {
                MessageUtil.sendPlayer(sender,"extension.not_found");
            }
            else ExceptionUtil.print(e);
        }
        return true;
    }
}

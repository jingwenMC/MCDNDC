package top.jingwenmc.mcdndc.commands.dndc.extensions;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.JCommand;
import top.jingwenmc.mcdndc.objects.MCDNDCExtension;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class list extends JCommand {
    @Override
    public boolean onCommand(String[] args, CommandSender sender) {
        if(args.length!=0)return false;
        MessageUtil.sendPlayer(sender,"extension.line");
        MessageUtil.sendPlayer(sender,"extension.about_1");
        MessageUtil.sendPlayer(sender,"extension.about_2");
        MessageUtil.sendPlayer(sender,"extension.loaded",new String[]{"%amount"},
                ""+Main.getInstance().getExtensionManager().getExtensionsArray().length);
        for(MCDNDCExtension extension : Main.getInstance().getExtensionManager().getExtensionsArray()) {
            MessageUtil.sendPlayer(sender,"extension.line");
            MessageUtil.sendPlayer(sender,"extension.info_1",new String[]{"%name"},extension.getExtensionName());
            String status = extension.isEnabled() ?
                    MessageUtil.getMessage("extension.enabled") : MessageUtil.getMessage("extension.disabled");
            MessageUtil.sendPlayer(sender,"extension.info_2",
                    new String[]{"%author","%status"},extension.getAuthor(),status);
            if(sender instanceof Player) {
                Player player = (Player) sender;
                String btn_enable = MessageUtil.getMessage("extension.btn_enable");
                String btn_disable = MessageUtil.getMessage("extension.btn_disable");
                player.sendRawMessage("[{\"text\":\""+btn_enable+"\",\"color\":\"green\",\"bold\":true,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/dndc extension enable "+extension.getExtensionName()+"\"}},{\"text\":\"            \",\"bold\":false,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false},{\"text\":\""+btn_disable+"\",\"color\":\"red\",\"bold\":true,\"italic\":false,\"underlined\":false,\"strikethrough\":false,\"obfuscated\":false,\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/dndc extension disable "+extension.getExtensionName()+"\"}}]");
            }
        }
        MessageUtil.sendPlayer(sender,"extension.line");
        return true;
    }
}

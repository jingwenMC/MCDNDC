package top.jingwenmc.mcdndc.commands.dndc.extensions;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
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
                String enable = MessageUtil.getMessage("extension.btn_enable");
                String disable = MessageUtil.getMessage("extension.btn_disable");
                TextComponent enable_btn = new TextComponent(enable);
                enable_btn.setColor(ChatColor.GREEN);
                enable_btn.setBold(true);
                enable_btn.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/dndc extension enable "+extension.getExtensionName()));
                TextComponent disable_btn = new TextComponent(disable);
                disable_btn.setColor(ChatColor.RED);
                disable_btn.setBold(true);
                disable_btn.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/dndc extension disable "+extension.getExtensionName()));
                TextComponent blank = new TextComponent("            ");
                TextComponent prefix = new TextComponent(MessageUtil.getPrefix());
                player.spigot().sendMessage(prefix,enable_btn,blank,disable_btn);
            }
        }
        MessageUtil.sendPlayer(sender,"extension.line");
        return true;
    }
}

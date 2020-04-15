package top.jingwenmc.mcdndc.commands;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class dndc implements CommandExecutor {
    Plugin plugin = top.jingwenmc.mcdndc.main.getPlugin(top.jingwenmc.mcdndc.main.class);
    private void errmsg(CommandSender sender,String str)
    {
        //In Stance Of
        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            player.sendMessage(ChatColor.RED+"[MCDNDC]执行的指令发生异常,错误信息:");
            player.sendMessage(ChatColor.RED+str);
        }
        else
        {
            System.out.println(ChatColor.RED+"[MCDNDC]执行的指令发生异常,错误信息:");
            System.out.println(ChatColor.RED+str);
        }
    }
    private boolean checkPerm (CommandSender sender,String perm)
    {
        //In Stance Of
        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            return player.hasPermission(perm);
        }
        else
        {
            return true;
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length!=1)
        {
            errmsg(sender,"太少的条件!请输入指令\"/dndc help\"获得帮助!");
            return false;
        }
        //String option = args[0];
        //if(args[0].equalsIgnoreCase("reload"))errmsg(sender,"[DEBUG]你选择了1:"+args[0]);
        //errmsg(sender,"[DEBUG]你选择了:"+args[0]);
        if(args[0].equalsIgnoreCase("reload"))
        {
            if(checkPerm(sender,"dndc.reload"))
            {
                if(sender instanceof Player){Player player = (Player) sender;player.sendMessage(ChatColor.GOLD+"[MCDNDC]正在重载配置文件...");}
                System.out.println(ChatColor.GOLD+"[MCDNDC]正在重载配置文件...");
                top.jingwenmc.mcdndc.main.words = plugin.getConfig().getStringList("words");
                if(top.jingwenmc.mcdndc.main.words.size()==0) errmsg(sender,"配置文件错误:Config-Words-Matches-0.");
                else {
                    int cv = plugin.getConfig().getInt("config_version");
                    boolean isntRightConfig = !(cv == 1);
                    if (isntRightConfig)
                        errmsg(sender,"配置文件版本错误:Config-Version-Expected-1-Got-" + cv + ".");
                    else {
                        if (sender instanceof Player) {Player player = (Player) sender;player.sendMessage(ChatColor.GREEN + "[MCDNDC]配置文件已重载.");}
                        System.out.println(ChatColor.GREEN + "[MCDNDC]配置文件已重载.");
                    }
                }
            }
            else errmsg(sender,"权限不足.");
            return true;
        }
        if(args[0].equalsIgnoreCase("next"))
        {
            if(checkPerm(sender,"dndc.play"))
            {
                if(sender instanceof Player)
                {
                    Player player = (Player) sender;
                    if(top.jingwenmc.mcdndc.main.words.size()!=0){
                        long rand=System.currentTimeMillis();
                        int r2 = ((int)rand)%top.jingwenmc.mcdndc.main.words.size();
                        if(r2<0)r2=-r2;
                        String now = top.jingwenmc.mcdndc.main.words.get(r2);
                        me.neznamy.tab.api.TABAPI.setValueTemporarily(player.getUniqueId(), EnumProperty.TAGPREFIX,"["+now+"]");
                        top.jingwenmc.mcdndc.main.words.remove(r2);
                    }
                    else
                    {
                        errmsg(sender,"词库已耗尽.");
                    }
                }
                else
                {
                    errmsg(sender,"请在游戏中使用这个指令.");
                }
            }
            else
            {
                errmsg(sender,"权限不足.");
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("help"))
        {
            if(sender instanceof Player)
            {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]MCDNDCv0.1-帮助页面");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]====================");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]/dndc(/dnd) - 插件主命令");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]子命令:");
                if(player.hasPermission("dndc.reload"))
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]  reload - 重载词库&配置文件");
                if(player.hasPermission("dndc.play"))
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]  next   - 从词库抽取词语");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]  help   - 帮助页面");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]====================");
            }
            else
            {
                System.out.println(ChatColor.YELLOW+"[MCDNDC]MCDNDCv0.1-帮助页面");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]====================");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]/dndc(/dnd) - 插件主命令");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]子命令:");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]  reload - 重载词库&配置文件");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]  next   - 从词库抽取词语");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]  help   - 帮助页面");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]====================");
            }
        }
        errmsg(sender,"未知指令!请输入指令\"/dndc help\"获得帮助!");
        return false;
    }
}
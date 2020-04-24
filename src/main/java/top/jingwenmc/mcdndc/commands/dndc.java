package top.jingwenmc.mcdndc.commands;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import top.jingwenmc.mcdndc.main;

import java.util.List;
import java.util.Objects;
//Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"命令");
public class dndc implements CommandExecutor {
    //public String type = null;
    Plugin plugin = top.jingwenmc.mcdndc.main.getPlugin(top.jingwenmc.mcdndc.main.class);
    private void sendmsg(CommandSender sender,String str)
    {
        //In Stance Of
        if(sender instanceof Player)
        {
            Player player = (Player)sender;
            player.sendMessage(ChatColor.AQUA+"[MCDNDC]"+str);
        }
        else
        {
            System.out.println(ChatColor.AQUA +"[MCDNDC]"+str);
        }
    }
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
    public int getValue (String string)
    {
        int i;
        string=string.substring(string.lastIndexOf(":")+1).replaceAll("\"", "").replaceAll("]", "");
        i = Integer.parseInt(string);
        return i;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length<1||((!(args[0].equalsIgnoreCase("debug")||args[0].equalsIgnoreCase("set")))&&args.length<3)||args.length>3)
        {
            errmsg(sender,"太少的条件!请输入指令\"/dndc help\"获得帮助!");
            return false;
        }
        //String option = args[0];
        //if(args[0].equalsIgnoreCase("reload"))errmsg(sender,"[DEBUG]你选择了1:"+args[0]);
        //errmsg(sender,"[DEBUG]你选择了:"+args[0]);
        if(args[0].equalsIgnoreCase("reload"))
        {
            if(checkPerm(sender,"dndc.reload")) {
                sendmsg(sender, "正在重载配置文件...");
                plugin.reloadConfig();
                sendmsg(sender, "配置文件重载完成,正在重启游戏...");
                main.notify_player_after_next_word = plugin.getConfig().getString("notify_player_after_next_word");
                main.words = plugin.getConfig().getStringList("words");
                if (main.words.size() == 0) errmsg(sender, "配置文件错误:Config-Words-Matches-0.");
                else {
                    int cv = plugin.getConfig().getInt("config_version");
                    boolean isntRightConfig = !(cv == 2);
                    if (isntRightConfig)
                        errmsg(sender, "配置文件版本错误:Config-Version-Expected-2-Got-" + cv + ".");
                    else {
                        if (plugin.getConfig().getBoolean("reset_tag_on_restart"))
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                TABAPI.setValueTemporarily(p.getUniqueId(), EnumProperty.TAGPREFIX, null);
                            }
                        if (sender instanceof Player) {
                            Player player = (Player) sender;
                            player.sendMessage(ChatColor.GREEN + "[MCDNDC]游戏已重载.");
                        }
                        System.out.println(ChatColor.GREEN + "[MCDNDC]游戏已重载.");
                    }
                }
            }
            else errmsg(sender,"权限不足.");
            return true;
        }
        if(args[0].equalsIgnoreCase("restart"))
        {
            if(checkPerm(sender,"dndc.restart"))
            {
                if(sender instanceof Player){
                    Player player = (Player) sender;
                    player.sendMessage(ChatColor.GOLD+"[MCDNDC]正在重启游戏...");
                }
                System.out.println(ChatColor.GOLD+"[MCDNDC]正在重启游戏...");
                //type = null;
                main.notify_player_after_next_word = plugin.getConfig().getString("notify_player_after_next_word");
                main.words = plugin.getConfig().getStringList("words");
                if(main.words.size()==0) errmsg(sender,"配置文件错误:Config-Words-Matches-0.");
                else {
                    int cv = plugin.getConfig().getInt("config_version");
                    boolean isntRightConfig = !(cv == 3);
                    if (isntRightConfig)
                        errmsg(sender,"配置文件版本错误:Config-Version-Expected-3-Got-" + cv + ".");
                    else {
                        if(plugin.getConfig().getBoolean("reset_tag_on_restart"))
                        for(Player p : Bukkit.getOnlinePlayers())
                        {
                            TABAPI.setValueTemporarily(p.getUniqueId(), EnumProperty.TAGPREFIX,null);
                        }
                        if(plugin.getConfig().getBoolean("reset_score_on_restart"))
                        {
                            sendmsg(sender,"正在重置分数...");
                            for(Player p : Bukkit.getOnlinePlayers())
                            {
                                TABAPI.setValueTemporarily(p.getUniqueId(),EnumProperty.TABSUFFIX,ChatColor.GOLD+"[分数:"+0+"]");
                            }
                        }
                        if (sender instanceof Player) {Player player = (Player) sender;player.sendMessage(ChatColor.GREEN + "[MCDNDC]游戏已重载.");}
                        System.out.println(ChatColor.GREEN + "[MCDNDC]游戏已重载.");
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
                    if(plugin.getConfig().getBoolean("add_point"))
                    {
                        int i = getValue(TABAPI.getTemporaryValue(player.getUniqueId(), EnumProperty.TABSUFFIX));
                        i++;
                        TABAPI.setValueTemporarily(player.getUniqueId(),EnumProperty.TABSUFFIX,ChatColor.GOLD+"[分数:"+i+"]");
                    }
                    String prev = TABAPI.getTemporaryValue(player.getUniqueId(),EnumProperty.TAGPREFIX);
                    if(main.words.size()!=0){
                        long rand=System.currentTimeMillis();
                        int r2 = ((int)rand)% main.words.size();
                        if(r2<0)r2=-r2;
                        String now = main.words.get(r2);
                        TABAPI.setValueTemporarily(player.getUniqueId(), EnumProperty.TAGPREFIX,"["+now+"]");
                        main.words.remove(r2);
                        player.sendMessage(ChatColor.GREEN+"[MCDNDC]成功抽取下个词语");
                        if(Objects.requireNonNull(main.notify_player_after_next_word).equalsIgnoreCase("message")&&(prev!=null)) {
                            player.sendMessage(ChatColor.AQUA+"[MCDNDC]你之前的词语是"+prev);
                        }
                        if(Objects.requireNonNull(main.notify_player_after_next_word).equalsIgnoreCase("broadcast")&&(prev!=null))
                        {
                            for(Player p : Bukkit.getOnlinePlayers())
                            {
                                p.sendMessage(ChatColor.AQUA+"[MCDNDC]"+player.getDisplayName()+"刚刚抽取了一个词语，他之前的词语是"+prev);
                            }
                        }
                        else if(prev==null)
                        {
                            for(Player p : Bukkit.getOnlinePlayers())
                            {
                                p.sendMessage(ChatColor.AQUA+"[MCDNDC]"+player.getDisplayName()+"刚刚抽取了一个词语.");
                            }
                        }
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
        //XXX:help
        if(args[0].equalsIgnoreCase("set"))
        {
            if(args.length==1)
            {
                errmsg(sender,"太少的条件!正确的用法是\"/dndc set <玩家> <分数>\"!");
                return false;
            }
            Player target = Bukkit.getPlayerExact(args[1]);
            if(target != null)
            //if (target instanceof Player)
            {
                TABAPI.setValueTemporarily(target.getUniqueId(),EnumProperty.TABSUFFIX,ChatColor.GOLD+"[分数:"+args[2]+"]");
                sendmsg(sender,"分数设置成功.");
            }
            else
            {
                errmsg(sender,"玩家不存在/不在线");
            }
            //else errmsg(sender,"NullPointerError");
            return true;
        }
        if(args[0].equalsIgnoreCase("help"))
        {
            if(sender instanceof Player)
            {
                Player player = (Player) sender;
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]MCDNDCv0.2.0-帮助页面");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]====================");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]/dndc(/dnd,/byz) - 插件主命令");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]子命令:");
                if(player.hasPermission("dndc.reload"))
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]  reload  - 重载配置文件");
                if(player.hasPermission("dndc.restart"))
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]  restart - 重新加载游戏");
                if(player.hasPermission("dndc.play"))
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]  next   - 从词库抽取词语");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]  set    - 设置分数");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]  help   - 帮助页面");
                player.sendMessage(ChatColor.YELLOW+"[MCDNDC]====================");
            }
            else
            {
                System.out.println(ChatColor.YELLOW+"[MCDNDC]MCDNDCv0.2.0-帮助页面");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]====================");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]/dndc(/dnd,/byz) - 插件主命令");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]子命令:");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]  reload  - 重载配置文件");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]  restart - 重新加载游戏");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]  next   - 从词库抽取词语");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]  set    - 设置分数");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]  help   - 帮助页面");
                System.out.println(ChatColor.YELLOW+"[MCDNDC]====================");
            }
            return true;
        }
        if(args[0].equalsIgnoreCase("debug")&&checkPerm(sender,"dndc.admin"))
        {
            if(args.length==1||args.length==3)
            {
                sendmsg(sender,"=====JDebugTool@MCDNDC=====");
                sendmsg(sender,"jingwen的秘密调试工具-ForDevPurpose");
                sendmsg(sender,"可用:gettagprefix,gettabsuffix");
                sendmsg(sender,"=====JDebugTool@MCDNDC=====");
                return true;
            }
            if(args[1].equalsIgnoreCase("gettagprefix"))
            {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    sendmsg(sender, "Your TagPrefix:"+TABAPI.getTemporaryValue(player.getUniqueId(), EnumProperty.TAGPREFIX));
                }
                else
                {
                    errmsg(sender,"JD-NonPlayerException");
                }
            }
            if(args[1].equalsIgnoreCase("gettabsuffix"))
            {
                if(sender instanceof Player) {
                    Player player = (Player) sender;
                    sendmsg(sender, "Your TabSuffix:"+TABAPI.getTemporaryValue(player.getUniqueId(), EnumProperty.TABSUFFIX));
                }
                else
                {
                    errmsg(sender,"JD-NonPlayerException");
                }
            }
            return true;
        }
        errmsg(sender,"未知指令!请输入指令\"/dndc help\"获得帮助!");
        return false;
    }
}
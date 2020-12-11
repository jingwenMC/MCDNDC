package top.jingwenmc.mcdndc.managers;
import org.bukkit.Bukkit;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.util.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class GameManager {
    public static List<String> words = new ArrayList<>();
    public String newWord()
    {
        if(words.size()==0)return null;
        long rand=System.currentTimeMillis();
        int r2 = ((int)rand)% words.size();
        if(r2<0)r2=-r2;
        String now = words.get(r2);
        words.remove(r2);
        return now;
    }
    public void resetList()
    {
        try {
            ConfigUtil.checkConfigVersion();
            words.clear();
            for (String s : ConfigUtil.getStringList("words")) {
                MessageUtil.sendConsole("words.line");
                Bukkit.getLogger().info(MessageUtil.getPrefix() +
                        MessageUtil.getMessage("words.loading").replaceAll("%file", s));
                File folder = new File(Main.getInstance().getDataFolder(), "words");
                File file = new File(folder, s);
                if (!file.exists()) {
                    if(s.equals("words.yml"))
                    {
                        new ConfigAccessor(Main.getInstance(),new File(Main.getInstance().getDataFolder(),"words"),"words.yml").saveDefaultConfig(true);
                    }
                    else {
                        Bukkit.getLogger().info(MessageUtil.getPrefix() +
                                MessageUtil.getMessage("words.not_found").replaceAll("%file", s));
                    }
                }
                ConfigAccessor c = new ConfigAccessor(Main.getInstance(), folder, s);
                words.addAll(Objects.requireNonNull(WordListReader.readWords(c.getConfig())));
                MessageUtil.sendConsole("words.line");
            }
        }
        catch (Throwable e)
        {
            ExceptionUtil.print(e);
        }
    }
}
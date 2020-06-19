package top.jingwenmc.mcdndc.managers;

import org.bukkit.Bukkit;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.util.MessageUtil;

import javax.naming.ConfigurationException;
import java.util.ArrayList;
import java.util.List;

public class GameManager {
    public List<String> words = new ArrayList<>();
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

        if(main.getInstance().getConfigAccessor().getConfig().getInt("config_version")!=main.cv)
        {
            MessageUtil.sendConsole("console.version_not_correct");
            try {
                throw new ConfigurationException("MCDCDC : Config Version Not Correct!");
            } catch (ConfigurationException e) {
                e.printStackTrace();
                System.out.println("You may delete /plugin/MCDNDC/config.yml to generate a new config.");
            }
            Bukkit.getPluginManager().disablePlugin(main.getInstance());
        }
        try {
            words = main.getInstance().getConfigAccessor().getConfig().getStringList("words");
            if(words.size()==0)
            {
                throw new ConfigurationException("MCDNDC: Words Size Cant Be 0");
            }
        }
        catch (ConfigurationException e)
        {
            MessageUtil.sendConsole("console.conf_not_correct");
            e.printStackTrace();
            System.out.println("You may delete /plugin/MCDNDC/config.yml to generate a new config.");
            Bukkit.getPluginManager().disablePlugin(main.getInstance());
        }
    }
}

package top.jingwenmc.mcdndc.util;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class WordListReader {
    final static String required_versions = "1";
    final static int latest_version = 1;
    public static List<String> readWords(FileConfiguration configuration)
    {
        try {
            int scv;
            Set<String> set = configuration.getKeys(false);
            if (!set.contains("schema_version")) {
                Bukkit.getLogger().info(MessageUtil.getPrefix() +
                        MessageUtil.getMessage("words.not_compatable").replaceAll("%ver", "[NOT FOUND]")
                                .replaceAll("%latest", required_versions));
                return null;
            }
            scv = configuration.getInt("schema_version");


            //SCV1
            if (scv == 1) {
                String[] sc1_required_keys = {"name", "schema_version", "words"};
                //If Not Full
                if (!set.containsAll(Arrays.asList(sc1_required_keys))) {
                    MessageUtil.sendConsole("words.error");
                    return null;
                }
                List<String> rt = configuration.getStringList("words");
                Bukkit.getLogger().info(MessageUtil.getPrefix() +
                        MessageUtil.getMessage("words.name").replaceAll("%name", Objects.requireNonNull(configuration.getString("name"))));
                Bukkit.getLogger().info(MessageUtil.getPrefix() +
                        MessageUtil.getMessage("words.amount").replaceAll("%amount", String.valueOf(rt.size())));
                return rt;
            } else {
                Bukkit.getLogger().info(MessageUtil.getPrefix() +
                        MessageUtil.getMessage("words.not_compatable").replaceAll("%ver", String.valueOf(scv))
                                .replaceAll("%latest", required_versions));
                return null;
            }
        }
        catch (Throwable e)
        {
            ExceptionUtil.print(e);
        }
        return null;
    }
}

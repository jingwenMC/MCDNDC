package top.jingwenmc.mcdndc.extension.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.events.NewWordEvent;
import top.jingwenmc.mcdndc.managers.GameManager;
import top.jingwenmc.mcdndc.objects.MCDNDCExtension;

public class ScoreboardExtensionLoader extends MCDNDCExtension implements Listener {
    private BukkitTask task;
    private ScoreboardManager scoreboardManager = new ScoreboardManager();
    public ScoreboardExtensionLoader() {
        super("internal_scoreboard", "jingwenMC");
    }

    @Override
    public void onLoad() {
        Bukkit.getPluginManager().registerEvents(this,Main.getInstance());
    }

    @Override
    public void onEnable() {
        if (!getConfigSection().isSet("method"))
            getConfigSection().set("method", "SIDEBAR");
        if (!getConfigSection().isSet("interval"))
            getConfigSection().set("interval", 20);
        if (!getConfigSection().isSet("add_score_on_switch"))
            getConfigSection().set("add_score_on_switch", true);
        saveConfig();
        task = new BukkitRunnable()
        {
            @Override
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers())
                {
                    scoreboardManager.updateOneScoreboard(p);
                }
            }
        }.runTaskTimer(Main.getInstance(),getConfigSection().getInt("interval"),getConfigSection().getInt("interval"));
    }

    @Override
    public void onDisable() {
        task.cancel();
        for (Player p : Bukkit.getOnlinePlayers())
        {
            scoreboardManager.resetScoreboard(p);
        }
    }

    @EventHandler
    public void onNewWord(NewWordEvent event) {
        if(!GameManager.words.isEmpty() && !(event.isNew() || !getConfigSection().getBoolean("add_score_on_switch") || !isEnabled())) {
            event.getGamePlayer().setScore(event.getGamePlayer().getScore()+1);
        }
    }
}

package top.jingwenmc.mcdndc.extension.scoreboard;

import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import top.jingwenmc.mcdndc.objects.GamePlayer;
import top.jingwenmc.mcdndc.objects.MCDNDCExtension;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.Objects;

public class ScoreboardManager {
    private MCDNDCExtension getExtension() {
        return Main.getInstance().getExtensionManager().getExtension("internal_scoreboard");
    }
    private Scoreboard getEmptyScoreBoard() {
        Scoreboard board = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective ob = board.registerNewObjective("sidebar","dummy","TITLE_NOT_SET");
        ob.setDisplayName(MessageUtil.getMessage("scoreboard"));
        String method = getExtension().getConfigSection().getString("method");
        switch (Objects.requireNonNull(method)) {
            case "SIDEBAR":
                ob.setDisplaySlot(DisplaySlot.SIDEBAR);
                break;
            case "BELOW_NAME":
                ob.setDisplaySlot(DisplaySlot.BELOW_NAME);
                break;
            case "PLAYER_LIST":
                ob.setDisplaySlot(DisplaySlot.PLAYER_LIST);
                break;
        }
        return board;
    }
    public void updateOneScoreboard(Player p) {
        if(!getExtension().isEnabled())return;
        Scoreboard sb = getEmptyScoreBoard();
        Objective obj = null;
        if(Objects.equals(getExtension().getConfigSection().getString("method"), "SIDEBAR"))
            obj = sb.getObjective(DisplaySlot.SIDEBAR);
        if(Objects.equals(getExtension().getConfigSection().getString("method"), "BELOW_NAME"))
            obj = sb.getObjective(DisplaySlot.BELOW_NAME);
        if(Objects.equals(getExtension().getConfigSection().getString("method"), "PLAYER_LIST"))
            obj = sb.getObjective(DisplaySlot.PLAYER_LIST);
        for(GamePlayer gamePlayer : Main.getInstance().getPlayerManager().getMap().values())
        {
            assert obj != null;
            Score s;
            if(Objects.equals(getExtension().getConfigSection().getString("method"), "SIDEBAR"))
                s = obj.getScore(ChatColor.WHITE+gamePlayer.getPlayer().getName());
            else
                s = obj.getScore(gamePlayer.getPlayer().getName());
            s.setScore(gamePlayer.getScore());
        }
        p.setScoreboard(sb);
    }

    public void resetScoreboard(Player player) {
        player.setScoreboard(getEmptyScoreBoard());
    }
}

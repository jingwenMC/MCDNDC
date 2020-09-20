package top.jingwenmc.mcdndc.legacy.util;

import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class ScoreboardUtil {
    private static Scoreboard getEmptyScoreBoard() {
        Scoreboard board = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective ob = board.registerNewObjective("sidebar","dummy","TITLE_NOT_SET");
        ob.setDisplayName(MessageUtil.getMessage("scoreboard"));
        switch (Objects.requireNonNull(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard"))) {
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
    public static void updateOneScoreboard(Player p) {
        Scoreboard sb = getEmptyScoreBoard();
        Objective obj = null;
        if(Objects.equals(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard"), "SIDEBAR"))
        obj = sb.getObjective(DisplaySlot.SIDEBAR);
        if(Objects.equals(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard"), "BELOW_NAME"))
            obj = sb.getObjective(DisplaySlot.BELOW_NAME);
        if(Objects.equals(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard"), "PLAYER_LIST"))
            obj = sb.getObjective(DisplaySlot.PLAYER_LIST);
        for(GamePlayer gamePlayer : main.getInstance().getPlayerManager().getMap().values())
        {
            assert obj != null;
            Score s;
            if(Objects.equals(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard"), "SIDEBAR"))
            s = obj.getScore(ChatColor.WHITE+gamePlayer.getPlayer().getName());
            else
                s = obj.getScore(gamePlayer.getPlayer().getName());
            s.setScore(gamePlayer.getScore());
        }
        p.setScoreboard(sb);
    }
}

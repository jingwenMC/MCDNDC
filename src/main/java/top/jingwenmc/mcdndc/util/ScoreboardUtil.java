package top.jingwenmc.mcdndc.util;

import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class ScoreboardUtil {
    private static Scoreboard getEmptyScoreBoard() {
        Scoreboard board = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        Objective ob = board.registerNewObjective("sidebar","dummy","");
        ob.setDisplayName(ChatColor.YELLOW + ChatColor.BOLD.toString() + "Score");
        if(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard").equals("SIDEBAR"))
        ob.setDisplaySlot(DisplaySlot.SIDEBAR);
        else if(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard").equals("BELOW_NAME"))
            ob.setDisplaySlot(DisplaySlot.BELOW_NAME);
            else if(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard").equals("PLAYER_LIST"))
            ob.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        return board;
    }
    public static void updateOneScoreboard(Player p) {
        Scoreboard sb = getEmptyScoreBoard();
        Objective obj = null;
        if(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard").equals("SIDEBAR"))
        obj = sb.getObjective(DisplaySlot.SIDEBAR);
        if(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard").equals("BELOW_NAME"))
            obj = sb.getObjective(DisplaySlot.BELOW_NAME);
        if(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard").equals("PLAYER_LIST"))
            obj = sb.getObjective(DisplaySlot.PLAYER_LIST);
        for(GamePlayer gamePlayer : main.getInstance().getPlayerManager().map.values())
        {
            assert obj != null;
            Score s;
            if(main.getInstance().getConfigAccessor().getConfig().getString("scoreboard").equals("SIDEBAR"))
            s = obj.getScore(ChatColor.WHITE+gamePlayer.getPlayer().getName());
            else
                s = obj.getScore(gamePlayer.getPlayer().getName());
            s.setScore(gamePlayer.getScore());
        }
        p.setScoreboard(sb);
    }
}

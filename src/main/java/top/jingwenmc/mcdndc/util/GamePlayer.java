package top.jingwenmc.mcdndc.util;

import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.main;

public class GamePlayer {
    private String topic = null;
    private int score = 0;
    private Player player;
    public GamePlayer(Player p){
        player = p;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTopic() {
        return topic;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean setNewTopic()
    {
        this.topic = main.getInstance().getGameManager().newWord();
        if(topic==null)return false;
        else return true;
    }
}

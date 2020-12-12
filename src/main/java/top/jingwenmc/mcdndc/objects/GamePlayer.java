package top.jingwenmc.mcdndc.objects;
import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.enums.CallResult;
import top.jingwenmc.mcdndc.events.NewWordEvent;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.managers.GameManager;

import java.util.HashMap;
import java.util.Map;

/**
 * GamePlayer instance
 */
public class GamePlayer {
    /**
     * Storage For Extension
     */
    public Map<String,Object> STORAGE = new HashMap<>();
    private String topic = null;
    private int score = 0;
    private Player player;

    /**
     * GamePlayer constructor
     * @param p Player that belongs to
     */
    public GamePlayer(Player p){
        player = p;
    }

    /**
     * Get the Player that belongs to
     * @return Player that belongs to
     */
    public Player getPlayer() {
        return player;
    }
    /**
     * Get the player's topic
     * @return topic
     */
    public String getTopic() {
        return topic;
    }
    /**
     * Get the player's score
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Set the player's score
     * @param score the score will be set
     */
    public void setScore(int score) {
        this.score = score;
    }
    /**
     * Set the player's topic
     * @param topic the topic will be set
     */
    public void setTopic(String topic) {
        if(topic == null || topic.isEmpty())Main.getInstance().getProviderManager().requestWordChange(this,"&r");
        Main.getInstance().getProviderManager().requestWordChange(this,topic);
        this.topic = topic;
    }

    /**
     * Get a new random topic and set it to player
     * @return Return {@link CallResult}.NO_WORD if topic list is empty;
     * Return {@link CallResult}.CANCELED if event was canceled
     */
    public CallResult setNewTopic(boolean isNew)
    {
        NewWordEvent event = new NewWordEvent(this,isNew);
        Main.getInstance().getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())return CallResult.CANCELED;
        boolean flag = GameManager.words.isEmpty();
        String newTopic;
        if(!flag)newTopic = Main.getInstance().getGameManager().newWord();
        else return CallResult.NO_WORD;
        setTopic(newTopic);
        return CallResult.SUCCESS;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
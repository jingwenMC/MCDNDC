package top.jingwenmc.mcdndc.util;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import top.jingwenmc.mcdndc.events.MCDNDCEvent;
import top.jingwenmc.mcdndc.events.NewWordEvent;
import top.jingwenmc.mcdndc.main;

/**
 * GamePlayer instance
 */
public class GamePlayer {
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
        this.topic = topic;
    }

    /**
     * Get a new random topic and set it to player
     * @return Return {@link CallResult}.NO_WORD if topic list is empty;
     * Return {@link CallResult}.CANCELED if event was canceled
     */
    public CallResult setNewTopic()
    {
        NewWordEvent event = new NewWordEvent(this);
        main.getInstance().getServer().getPluginManager().callEvent(event);
        if(event.isCancelled())return CallResult.CANCELED;
        this.topic = main.getInstance().getGameManager().newWord();
        if(topic==null)return CallResult.NO_WORD;
        else
            return CallResult.SUCCESS;
    }
}

package top.jingwenmc.mcdndc.managers;

import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.objects.GamePlayer;

import java.util.HashMap;

public class PlayerManager {
    private HashMap<String, GamePlayer> map = new HashMap<>();

    /**
     * Add a player to the manager
     * @param p Player that will be add
     * @return {@link GamePlayer} that added
     */
    public GamePlayer createGamePlayer(Player p){
        if(map.containsKey(p.getName())){
            return map.get(p.getName());
        }
        GamePlayer gp = new GamePlayer(p);
        map.put(p.getName(), gp);
        return gp;
    }
    /**
     * Remove a player from the manager
     * @param name Player name that will be remove
     */
    public void removeGamePlayer(String name){
        map.remove(name);
    }

    /**
     * Get a GamePlayer from the manager by Player
     * if the Player was not found,will create a new GamePlayer
     * @param p Player that will get
     * @return  {@link GamePlayer} that get
     */
    public GamePlayer getGamePlayer(Player p){
        GamePlayer gp = map.get(p.getName());
        if(gp == null){
            return createGamePlayer(p);
        }
        return gp;
    }

    public GamePlayer getGamePlayer(String name) throws NullPointerException{
        GamePlayer gp = map.get(name);
        if(gp == null) {
            throw new NullPointerException("Cannot Found The Player");
        }
        return gp;
    }

    /**
     * Get the map of GamePlayers in the manager
     * @return the HashMap
     */
    public HashMap<String,GamePlayer> getMap()
    {
        return map;
    }
}
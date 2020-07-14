package top.jingwenmc.mcdndc.managers;

import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.util.GamePlayer;

import java.util.HashMap;

public class PlayerManager {
    private HashMap<String, GamePlayer> map = new HashMap<>();

    /**
     * Add a player to the manager
     * @param p Player that will be add
     * @return GamePlayer that added
     * @deprecated can be added by getGamePlayer()
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
     * @return  GamePlayer that get
     */
    public GamePlayer getGamePlayer(Player p){
        GamePlayer gp = map.get(p.getName());
        if(gp == null){
            return createGamePlayer(p);
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
/*
MIT License

Copyright (c) 2017 Matthew Yu

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
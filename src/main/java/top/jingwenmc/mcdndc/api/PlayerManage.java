package top.jingwenmc.mcdndc.api;

import org.bukkit.entity.Player;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.GamePlayer;

public class PlayerManage {
    public static GamePlayer getGamePlayerByPlayer(Player player)
    {
        return Main.INSTANCE.getPlayerManager().getGamePlayer(player);
    }
    public static GamePlayer getGamePlayerByName(String name)
    {
        return Main.INSTANCE.getPlayerManager().getGamePlayer(name);
    }
}

package top.jingwenmc.mcdndc.provider;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import me.neznamy.tab.api.TabPlayer;
import org.bukkit.ChatColor;
import top.jingwenmc.mcdndc.objects.GamePlayer;
import top.jingwenmc.mcdndc.objects.MCDNDCProvider;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class TABProvider extends MCDNDCProvider {
    @Override
    public void onWordSet(GamePlayer gamePlayer, String word) {
        TabPlayer player = TABAPI.getPlayer(gamePlayer.getPlayer().getUniqueId());
        player.setValueTemporarily(EnumProperty.TAGPREFIX, ChatColor.GOLD
                +"[" +ChatColor.translateAlternateColorCodes('&',word)+ChatColor.GOLD+"]&r");
    }

    @Override
    public void onLoad() {
        //onLoad
        MessageUtil.sendConsole("provider.tab_load");
    }
}

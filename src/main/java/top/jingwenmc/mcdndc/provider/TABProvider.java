package top.jingwenmc.mcdndc.provider;

import me.neznamy.tab.api.EnumProperty;
import me.neznamy.tab.api.TABAPI;
import org.bukkit.ChatColor;
import top.jingwenmc.mcdndc.objects.GamePlayer;
import top.jingwenmc.mcdndc.objects.MCDNDCProvider;
import top.jingwenmc.mcdndc.util.MessageUtil;

public class TABProvider extends MCDNDCProvider {
    @Override
    public void onWordSet(GamePlayer gamePlayer, String word) {
        TABAPI.setValueTemporarily(gamePlayer.getPlayer().getUniqueId(), EnumProperty.ABOVENAME, ChatColor.GOLD+word);
    }

    @Override
    public void onLoad() {
        //onLoad
        MessageUtil.sendConsole("provider.tab_load");
    }
}

package top.jingwenmc.mcdndc.util;

import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.extension.itemswitch.ItemSwitchExtensionLoader;
import top.jingwenmc.mcdndc.extension.scoreboard.ScoreboardExtensionLoader;

public class Loader {
    public static void loadExtension() {
        Main.getInstance().getExtensionManager().registerNewExtension(new ItemSwitchExtensionLoader());
        Main.getInstance().getExtensionManager().registerNewExtension(new ScoreboardExtensionLoader());
    }
}

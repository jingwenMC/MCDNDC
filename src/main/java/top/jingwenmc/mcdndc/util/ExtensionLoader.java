package top.jingwenmc.mcdndc.util;

import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.extension.itemswitch.ItemSwitchExtensionLoader;

public class ExtensionLoader {
    public static void load() {
        Main.getInstance().getExtensionManager().registerNewExtension(new ItemSwitchExtensionLoader());
    }
}

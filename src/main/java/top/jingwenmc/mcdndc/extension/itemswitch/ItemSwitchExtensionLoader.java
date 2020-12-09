package top.jingwenmc.mcdndc.extension.itemswitch;

import org.bukkit.Bukkit;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.MCDNDCExtension;

public class ItemSwitchExtensionLoader extends MCDNDCExtension {
    public static ItemSwitchListener listener;
    public ItemSwitchExtensionLoader() {
        super("internal_itemswitch", "jingwenMC");
    }

    @Override
    public void onLoad() {
        listener = new ItemSwitchListener();
        listener.init();
        Bukkit.getPluginManager().registerEvents(listener, Main.getInstance());
    }

    @Override
    public void onEnable() {
        //System.out.println("Extension Enabled!");
    }

    @Override
    public void onDisable() {
        //System.out.println("Extension Disabled!");
    }
}

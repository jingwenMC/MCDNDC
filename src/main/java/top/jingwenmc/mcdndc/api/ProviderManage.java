package top.jingwenmc.mcdndc.api;

import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.MCDNDCProvider;

public class ProviderManage {
    public static void registerExternalProvider(MCDNDCProvider provider,String providerName)
    {
        Main.INSTANCE.providerManager.registerProvider(provider,providerName);
    }
    public static void loadProvider(boolean reloadConfig,boolean silent)
    {
        Main.INSTANCE.providerManager.loadProvider(reloadConfig,silent);
    }
    public static void loadProvider(boolean reloadConfig)
    {
        Main.INSTANCE.providerManager.loadProvider(reloadConfig);
    }
    public static void loadProvider()
    {
        Main.INSTANCE.providerManager.loadProvider();
    }
}

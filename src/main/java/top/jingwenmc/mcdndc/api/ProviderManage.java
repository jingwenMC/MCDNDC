package top.jingwenmc.mcdndc.api;

import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.MCDNDCProvider;

public class ProviderManage {
    public static void registerExternalProvider(MCDNDCProvider provider,String providerName)
    {
        Main.getInstance().providerManager.registerProvider(provider,providerName);
    }
    public static void reloadProvider(boolean reloadConfig,boolean silent)
    {
        Main.getInstance().providerManager.loadProvider(reloadConfig,silent);
    }
    public static void reloadProvider(boolean reloadConfig)
    {
        Main.getInstance().providerManager.loadProvider(reloadConfig);
    }
    public static void reloadProvider()
    {
        Main.getInstance().providerManager.loadProvider();
    }
}

package top.jingwenmc.mcdndc.api;

import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.MCDNDCProvider;

public class ProviderManage {
    public static void registerExternalProvider(MCDNDCProvider provider,String providerName)
    {
        Main.getInstance().getProviderManager().registerProvider(provider,providerName);
    }
    public static void reloadProvider(boolean reloadConfig,boolean silent)
    {
        Main.getInstance().getProviderManager().loadProvider(reloadConfig,silent);
    }
    public static void reloadProvider(boolean reloadConfig)
    {
        Main.getInstance().getProviderManager().loadProvider(reloadConfig);
    }
    public static void reloadProvider()
    {
        Main.getInstance().getProviderManager().loadProvider();
    }
}

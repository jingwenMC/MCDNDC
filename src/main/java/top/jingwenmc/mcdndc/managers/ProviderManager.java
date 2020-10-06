package top.jingwenmc.mcdndc.managers;

import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.objects.GamePlayer;
import top.jingwenmc.mcdndc.objects.MCDNDCProvider;
import top.jingwenmc.mcdndc.util.ConfigUtil;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.HashMap;
import java.util.Map;

public class ProviderManager {
    private final Map<String , MCDNDCProvider> map = new HashMap<>();
    private String now_provider = "DEFAULT";
    private boolean request_change = false;
    public void registerProvider(MCDNDCProvider provider,String providerName)
    {
        if(provider==null)throw new IllegalArgumentException("Provider Cannot Be Null");
        if(map.containsKey(providerName))throw new IllegalArgumentException("Provider Already Exists");
        map.put(providerName,provider);
    }
    public void requestWordChange(GamePlayer gamePlayer,String word)
    {
        if(request_change)loadProvider(true,true);
        if(!map.containsKey(now_provider))
        {
            request_change=true;
            MessageUtil.sendServer("server.no_provider");
            return;
        }
        map.get(now_provider).onWordSet(gamePlayer,word);
        request_change=false;
    }
    public void loadProvider(boolean reload,boolean silent)
    {
        if(reload) {
            MessageUtil.sendConsole("console.reload_config");
            Main.config.reloadConfig();
        }
        boolean change = !now_provider.equals(ConfigUtil.getString("word_provider"));
        now_provider= ConfigUtil.getString("word_provider");
        if(!map.containsKey(now_provider))
        {
            if(!silent) {
                MessageUtil.sendServer("server.no_provider");
            }
            return;
        }
        if(change)
        {
            map.get(now_provider).onLoad();
        }
    }
    public void loadProvider()
    {
        loadProvider(false);
    }
    public void loadProvider(boolean reload)
    {
        loadProvider(reload,false);
    }
}

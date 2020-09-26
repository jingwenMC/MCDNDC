package top.jingwenmc.mcdndc.objects;

public abstract class MCDNDCProvider {
    public abstract void onWordSet(GamePlayer gamePlayer,String word);
    public abstract void onLoad();
}

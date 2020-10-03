package top.jingwenmc.mcdndc.managers;
import top.jingwenmc.mcdndc.util.ConfigUtil;

import java.util.ArrayList;
import java.util.List;

public class GameManager {
    public List<String> words = new ArrayList<>();
    public String newWord()
    {
        if(words.size()==0)return null;
        long rand=System.currentTimeMillis();
        int r2 = ((int)rand)% words.size();
        if(r2<0)r2=-r2;
        String now = words.get(r2);
        words.remove(r2);
        return now;
    }
    public void resetList()
    {
        //TODO:Check Conf Version and load word list
        ConfigUtil.checkConfigVersion();

    }
}
package top.jingwenmc.mcdndc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.enums.UpdateCheckResult;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UpdateUtil implements Listener {
    static String ver_now = "0";
    static String ver_new = "0";
    public static void checkUpdateAsync()
    {
        new BukkitRunnable() {
            @Override
            public void run() {
                System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.check"));
                UpdateCheckResult result = updateExists();
                if (result.equals(UpdateCheckResult.SNAPSHOT)) {
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.dev_l1"));
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.dev_l2"));
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.dev_l3"));
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.dev_l4")+"https://github.com/jingwenMC/MCDNDC/actions");
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.dev_l5")+"https://github.com/jingwenMC/MCDNDC/releases");
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.dev_l6")+"https://github.com/jingwenMC/MCDNDC/issues");
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.dev_l7"));
                } else if (result.equals(UpdateCheckResult.LATEST_RELEASE)) {
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.latest"));
                } else if (result.equals(UpdateCheckResult.NEW_RELEASE)) {
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.new_ver"));
                    System.out.println(MessageUtil.getPrefix() + "v" + ver_now + " -> v" + ver_new);
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.download_addr")+"https://github.com/jingwenMC/MCDNDC/releases/latest");
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (p.isOp()) {
                            p.sendMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("update.new_ver"));
                            p.sendMessage(MessageUtil.getPrefix() + "v" + ver_now + " -> v" + ver_new);
                            p.sendMessage(MessageUtil.getPrefix()+MessageUtil.getMessage("update.download_addr")+"https://github.com/jingwenMC/MCDNDC/releases/latest");
                        }
                    }
                }
                else
                {
                    System.out.println(MessageUtil.getPrefix()+MessageUtil.getMessage("update.error"));
                }
            }
        }.runTaskAsynchronously(main.getInstance());
    }
    public static UpdateCheckResult updateExists()
    {
        String version_now = main.getInstance().getDescription().getVersion();
        if(version_now.contains("SNAPSHOT"))
        {
            return UpdateCheckResult.SNAPSHOT;
        }
        else if(version_now.contains("RELEASE"))
        {
            String str = getFromUrl("https://api.github.com/repos/jingwenMC/MCDNDC/releases/latest");
            if(str==null)return UpdateCheckResult.UNEXPECTED;
            JSONObject object = JSON.parseObject(str);
            String rawVersion = object.getString("tag_name");
            String version = rawVersion.substring(1);
            String[] split_ver = version.split("-");
            String[] split_now_ver = version_now.split("-");
            ver_new = split_ver[0];
            ver_now = split_now_ver[0];
            if(ver_new.equalsIgnoreCase(ver_now))
            {
                return UpdateCheckResult.LATEST_RELEASE;
            }
            else return UpdateCheckResult.NEW_RELEASE;
        }
        return UpdateCheckResult.UNEXPECTED;
    }
    private static String getFromUrl(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(20000);
            InputStream stream = connection.getInputStream();
            return streamToString(stream);
        } catch (MalformedURLException e) {
            ExceptionUtil.print(e);
        } catch (IOException e) {
            ExceptionUtil.print(e);
        }
        return null;
    }
    private static String streamToString(InputStream source) {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        try {
            while ((length = source.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    /*
    @EventHandler
    public void onNewGame(NewGameEvent event)
    {
        checkUpdateAsync();
    }
    */
}

/* This file's some part is under the following license:
 *
 * Copyright (c) 2020 Spazzinq
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
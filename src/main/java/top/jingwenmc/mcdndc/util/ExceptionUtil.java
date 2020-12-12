package top.jingwenmc.mcdndc.util;

import top.jingwenmc.mcdndc.Main;

import java.io.*;
import java.util.Date;
import java.util.logging.Level;

public class ExceptionUtil {
    public static void print(Throwable e) {
        File eFile = new File(Main.getInstance().getDataFolder(), "errors.txt");
        try (FileWriter out = new FileWriter(eFile,true);PrintWriter writer = new PrintWriter(out)) {
            if (!eFile.exists()) if(!eFile.createNewFile()) {
                throw new IllegalStateException("Cannot Create File");
            }
            if (!eFile.canWrite()) throw new IOException("errors.txt is not writable");
            writer.print(("[MCDNDC Error] " + new Date(System.currentTimeMillis())));
            writer.print(System.lineSeparator());
            writer.print("Error Details:");
            writer.print(System.lineSeparator());
            e.printStackTrace(writer);
            writer.print(System.lineSeparator());
            Main.getInstance().getLogger().log(Level.SEVERE, "一个错误已经发生,已将该错误信息保存到插件目录下的 errors.txt");
            Main.getInstance().getLogger().log(Level.SEVERE, "An error has occurred and the error message has been saved to errors.txt in the plugin directory");
        } catch (IOException | IllegalStateException exception) {
            System.err.println("MCDNDC UNEXPECTED ERROR:Cannot Create/Write errors.txt While Recording An Error");
            System.err.println("IF ITS ALL RIGHT, PLEASE REPORT TO DEV.");
            System.err.println("Message:"+exception.getMessage());
        }
    }
}

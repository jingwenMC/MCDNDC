package top.jingwenmc.mcdndc.util;

import top.jingwenmc.mcdndc.Main;

import java.io.*;
import java.util.Date;
import java.util.logging.Level;

public class ExceptionUtil {
    public static void print(Throwable e) {
        File eFile = new File(Main.getInstance().getDataFolder(), "errors.txt");
        try {
            if (!eFile.exists()) eFile.createNewFile();
            if (!eFile.canWrite()) throw new IOException("errors.txt is not writable");
            FileWriter out = new FileWriter(eFile,true);
            PrintWriter writer = new PrintWriter(out);
            writer.print(("[MCDNDC Error] " + new Date(System.currentTimeMillis())));
            writer.print(System.lineSeparator());
            writer.print("Error Details:");
            writer.print(System.lineSeparator());
            e.printStackTrace(writer);
            writer.print(System.lineSeparator());
            out.close();
            Main.getInstance().getLogger().log(Level.SEVERE, "一个错误已经发生,已将该错误信息保存到插件目录下的 errors.txt");
            Main.getInstance().getLogger().log(Level.SEVERE, "An error has occurred and the error message has been saved to errors.txt in the plugin directory");
        } catch (IOException ioException) {
            System.out.println("MCDNDC UNEXPECTED ERROR:Cannot Create/Write errors.txt While Recording An Error");
            System.out.println("Printing Creating/Writing StackTrace...");
            ioException.printStackTrace();
            System.out.println("Printing StackTrace Directly...");
            e.printStackTrace();
        }
    }
}

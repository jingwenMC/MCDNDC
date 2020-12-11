package top.jingwenmc.mcdndc.objects;

import org.bukkit.configuration.ConfigurationSection;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.util.ConfigUtil;

public abstract class MCDNDCExtension {
    private String author;
    private String extensionName;
    private String sectionName;

    public MCDNDCExtension(String extensionName,String author)
    {
        this.extensionName = extensionName;
        this.author = author;
        sectionName = "extension."+extensionName;
        if(!Main.config.getConfig().contains(sectionName)) {
            Main.config.getConfig().createSection(sectionName);
            Main.config.saveConfig();
        }
        if(!getConfigSection().isSet("enable")) {
            getConfigSection().set("enable",true);
            Main.config.saveConfig();
        }
    }

    public void onEnable() {}

    public void onLoad() {}

    public void onDisable() {}

    public String getAuthor() {
        return author;
    }
    public String getExtensionName() {
        return extensionName;
    }
    public boolean isEnabled() {
        return getConfigSection().getBoolean("enable");
    }
    public void setEnabled(boolean enabled)
    {
        getConfigSection().set("enable",enabled);
        Main.config.saveConfig();
    }

    public final ConfigurationSection getConfigSection()
    {
        return ConfigUtil.getConfigurationSection(sectionName);
    }

    public final void saveConfig() {
        Main.config.saveConfig();
    }
}

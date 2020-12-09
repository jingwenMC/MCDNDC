package top.jingwenmc.mcdndc.objects;

import org.bukkit.configuration.ConfigurationSection;
import top.jingwenmc.mcdndc.Main;

public abstract class MCDNDCExtension {
    private ConfigurationSection section;
    private String author;
    private String extensionName;

    public MCDNDCExtension(String extensionName,String author)
    {
        this.extensionName = extensionName;
        this.author = author;
        String sectionName = "extension."+extensionName;
        if(Main.config.getConfig().isSet(sectionName))
        section = Main.config.getConfig().getConfigurationSection(sectionName);
        else section = Main.config.getConfig().createSection(sectionName);

        assert section != null;
        if(!section.isSet("enable"))section.set("enable",true);
        Main.config.saveConfig();
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
        return section.getBoolean("enable");
    }
    public void setEnabled(boolean enabled)
    {
        section.set("enable",enabled);
        Main.config.saveConfig();
    }

    public final ConfigurationSection getConfigSection()
    {
        return section;
    }
}

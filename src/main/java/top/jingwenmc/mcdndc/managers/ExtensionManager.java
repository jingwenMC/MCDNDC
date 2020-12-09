package top.jingwenmc.mcdndc.managers;

import top.jingwenmc.mcdndc.objects.MCDNDCExtension;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.HashMap;
import java.util.Map;

public class ExtensionManager {
    private final Map<String , MCDNDCExtension> EXTENSIONS = new HashMap<>();

    public void registerNewExtension(MCDNDCExtension extension) throws IllegalArgumentException
    {
        if(extension==null)throw new IllegalArgumentException("501 Extension Cannot Be Null");
        if(EXTENSIONS.containsKey(extension.getExtensionName()))throw new IllegalArgumentException("500 Extension Already Exists");
        EXTENSIONS.put(extension.getExtensionName(),extension);
        MessageUtil.sendConsole(true,"extension.on_register",new String[]{"%name"},extension.getExtensionName());
        extension.onLoad();
        if(extension.isEnabled())extension.onEnable();
    }

    public boolean enableExtension(String extensionName) throws IllegalArgumentException
    {
        if(extensionName == null || (!EXTENSIONS.containsKey(extensionName)))
            throw new IllegalArgumentException("404 Extension Not Found");
        MCDNDCExtension extension = EXTENSIONS.get(extensionName);
        if(!extension.isEnabled()) {
            extension.setEnabled(true);
            extension.onEnable();
            return true;
        }
        return false;
    }
    public boolean disableExtension(String extensionName) throws IllegalArgumentException
    {
        if(extensionName == null || (!EXTENSIONS.containsKey(extensionName)))
            throw new IllegalArgumentException("404 Extension Not Found");
        MCDNDCExtension extension = EXTENSIONS.get(extensionName);
        if(extension.isEnabled()) {
            extension.setEnabled(false);
            extension.onDisable();
            return true;
        }
        return false;
    }

    public MCDNDCExtension getExtension(String extensionName) throws IllegalArgumentException
    {
        if(extensionName == null || (!EXTENSIONS.containsKey(extensionName)))
            throw new IllegalArgumentException("404 Extension Not Found");
        return EXTENSIONS.get(extensionName);
    }

    public MCDNDCExtension[] getExtensionsArray()
    {
        return EXTENSIONS.values().toArray(new MCDNDCExtension[0]);
    }
    //TODO
}

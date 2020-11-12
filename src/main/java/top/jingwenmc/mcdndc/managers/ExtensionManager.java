package top.jingwenmc.mcdndc.managers;

import top.jingwenmc.mcdndc.objects.MCDNDCExtension;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.HashMap;
import java.util.Map;

public class ExtensionManager {
    private final Map<String , MCDNDCExtension> EXTENSIONS = new HashMap<>();

    public void registerNewExtension(MCDNDCExtension extension)
    {
        if(extension==null)throw new IllegalArgumentException("Extension Cannot Be Null");
        if(EXTENSIONS.containsKey(extension.getExtensionName()))throw new IllegalArgumentException("Exension Already Exists");
        EXTENSIONS.put(extension.getExtensionName(),extension);
        MessageUtil.sendConsole("extension.on_register",new String[]{"%name"},extension.getExtensionName());
    }

    public boolean enableExtension(String extensionName)
    {
        if(extensionName == null || (!EXTENSIONS.containsKey(extensionName)))
            throw new IllegalArgumentException("Extension Not Found");
        MCDNDCExtension extension = EXTENSIONS.get(extensionName);
        if(!extension.isEnabled()) {
            extension.setEnabled(true);
            return true;
        }
        return false;
    }
    public boolean disableExtension(String extensionName)
    {
        if(extensionName == null || (!EXTENSIONS.containsKey(extensionName)))
            throw new IllegalArgumentException("Extension Not Found");
        MCDNDCExtension extension = EXTENSIONS.get(extensionName);
        if(extension.isEnabled()) {
            extension.setEnabled(false);
            return true;
        }
        return false;
    }

    public MCDNDCExtension getExtension(String extensionName)
    {
        if(extensionName == null || (!EXTENSIONS.containsKey(extensionName)))
            throw new IllegalArgumentException("Extension Not Found");
        return EXTENSIONS.get(extensionName);
    }

    public MCDNDCExtension[] getExtensionsArray()
    {
        return EXTENSIONS.values().toArray(new MCDNDCExtension[0]);
    }
    //TODO
}

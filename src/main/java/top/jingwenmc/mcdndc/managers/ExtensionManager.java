package top.jingwenmc.mcdndc.managers;

import top.jingwenmc.mcdndc.objects.MCDNDCExtension;

import java.util.HashMap;
import java.util.Map;

public class ExtensionManager {
    private final Map<String , MCDNDCExtension> EXTENSIONS = new HashMap<>();
    public void registerNewExtension(MCDNDCExtension extension)
    {
        if(extension==null)throw new IllegalArgumentException("Extension Cannot Be Null");
        if(EXTENSIONS.containsKey(extension.getExtensionName()))throw new IllegalArgumentException("Provider Already Exists");
        EXTENSIONS.put(extension.getExtensionName(),extension);
    }
    //TODO
}

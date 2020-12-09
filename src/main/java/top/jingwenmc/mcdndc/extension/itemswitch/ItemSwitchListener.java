package top.jingwenmc.mcdndc.extension.itemswitch;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import top.jingwenmc.mcdndc.Main;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ItemSwitchListener implements Listener {
    static ConfigurationSection section;
    public void init() {
        this.section = Main.getInstance().getExtensionManager().getExtension("internal_itemswitch").getConfigSection();
        if(!section.contains("enabled"))
        section.set("enabled",true);
        if(!section.contains("command"))
        section.set("command","/getitem");
        if(!section.contains("command_perm"))
        section.set("command_perm","none");
        Main.config.saveConfig();
        this.section = Main.getInstance().getExtensionManager().getExtension("internal_itemswitch").getConfigSection();
    }
    static ItemStack itemStack = new ItemStack(Material.PAPER);
    static ItemMeta itemMeta = itemStack.getItemMeta();
    static Set<String> set = new HashSet<>();
    private void setupItem()
    {
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&',MessageUtil.getMessage("item_switch.title")));
        itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&',MessageUtil.getMessage("item_switch.lore"))));
        itemStack.setItemMeta(itemMeta);
    }
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event)
    {
        if(!Main.getInstance().getExtensionManager().getExtension("internal_itemswitch").isEnabled())return;
        if(event.getMessage().equalsIgnoreCase(section.getString("command")))
        {
            if(section.getBoolean("enabled"))
            {
                event.setCancelled(true);
                if(event.getPlayer().hasPermission(section.getString("command_perm"))
                        || section.getString("command_perm").equalsIgnoreCase("none"))
                {
                    setupItem();
                    event.getPlayer().getInventory().addItem(itemStack);
                    MessageUtil.sendPlayer(event.getPlayer(),"item_switch.gave");
                }
                else
                {
                    MessageUtil.sendPlayer(event.getPlayer(),"server.no_perm");
                    return;
                }
            }
        }
    }
    @EventHandler
    public void onClick(PlayerInteractEvent event)
    {
        if(!Main.getInstance().getExtensionManager().getExtension("internal_itemswitch").isEnabled())return;
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && section.getBoolean("enabled"))
        {
            setupItem();
            if(event.getItem() == null)return;
            if (event.getItem().isSimilar(itemStack)) {
                MessageUtil.sendPlayer(event.getPlayer(),"item_switch.used");
                String name = event.getPlayer().getName();
                if(!set.contains(name)) {
                    Bukkit.dispatchCommand(event.getPlayer(), "dndc next");
                    set.add(name);
                    new BukkitRunnable()
                    {
                        @Override
                        public void run() {
                            set.remove(name);
                        }
                    }.runTaskLaterAsynchronously(Main.getInstance(),30);
                }
            }
        }
    }
}

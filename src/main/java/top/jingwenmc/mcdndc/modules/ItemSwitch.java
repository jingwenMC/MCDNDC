package top.jingwenmc.mcdndc.modules;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import top.jingwenmc.mcdndc.events.MCDNDCEvent;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.Arrays;

public class ItemSwitch implements Listener {
    static ItemStack itemStack = new ItemStack(Material.PAPER);
    static ItemMeta itemMeta = itemStack.getItemMeta();
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event)
    {
        if(event.getMessage().equalsIgnoreCase(main.getInstance().getConfigAccessor().getConfig().getString("modules.item_switch.command")))
        {
            if(main.getInstance().getConfigAccessor().getConfig().getBoolean("modules.item_switch.enabled"))
            {
                event.setCancelled(true);
                if(event.getPlayer().hasPermission(main.getInstance().getConfigAccessor().getConfig().getString("modules.item_switch.command_perm")))
                {
                    itemMeta.setDisplayName(ChatColor.AQUA+"切换词语");
                    itemMeta.setLore(Arrays.asList(ChatColor.AQUA+"右键点击以切换词语"));
                    itemStack.setItemMeta(itemMeta);
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
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && main.getInstance().getConfigAccessor().getConfig().getBoolean("modules.item_switch.enabled"))
        {
            itemMeta.setDisplayName(ChatColor.AQUA+"切换词语");
            itemMeta.setLore(Arrays.asList(ChatColor.AQUA+"右键点击以切换词语"));
            itemStack.setItemMeta(itemMeta);
            if(event.getItem() == null)return;
            if (event.getItem().equals(itemStack)) {
                MessageUtil.sendPlayer(event.getPlayer(),"item_switch.used");
                Bukkit.dispatchCommand(event.getPlayer(), "dndc next");
            }
        }
    }
}

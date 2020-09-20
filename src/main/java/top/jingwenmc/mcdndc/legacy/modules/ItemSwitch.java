package top.jingwenmc.mcdndc.legacy.modules;

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
import org.bukkit.scheduler.BukkitRunnable;
import top.jingwenmc.mcdndc.main;
import top.jingwenmc.mcdndc.legacy.util.MessageUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ItemSwitch implements Listener {
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
        if(event.getMessage().equalsIgnoreCase(main.getInstance().getConfigAccessor().getConfig().getString("modules.item_switch.command")))
        {
            if(main.getInstance().getConfigAccessor().getConfig().getBoolean("modules.item_switch.enabled"))
            {
                event.setCancelled(true);
                if(event.getPlayer().hasPermission(main.getInstance().getConfigAccessor().getConfig().getString("modules.item_switch.command_perm"))
                || main.getInstance().getConfigAccessor().getConfig().getString("modules.item_switch.command_perm").equalsIgnoreCase("none"))
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
        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && main.getInstance().getConfigAccessor().getConfig().getBoolean("modules.item_switch.enabled"))
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
                    }.runTaskLaterAsynchronously(main.getInstance(),30);
                }
            }
        }
    }
}
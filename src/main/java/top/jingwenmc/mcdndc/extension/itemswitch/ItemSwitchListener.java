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
import top.jingwenmc.mcdndc.objects.MCDNDCExtension;
import top.jingwenmc.mcdndc.util.MessageUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class ItemSwitchListener implements Listener {
    private MCDNDCExtension getExtension() {
        return Main.getInstance().getExtensionManager().getExtension("internal_itemswitch");
    }

    private ConfigurationSection getSection() {
        return Main.getInstance().getExtensionManager().getExtension("internal_itemswitch").getConfigSection();
    }

    public void init() {
        if (!getSection().isSet("command"))
            getSection().set("command", "/getitem");
        if (!getSection().isSet("command_perm"))
            getSection().set("command_perm", "none");
        getExtension().saveConfig();
    }

    static ItemStack itemStack = new ItemStack(Material.PAPER);
    static ItemMeta itemMeta = itemStack.getItemMeta();
    static Set<String> set = new HashSet<>();

    private void setupItem() {
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', MessageUtil.getMessage("item_switch.title")));
        itemMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', MessageUtil.getMessage("item_switch.lore"))));
        itemStack.setItemMeta(itemMeta);
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        if (!getExtension().isEnabled()) return;
        if (event.getMessage().equalsIgnoreCase(getSection().getString("command"))) {
            event.setCancelled(true);
            if (event.getPlayer().hasPermission(getSection().getString("command_perm"))
                    || getSection().getString("command_perm").equalsIgnoreCase("none")) {
                setupItem();
                event.getPlayer().getInventory().addItem(itemStack);
                MessageUtil.sendPlayer(event.getPlayer(), "item_switch.gave");
            } else {
                MessageUtil.sendPlayer(event.getPlayer(), "server.no_perm");
                return;
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (!getExtension().isEnabled()) return;
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            setupItem();
            if (event.getItem() == null) return;
            if (event.getItem().isSimilar(itemStack)) {
                String name = event.getPlayer().getName();
                if (!set.contains(name)) {
                    Bukkit.dispatchCommand(event.getPlayer(), "dndc next");
                    MessageUtil.sendPlayer(event.getPlayer(), "item_switch.used");
                    set.add(name);
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            set.remove(name);
                        }
                    }.runTaskLaterAsynchronously(Main.getInstance(), 10);
                }
            }
        }
    }
}

package top.jingwenmc.mcdndc.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import top.jingwenmc.mcdndc.commands.mcdndc;
import top.jingwenmc.mcdndc.main;

import java.util.*;

public class GuiUtil implements Listener {
    static Random r = new Random(System.currentTimeMillis());
    private static Material randomMaterial()
    {
        Material[] materials =
                {
                        Material.WHITE_WOOL,
                        Material.RED_WOOL,
                        Material.LIGHT_BLUE_WOOL,
                        Material.LIME_WOOL,
                        Material.ORANGE_WOOL
                };
        return materials[r.nextInt(materials.length)];
    }
    public static void showWordsGui(Player player,int page)
    {
        Inventory gui = Bukkit.createInventory(player,54, MessageUtil.getMessage("gui.title"));
        ItemStack itemStack = new ItemStack(randomMaterial());
        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> wordList = main.getInstance().getConfigAccessor().getConfig().getStringList("words");
        int maxPage = (wordList.size()+44)/45;
        boolean hasNext = true;
        boolean hasPrev = true;
        if(page<=1) {
            hasPrev = false;
            page = 1;
        }
        if(page >= maxPage) {
            hasNext = false;
            page = maxPage;
        }
        int x = 0;
        for(int i = (page-1)*45;i<wordList.size() && i<page*45;i++)
        {
            itemStack.setType(randomMaterial());
            itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(ChatColor.AQUA+wordList.get(i));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(MessageUtil.getMessage("gui.left"));
            lore.add(MessageUtil.getMessage("gui.shift_right"));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            gui.setItem(x,itemStack);
            x++;
        }
        if(hasPrev)
        {
            itemStack.setType(Material.ARROW);
            itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(MessageUtil.getMessage("gui.page_prev"));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(MessageUtil.getMessage("gui.page_now"));
            lore.add(String.valueOf(page));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            gui.setItem(45,itemStack);
        }
        if(hasNext)
        {
            itemStack.setType(Material.ARROW);
            itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(MessageUtil.getMessage("gui.page_next"));
            ArrayList<String> lore = new ArrayList<>();
            lore.add(MessageUtil.getMessage("gui.page_now"));
            lore.add(String.valueOf(page));
            itemMeta.setLore(lore);
            itemStack.setItemMeta(itemMeta);
            gui.setItem(53,itemStack);
        }
        itemStack.setType(Material.FLOWER_POT);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(MessageUtil.getMessage("gui.reload_editor"));
        ArrayList<String> lore = new ArrayList<>();
        lore.add(MessageUtil.getMessage("gui.reload_editor_l1"));
        lore.add(MessageUtil.getMessage("gui.reload_editor_l2"));
        lore.add(MessageUtil.getMessage("gui.reload_editor_l3"));
        lore.add(MessageUtil.getMessage("gui.reload_editor_l4"));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        gui.setItem(50,itemStack);
        player.openInventory(gui);
        itemStack.setType(Material.EMERALD_BLOCK);
        itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(MessageUtil.getMessage("gui.new_word"));
        lore = new ArrayList<>();
        lore.add(MessageUtil.getMessage("gui.new_word_l1"));
        lore.add(MessageUtil.getMessage("gui.new_word_l2"));
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        gui.setItem(48,itemStack);
        player.openInventory(gui);
    }
    public static Map<String , Integer> map = new HashMap<>();
    @EventHandler
    public void onGuiClick(InventoryClickEvent event)
    {
        if(event.getView().getTitle().equalsIgnoreCase(MessageUtil.getMessage("gui.title")))
        {
            event.setCancelled(true);
            //检测到主GUI
            if(event.getClick().equals(ClickType.LEFT))
            {
                if(event.getCurrentItem()==null)return;
                if(event.getCurrentItem().getType().equals(Material.EMERALD_BLOCK))
                {
                    List<String> list = main.getInstance().getConfigAccessor().getConfig().getStringList("words");
                    int index = list.size();
                    map.put(event.getWhoClicked().getName(),index);
                    event.getWhoClicked().sendMessage(MessageUtil.getMessage("gui.new_l1"));
                    event.getWhoClicked().sendMessage(MessageUtil.getMessage("gui.new_l2"));
                    event.getWhoClicked().sendMessage(MessageUtil.getMessage("gui.new_l3"));
                    event.getWhoClicked().closeInventory();
                    return;
                }
                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessageUtil.getMessage("gui.page_next")))
                {
                    showWordsGui((Player) event.getWhoClicked(),Integer.valueOf(event.getCurrentItem().getItemMeta().getLore().get(1))+1);
                    return;
                }
                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessageUtil.getMessage("gui.page_prev")))
                {
                    showWordsGui((Player) event.getWhoClicked(),Integer.valueOf(event.getCurrentItem().getItemMeta().getLore().get(1))-1);
                    return;
                }
                if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(MessageUtil.getMessage("gui.reload_editor")))
                {
                    if(!(event.getWhoClicked().hasPermission("dndc.reload")&&event.getWhoClicked().hasPermission("dndc.restart")))
                    {
                        MessageUtil.sendPlayer(event.getWhoClicked(),"server.no_perm");
                        return;
                    }
                    //else
                    event.getWhoClicked().closeInventory();
                    mcdndc.reloadPluginConf();
                    showWordsGui((Player) event.getWhoClicked(),1);
                    return;
                }
                String item_name = event.getCurrentItem().getItemMeta().getDisplayName().replaceFirst(ChatColor.AQUA.toString(),"");
                List<String> list = main.getInstance().getConfigAccessor().getConfig().getStringList("words");
                int index = list.indexOf(item_name);
                map.put(event.getWhoClicked().getName(),index);
                event.getWhoClicked().sendMessage(MessageUtil.getMessage("gui.edit_l1")
                        .replaceAll("%index", String.valueOf(index+1))
                        .replaceAll("%item_name",item_name));
                event.getWhoClicked().sendMessage(MessageUtil.getMessage("gui.edit_l2")
                        .replaceAll("%index", String.valueOf(index+1))
                        .replaceAll("%item_name",item_name));
                event.getWhoClicked().sendMessage(MessageUtil.getMessage("gui.edit_l3")
                        .replaceAll("%index", String.valueOf(index+1))
                        .replaceAll("%item_name",item_name));
                event.getWhoClicked().closeInventory();
            }
            if(event.getClick().equals(ClickType.SHIFT_RIGHT))
            {
                if(event.getCurrentItem()==null)return;
                if(event.getSlot() >= 45)return;
                String item_name = event.getCurrentItem().getItemMeta().getDisplayName().replaceFirst(ChatColor.AQUA.toString(),"");
                List<String> list = main.getInstance().getConfigAccessor().getConfig().getStringList("words");
                int index = list.indexOf(item_name);
                list.remove(index);
                main.getInstance().getConfigAccessor().getConfig().set("words",list);
                main.getInstance().getConfigAccessor().saveConfig();
                //event.getWhoClicked().sendMessage(ChatColor.GREEN + "删除第"+index+"个词条("+item_name+")成功.");
                event.getWhoClicked().sendMessage(MessageUtil.getMessage("gui.deleted")
                        .replaceAll("%index", String.valueOf(index+1))
                        .replaceAll("%item_name",item_name));
                new BukkitRunnable()
                {
                    @Override
                    public void run() {
                        event.getWhoClicked().closeInventory();
                    }
                }.runTaskLater(main.getInstance(),1);
            }
        }
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (map.containsKey(event.getPlayer().getName()))
        {
            event.setCancelled(true);
            //sync
            new BukkitRunnable() {
                @Override
                public void run() {
                    if(event.getMessage()==null)
                    {
                        event.getPlayer().sendMessage(MessageUtil.getMessage("gui.edit_cancel"));
                        event.getPlayer().sendMessage(MessageUtil.getMessage("gui.edit_not_null"));
                        map.remove(event.getPlayer().getName());
                        showWordsGui(event.getPlayer(),1);
                        return;
                    }
                    if (event.getMessage().equalsIgnoreCase("cancel")) {
                        event.getPlayer().sendMessage(MessageUtil.getMessage("gui.edit_cancel"));
                        map.remove(event.getPlayer().getName());
                        showWordsGui(event.getPlayer(),1);
                        return;
                    }
                    List<String> list = main.getInstance().getConfigAccessor().getConfig().getStringList("words");
                    if(map.get(event.getPlayer().getName())>=list.size())list.add(event.getMessage());
                    else
                    list.set(map.get(event.getPlayer().getName()), event.getMessage());
                    main.getInstance().getConfigAccessor().getConfig().set("words",list);
                    main.getInstance().getConfigAccessor().saveConfig();
                    event.getPlayer().sendMessage(MessageUtil.getMessage("gui.edit_success"));
                    map.remove(event.getPlayer().getName());
                    showWordsGui(event.getPlayer(),1);
                }
            }.runTask(main.getInstance());
        }
    }
}

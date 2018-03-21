package de.lois.plugin.listener;


import de.lois.plugin.main.BackpackConfig;
import de.lois.plugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;


public class BackpackListener implements Listener {

    private Main plugin;

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onOpenBackpack(PlayerInteractEvent e){

        Player p = e.getPlayer();


        if((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && p.getItemInHand().getType() == Material.CHEST){

            e.setCancelled(true);

            ItemStack i = p.getItemInHand();
            ItemMeta itemMeta = i.getItemMeta();
            String s = itemMeta.getDisplayName();

            if(s.equals("Rucksack")){
                e.getPlayer().openInventory(BackpackConfig.getBackpack(e.getPlayer().getUniqueId().toString()));
            }

        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        for(Player p : Bukkit.getOnlinePlayers()){
            boolean hasBackpack = false;
            for(int i = 0; i<p.getInventory().getSize(); i++){
                ItemStack itm = p.getInventory().getItem(i);
                if((itm != null && itm.getItemMeta().getDisplayName().equalsIgnoreCase("Rucksack"))){
                    hasBackpack = true;
                }
            }
            if(!hasBackpack)
                p.getWorld().dropItem(p.getLocation(), createBackpack());

        }
    }

    @EventHandler
    public void onItemPickup(PlayerPickupItemEvent e) {

        if (e.getItem().getItemStack().equals(createBackpack())) {
            if(e.getPlayer().getInventory().contains(createBackpack())){
                e.setCancelled(true);
            }
        }

    }


    private ItemStack createBackpack() {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Rucksack");
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add("Rechtsklick um Rucksack zu öffnen!");
        meta.setLore(Lore);
        item.setItemMeta(meta);
        return item;

    }

}

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


        if((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) && p.getItemInHand().getType() == Material.CHEST &&
                p.getItemInHand().getItemMeta().getDisplayName().equals("Hochschulrucksack")){

            e.setCancelled(true);

            ItemStack i = p.getItemInHand();
            ItemMeta itemMeta = i.getItemMeta();
            String s = itemMeta.getDisplayName();
            e.getPlayer().openInventory(BackpackConfig.getBackpack(itemMeta.getLore().get(0)));


        }

    }


}

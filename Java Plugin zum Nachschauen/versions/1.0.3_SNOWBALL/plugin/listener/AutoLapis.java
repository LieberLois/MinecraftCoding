package de.lois.plugin.listener;


import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

@SuppressWarnings("deprecation")
public class AutoLapis implements Listener{


    @EventHandler
    public void openInv(InventoryOpenEvent e){
        if(e.getInventory() instanceof EnchantingInventory){


            ((EnchantingInventory) e.getInventory()).setItem(1, new ItemStack(351, 3, (short) 0, (byte) 4));

        }
    }

    @EventHandler
    public void closeInv(InventoryCloseEvent e){
        if(e.getInventory() instanceof EnchantingInventory){


            ((EnchantingInventory) e.getInventory()).setItem(1, null);

        }
    }

    @EventHandler
    public void clickInv(InventoryClickEvent e){
        if(e.getInventory() instanceof EnchantingInventory && e.getSlot() == 1){


            e.setCancelled(true);

        }
    }
}

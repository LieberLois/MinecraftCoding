kommenpackage de.lois.plugin.listener;


import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Dye;

@SuppressWarnings("deprecation")
public class AutoFill implements Listener{


    @EventHandler
    public void openInvEnchant(InventoryOpenEvent e){
        if (e.getPlayer().getWorld().getName().equals("world")) {
            if(e.getInventory() instanceof EnchantingInventory){
                ((EnchantingInventory) e.getInventory()).setItem(1, new ItemStack(351, 3, (short) 0, (byte) 4));
            }
        }
    }

    @EventHandler
    public void closeInvEnchant(InventoryCloseEvent e){
        if (e.getPlayer().getWorld().getName().equals("world")) {
            if (e.getInventory() instanceof EnchantingInventory) {
                ((EnchantingInventory) e.getInventory()).setItem(1, null);
            }
        }
    }

    @EventHandler
    public void clickInvEnchant(InventoryClickEvent e){
        if (e.getWhoClicked().getWorld().getName().equals("world")) {
            if (e.getInventory() instanceof EnchantingInventory && e.getSlot() == 1) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void openInvOfen(InventoryOpenEvent e){
        if (e.getPlayer().getWorld().getName().equals("world")) {
            if (e.getInventory() instanceof FurnaceInventory) {
                ((FurnaceInventory) e.getInventory()).setItem(1, new ItemStack(263, 64));
            }
        }
    }


    @EventHandler
    public void clickInvOfen(InventoryClickEvent e){
        if (e.getWhoClicked().getWorld().getName().equals("world")) {
            if (e.getInventory() instanceof FurnaceInventory && e.getSlot() == 1) {
                e.setCancelled(true);
            }
        }
    }


}

package de.lois.plugin.listener;

import de.lois.plugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.entity.TippedArrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;


import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Random;




@SuppressWarnings("deprecation")
public class ChestsListener implements Listener {
    private Main plugin;

    public ItemStack createArrow(PotionType type){
        ItemStack tippedArrow = new ItemStack(Material.TIPPED_ARROW, 4);
        PotionMeta meta = (PotionMeta) tippedArrow.getItemMeta();
        meta.setBasePotionData(new PotionData(type));
        tippedArrow.setItemMeta(meta);
        return tippedArrow;
    }

    public ChestsListener(){
        this.plugin = plugin;
    }



    public HashMap<Location, Inventory> chests = new HashMap<>();


    public void refillChests(){
        chests.clear();
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        Player p = (Player)e.getPlayer();


        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock().getType() == Material.BOOKSHELF){
                e.setCancelled(true);
                Location loc = e.getClickedBlock().getLocation();

                if(chests.containsKey(loc)){
                    p.openInventory(chests.get(loc));
                }else{
                    Random r = new Random();
                    int l = r.nextInt(6);

                    if(l == 0){
                        l++;
                    }

                    String string = "Wegggeschniaaappt von " + e.getPlayer().getDisplayName();

                    Inventory inv = Bukkit.createInventory(null, InventoryType.CHEST, string);
                    List<ItemStack> items = new ArrayList<>();



                    //COMMON

                    for(int i = 0; i < 6; i++){


                        items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
                        items.add(new ItemStack(Material.LEATHER_BOOTS, 1));
                        items.add(new ItemStack(Material.LEATHER_HELMET, 1));
                        items.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));

                        items.add(new ItemStack(Material.RAW_BEEF ,2));
                        items.add(new ItemStack(Material.RAW_CHICKEN, 3));

                        items.add(new ItemStack(Material.WOOD_SWORD));
                        items.add(new ItemStack(Material.WOOD_SWORD));
                        items.add(new ItemStack(Material.WOOD_SWORD));
                        items.add(new ItemStack(Material.STONE_SWORD, 1));

                        items.add(new ItemStack(Material.STICK, 1));




                    }


                    //MEDIUM

                    for(int i= 0; i < 2; i++){
                        items.add(new ItemStack(Material.ARROW,4));

                        items.add(new ItemStack(Material.STONE_AXE, 1));
                        items.add(new ItemStack(Material.GOLD_AXE, 1));
                        items.add(new ItemStack(Material.GOLD_SWORD, 1));
                        items.add(new ItemStack(Material.BOW));

                        items.add(new ItemStack(Material.GOLD_CHESTPLATE));
                        items.add(new ItemStack(Material.GOLD_LEGGINGS ,1));
                        items.add(new ItemStack(Material.GOLD_BOOTS ,1));
                        items.add(new ItemStack(Material.GOLD_HELMET ,1));

                        items.add(new ItemStack(Material.GOLD_INGOT,2));
                        items.add(new ItemStack(Material.GOLD_INGOT,4));
                        items.add(new ItemStack(Material.GOLD_INGOT,3));
                        items.add(new ItemStack(Material.GOLD_INGOT,2));
                        
                        items.add(new ItemStack(Material.IRON_INGOT,4));
                        items.add(new ItemStack(Material.IRON_INGOT,3));
                        items.add(new ItemStack(Material.IRON_INGOT,2));
                        items.add(new ItemStack(Material.IRON_INGOT,1));


                        items.add(new ItemStack(Material.GOLDEN_CARROT, 3));

                        items.add(new ItemStack(Material.SNOW_BALL, 4));
                        items.add(new ItemStack(Material.EGG, 4));

                        // ARROWS

                        items.add(this.createArrow(PotionType.POISON));
                        items.add(this.createArrow(PotionType.WEAKNESS));
                        items.add(this.createArrow(PotionType.SLOWNESS));
                    }

                    items.add(this.createArrow(PotionType.SLOWNESS));


                    // RARE

                    items.add(new ItemStack(Material.GOLDEN_APPLE, 2));
                    items.add(new ItemStack(Material.SNOW_BALL, 4));
                    items.add(new ItemStack(Material.EGG, 4));


                    items.add(new ItemStack(Material.DIAMOND, 1));
                    items.add(new ItemStack(Material.DIAMOND, 2));
                    items.add(new ItemStack(Material.DIAMOND, 3));


                    //items.add(new ItemStack(Material.IRON_CHESTPLATE));
                    items.add(new ItemStack(Material.IRON_LEGGINGS ,1));
                    items.add(new ItemStack(Material.IRON_BOOTS ,1));
                    items.add(new ItemStack(Material.IRON_HELMET ,1));
                    //items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
                    //items.add(new ItemStack(Material.CHAINMAIL_LEGGINGS ,1));
                    items.add(new ItemStack(Material.CHAINMAIL_BOOTS ,1));
                    items.add(new ItemStack(Material.CHAINMAIL_HELMET ,1));

                    //items.add(new ItemStack(Material.DIAMOND_AXE, 1));
                    //items.add(new ItemStack(Material.DIAMOND_SWORD, 1));
                    items.add(new ItemStack(Material.IRON_AXE, 1));
                    items.add(new ItemStack(Material.IRON_AXE, 1));
                    items.add(new ItemStack(Material.IRON_AXE, 1));

                    items.add(new ItemStack(Material.ENDER_PEARL, 2));
                    items.add(new ItemStack(Material.BOOK, 3));

                    items.add(new ItemStack((new Potion(PotionType.INSTANT_HEAL, 1, false).toItemStack(1))));
                    items.add(new ItemStack((new Potion(PotionType.REGEN, 1, false).toItemStack(1))));
                    items.add(new ItemStack((new Potion(PotionType.SPEED, 1, false).toItemStack(1))));
                    items.add(new ItemStack((new Potion(PotionType.INSTANT_DAMAGE, 1, true).toItemStack(1))));
                    items.add(new ItemStack((new Potion(PotionType.WEAKNESS, 1, true).toItemStack(1))));

                    items.add(new ItemStack(Material.EXP_BOTTLE, 5));
                    items.add(new ItemStack(Material.EXP_BOTTLE, 4));
                    items.add(new ItemStack(Material.EXP_BOTTLE, 3));
                    items.add(new ItemStack(Material.EXP_BOTTLE, 2));
                    items.add(new ItemStack(Material.EXP_BOTTLE, 2));

                    items.add(new ItemStack(Material.COOKED_BEEF,2));
                    items.add(new ItemStack(Material.COOKED_RABBIT,2));
                    items.add(new ItemStack(Material.BREAD,2));
                    items.add(new ItemStack(Material.APPLE, 3));

                    while(l != 0){

                        l--;
                        int slot = r.nextInt(27);
                        int whichItem = r.nextInt(items.size());

                        inv.setItem(slot, items.get(whichItem));

                    }

                    chests.put(loc, inv);
                    p.openInventory(chests.get(loc));
                }

        }


        }
    }

}

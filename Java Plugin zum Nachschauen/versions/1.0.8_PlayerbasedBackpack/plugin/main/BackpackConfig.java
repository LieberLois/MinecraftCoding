package de.lois.plugin.main;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BackpackConfig {

    public static Map<String, Inventory> backpacks = new HashMap<String, Inventory>();

    public static void saveBackpack(String uuid, Inventory inv){

        // File vorhanden?
        if(!(hasBackpack(uuid))){
            try {
                new File("plugins/LoisPlugin/backpacks/" + uuid + ".yml").createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        // Items speichern
        ItemStack[] contents = inv.getContents();
        List<ItemStack> itemStackList = new ArrayList<ItemStack>();

        for(ItemStack iso : contents){

            if (iso != null) {
                ItemStack is = new ItemStack(iso);
                ItemMeta im = is.getItemMeta();

                if(im.getDisplayName() != null){
                    im.setDisplayName(im.getDisplayName().replace("§", "&"));
                }

                List<String> lore = new ArrayList<String>();
                if(im.getLore() != null){
                    for(String s : im.getLore()){
                        lore.add(s.replace("§", "&"));
                    }
                    im.setLore(lore);
                }
                is.setItemMeta(im);
                itemStackList.add(is);
            } else {
                itemStackList.add(null);
            }


        }

        YamlConfiguration cfg = new YamlConfiguration();
        cfg.set("content", itemStackList);

        try {
            cfg.save(new File("plugins/LoisPlugin/backpacks/" + uuid + ".yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Inventory loadBackpack(String uuid){

        File f = new File("plugins/LoisPlugin/backpacks/" + uuid + ".yml");

        if(!(hasBackpack(uuid))){
            BackpackConfig.saveBackpack(uuid, Bukkit.createInventory(null, 27, "Backpack"));
        }


        Inventory inv = Bukkit.createInventory(null, 27, "Rucksack");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(f);

        ItemStack[] content = ((List<ItemStack>)cfg.get("content")).toArray(new ItemStack[0]);

        for(ItemStack iso : content){



            if (iso != null) {
                ItemStack is = new ItemStack(iso);
                ItemMeta im = is.getItemMeta();

                if(im.getDisplayName() != null){
                    im.setDisplayName(im.getDisplayName().replace("&", "§"));
                }

                List<String> lore = new ArrayList<String>();
                if(im.getLore() != null){
                    for(String s : im.getLore()){
                        lore.add(s.replace("&", "§"));
                    }
                    im.setLore(lore);
                }
                is.setItemMeta(im);
            }
        }

        inv.setContents(content);
        return inv;

    }

    public static Inventory getBackpack(String uuid){
        if(!backpacks.containsKey((uuid))){
            backpacks.put(uuid, loadBackpack(uuid));
        }

        return backpacks.get(uuid);
    }

    public static boolean hasBackpack(String uuid){
        File f = new File("plugins/LoisPlugin/backpacks/" + uuid + ".yml");

        return f.exists();
    }
}



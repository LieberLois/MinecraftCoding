package de.lois.plugin.commands;


import de.lois.plugin.main.BackpackConfig;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class CMDbackpack implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("backpack")) {


            if (!(sender instanceof Player)) {
                sender.sendMessage("Du bist kein Spieler du Hänger!");
                return true;
            }

            Player p = (Player) sender;


                p.getWorld().dropItem(p.getLocation(), createBackpack(((Player) sender).getUniqueId().toString()));


            return true;
        }
        return false;
    }

    private ItemStack createBackpack(String uuid) {
        ItemStack item = new ItemStack(Material.CHEST);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Hochschulrucksack");
        ArrayList<String> Lore = new ArrayList<String>();
        Lore.add(uuid);
        meta.setLore(Lore);
        item.setItemMeta(meta);

        return item;

    }

}

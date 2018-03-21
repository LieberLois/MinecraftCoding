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

            if (args.length == 0) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(ChatColor.RED + "Du bist kein Spieler du Hänger!");
                    return true;
                }

                Player p = (Player) sender;
                p.getWorld().dropItem(p.getLocation(), createBackpack(((Player) sender).getUniqueId().toString()));

                return true;
            } else if(args.length == 1){

                boolean online = false;
                Player p = args[1];

                for(Player player : Bukkit.getOnlinePlayers()){
                    if(player == p){
                        online = true;
                    }
                }

                if (online) {
                    sender.getPlayer().openInventory(BackpackConfig.getBackpack(p.getUniqueId().toString()));
                } else {
                    sender.sendMessage(ChatColor.RED + "Der Spieler ist nicht online!")
                }

                return true;
            }
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

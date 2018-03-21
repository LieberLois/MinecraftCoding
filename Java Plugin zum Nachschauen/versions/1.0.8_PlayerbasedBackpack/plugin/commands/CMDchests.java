package de.lois.plugin.commands;


import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**

 */
public class CMDchests implements CommandExecutor {

    private HashMap<Location, ItemStack[]> inv = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(command.getName().equalsIgnoreCase("chests")){


            if(sender instanceof Player){

                Player p = (Player)sender;
                World w = p.getWorld();

                if ((args.length == 1) && p.getGameMode() == GameMode.CREATIVE) {
                    if (args[0].equalsIgnoreCase("save")) {


                        inv.clear();

                            for(double z = -1876; z < -1634 ; z++){

                                for (double y = 0; y < 35; y++) {

                                    for (double x = -828; x < -586; x++) {
                                        Location loc = new Location(w, x, y, z);
                                        if(loc.getBlock().getType() == Material.CHEST){

                                            Chest chest = (Chest)loc.getBlock().getState();

                                            inv.put(loc, chest.getBlockInventory().getContents());
                                            chest.getBlockInventory().clear();
                                            chest.getBlockInventory().setContents(inv.get(loc));

                                        }
                                    }

                                }

                            }

                            int amount = inv.size();

                            p.sendMessage(ChatColor.GREEN + "** " + amount + " Kisten gespeichert! **");

                    } else if (args[0].equalsIgnoreCase("load") && inv.size() > 0) {

                        for(double z = -1876; z < -1634 ; z++){
                            for (double y = 0; y < 30; y++) {
                                for (double x = -828; x < -586; x++) {
                                    Location loc = new Location(w, x, y, z);
                                    if(loc.getBlock().getType() == Material.CHEST){
                                        Chest chest = (Chest)loc.getBlock().getState();
                                        chest.getBlockInventory().setContents(inv.get(loc));
                                    }
                                }
                            }
                        }
                        int amount = inv.size();

                        p.sendMessage(ChatColor.GREEN + "** " + amount + " Kisten geladen! **");

                    }else if (args[0].equalsIgnoreCase("fill")) {

                        de.lois.plugin.main.Main.getChestsListener().refillChests();

                        p.sendMessage(ChatColor.GREEN + "** Chests filled! **");

                    }else {
                        p.sendMessage(ChatColor.GREEN + "Please use " + ChatColor.RED + "/chests save|load|fill " + ChatColor.GREEN + "in CREATIVE only!");
                    }


                } else {
                    p.sendMessage(ChatColor.GREEN + "Please use " + ChatColor.RED + "/chests save|load|fill " + ChatColor.GREEN + "in CREATIVE only!");
                }
            } else if (args[0].equalsIgnoreCase("fill")) {
                de.lois.plugin.main.Main.getChestsListener().refillChests();
            }

            }


        return false;
    }

}

//
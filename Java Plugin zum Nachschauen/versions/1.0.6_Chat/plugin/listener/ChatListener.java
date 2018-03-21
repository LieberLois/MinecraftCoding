package de.lois.plugin.listener;


import de.lois.plugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Main plugin;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){

        Player p = e.getPlayer();
        String name = p.getDisplayName();
        ChatColor color = ChatColor.WHITE;

        switch(name){
            case "Lois_Bois":
                color = ChatColor.DARK_RED;
                break;
            case "Luke_skywalker99":
                color = ChatColor.DARK_GREEN;
                break;
            case "Waschlappen21":
                color = ChatColor.DARK_AQUA;
                break;
            case "Pfannenwender24":
                color = ChatColor.GOLD;
                break;
            case "mo158":
                color = ChatColor.DARK_PURPLE;
                break;
        }



            if(name.equalsIgnoreCase("Luke_skywalker99"))
                Bukkit.broadcastMessage(color + "[Der mit dem kleinen Penis]: "+ ChatColor.WHITE + e.getMessage());
            else{
                e.setCancelled(true);
                Bukkit.broadcastMessage(color + "[" + p.getDisplayName() + "]: "+ ChatColor.WHITE + e.getMessage());
            }


    }

}

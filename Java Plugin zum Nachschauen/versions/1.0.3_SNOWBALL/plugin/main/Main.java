package de.lois.plugin.main;

import de.lois.plugin.commands.CMDchests;
import de.lois.plugin.listener.AutoLapis;
import de.lois.plugin.listener.ChatListener;
import de.lois.plugin.listener.Snowball;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static java.awt.Color.GREEN;


public class Main extends JavaPlugin{

    private static Main plugin;

    public void onEnable() {

        plugin = this;


        this.getCommand("chests").setExecutor(new CMDchests());




        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new ChatListener(), this);
        pm.registerEvents(new AutoLapis(), this);
        pm.registerEvents(new Snowball(), this);


        System.out.println("[LoisPlugin] Plugin geladen!");



    }

    public void onDisable(){
        plugin = this;

        System.out.println("[LoisPlugin] Plugin gestoppt!");
    }

    public static Main getPlugin(){
        return plugin;
    }

}

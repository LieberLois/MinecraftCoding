package de.lois.plugin.main;

import de.lois.plugin.commands.CMDchests;
import de.lois.plugin.listener.AutoFill;
import de.lois.plugin.listener.ChatListener;
import de.lois.plugin.listener.ChestsListener;
import de.lois.plugin.listener.projectileHit;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static java.awt.Color.GREEN;


public class Main extends JavaPlugin{

    private static Main plugin;
    private static ChatListener chatListener = new ChatListener();
    private static AutoFill AutoFill = new AutoFill();
    private static projectileHit projectileHit = new projectileHit();
    private static ChestsListener chestsListener = new ChestsListener();

    public static ChestsListener getChestsListener(){
        return chestsListener;
    }

    public void onEnable() {

        plugin = this;


        this.getCommand("chests").setExecutor(new CMDchests());




        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(chatListener, this);
        pm.registerEvents(AutoFill, this);
        pm.registerEvents(projectileHit, this);
        pm.registerEvents(chestsListener, this);


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

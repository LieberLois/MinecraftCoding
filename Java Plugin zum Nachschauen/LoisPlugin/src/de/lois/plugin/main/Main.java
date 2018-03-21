kommenpackage de.lois.plugin.main;

import de.lois.plugin.commands.CMDbackpack;
import de.lois.plugin.commands.CMDchests;
import de.lois.plugin.listener.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.GREEN;


public class Main extends JavaPlugin{

    private static Main plugin;
    private static ChatListener chatListener = new ChatListener();
    private static AutoFill AutoFill = new AutoFill();
    private static projectileHit projectileHit = new projectileHit();
    private static ChestsListener chestsListener = new ChestsListener();
    private static BackpackListener backpackListener = new BackpackListener();

    public static ChestsListener getChestsListener(){
        return chestsListener;
    }

    public void onEnable() {
        plugin = this;

        File LoisPlugin = new File ("plugins/LoisPlugin");

        if(!(LoisPlugin.exists())){
            LoisPlugin.mkdir();
        }

        File backpacks = new File ("plugins/LoisPlugin/backpacks");

        if(!(backpacks.exists())){
            backpacks.mkdir();
        }

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
                for(String s : BackpackConfig.backpacks.keySet()){
                    BackpackConfig.saveBackpack(s, BackpackConfig.backpacks.get(s));
                }

                BackpackConfig.backpacks.clear();

                for(Player p : Bukkit.getOnlinePlayers()){
                    BackpackConfig.getBackpack(p.getUniqueId().toString());
                }
            }

        }, 20*60*15, 20*60*15);

        this.getCommand("chests").setExecutor(new CMDchests());
        this.getCommand("backpack").setExecutor(new CMDbackpack());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(chatListener, this);
        pm.registerEvents(AutoFill, this);
        pm.registerEvents(projectileHit, this);
        pm.registerEvents(chestsListener, this);
        pm.registerEvents(backpackListener, this);


        System.out.println("[LoisPlugin] Plugin geladen!");



    }

    public void onDisable(){
        plugin = this;

        for(String s : BackpackConfig.backpacks.keySet()){
            BackpackConfig.saveBackpack(s, BackpackConfig.backpacks.get(s));
        }

        System.out.println("[LoisPlugin] Plugin gestoppt!");
    }


    public static Main getPlugin(){
        return plugin;
    }

}

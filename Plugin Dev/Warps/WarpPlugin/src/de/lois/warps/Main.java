package de.lois.warps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class Main extends JavaPlugin {

    private static Main plugin;

    public void onEnable(){
        plugin = this;
        getConfig();
        saveConfig();

        System.out.println("[Warps]: Plugin geladen!");

    }

    public void onDisable(){
        plugin = this;

        System.out.println("[Warps]: Plugin gestoppt!");
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equalsIgnoreCase("warp")){
            Player p = (Player)sender;
            if(args.length == 0){
                p.sendMessage("§4[Warps]: Usage: §a</warp [ list | add | remove | \"Name of existing warp\" ]>");
                return true;
            }


            if((args.length == 1)){
                if (args[0].equalsIgnoreCase("list")) {

                    if(!(p.hasPermission("warps.list"))){
                        p.sendMessage("§cYou are not permitted to do this!");
                        return true;
                    }

                    Set<String> warps = getConfig().getConfigurationSection("warps").getKeys(false);

                    if(warps.isEmpty()){
                        p.sendMessage("§4[Warps] No warps set!");
                        return true;
                    }

                    p.sendMessage("§aWarps:");

                    for(String string : warps){
                        p.sendMessage(string);
                    }
                    return true;
                }else {

                    if(!(p.hasPermission("warps.warp"))){
                        p.sendMessage("§cYou are not permitted to do this!");
                        return true;
                    }


                    if(!(getConfig().contains("warps." + args[0]))){
                        p.sendMessage("§4[Warps]: §fWarp §a" + args[0] + "§f does not exist!");
                        return true;
                    }

                    World w = Bukkit.getServer().getWorld((getConfig().getString("warps." + args[0] + ".world")));
                    double x = getConfig().getDouble("warps." + args[0] + ".x");
                    double y = getConfig().getDouble("warps." + args[0] + ".y");
                    double z = getConfig().getDouble("warps." + args[0] + ".z");
                    double yaw = getConfig().getDouble("warps." + args[0] + ".yaw");
                    double pitch = getConfig().getDouble("warps." + args[0] + ".pitch");
                    Location loc = new Location(w, x, y, z, (float)yaw, (float)pitch);
                    p.teleport(loc);
                    p.sendMessage("§4[Warps]: §fWarped to §a" + args[0]+ "§f!");
                    return true;

                }

            } else if (args.length == 2){
                if(args[0].equalsIgnoreCase("add")){
                    if(!(p.hasPermission("warps.setwarp"))){
                        p.sendMessage("§cYou are not permitted to do this!");
                        return true;
                    }

                    Location loc = p.getLocation();

                    getConfig().set("warps." + args[1] + ".world", loc.getWorld().getName());
                    getConfig().set("warps." + args[1] + ".x", loc.getX());
                    getConfig().set("warps." + args[1] + ".y", loc.getY());
                    getConfig().set("warps." + args[1] + ".z", loc.getZ());
                    getConfig().set("warps." + args[1] + ".yaw", loc.getYaw());
                    getConfig().set("warps." + args[1] + ".pitch", loc.getPitch());
                    saveConfig();

                    p.sendMessage("§4[Warps]: §fWarp §a" + args[1] + "§f set!");

                    return true;
                } else if(args[0].equalsIgnoreCase("remove")){
                    if(!(p.hasPermission("warps.delwarp"))){
                        p.sendMessage("§cYou are not permitted to do this!");
                        return true;
                    }

                    if(!(getConfig().contains("warps." + args[1]))){
                            p.sendMessage("§4[Warps]: §fWarp §a" + args[1] + "§f does not exist!");
                            return true;
                    }

                    getConfig().set("warps." + args[1], null);
                    saveConfig();
                    p.sendMessage("§4[Warps]: §fWarp §a" + args[1] + "§f removed!");
                    return true;
                    }
                }
            }
            return false;
        }
}



package de.lois.plugin.listener;


import de.lois.plugin.main.Main;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

public class projectileHit implements Listener {

    private Main plugin;

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){



        if (e.getDamager().getType() == EntityType.SNOWBALL){
            if(e.getEntity().getType() == EntityType.PLAYER){
                Player p = (Player) e.getEntity();
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 35, 1));
            }

        }

        if (e.getDamager().getType() == EntityType.EGG){
            if(e.getEntity().getType() == EntityType.PLAYER){
                Player p = (Player) e.getEntity();
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 180, 4));
            }

        }
    }


}
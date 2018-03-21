package de.lois.plugin.listener;


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

public class Snowball implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){

        Player p = (Player) e.getEntity();

         if (e.getDamager().getType() == EntityType.SNOWBALL){
            if(e.getEntity().getType() == EntityType.PLAYER){
                p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 35, 1));
            }

        }
    }

}
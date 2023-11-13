package me.wizzo.hubutility.Events.PvPEvents;

import me.wizzo.hubutility.Utils.PvPFile;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class damageListener implements Listener {

    @EventHandler
    public void onPvP(EntityDamageByEntityEvent e) {
        Entity killer = e.getDamager();

        if (!(killer instanceof Player)) {
            return;
        }

        if (e.getEntity() instanceof Player) {
            Player victim = (Player) e.getEntity();
            if (PvPFile.PvPplayers.contains(killer) && PvPFile.PvPplayers.contains(victim)) {
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }
        }
    }
}

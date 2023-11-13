package me.wizzo.hubutility.Events.DefaultEvents;

import me.wizzo.hubutility.Files.coordinatesSystem;
import me.wizzo.hubutility.Utils.PvPFile;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class entityDamageListener implements Listener {

    @EventHandler
    public void onFall(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        } else {
            Player p = (Player) e.getEntity();

            if (p.getHealth() - e.getFinalDamage() > 0) {
                return;
            } else {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                    e.setCancelled(true);
                } else {
                    e.setCancelled(true);
                    Location spawn = new Location(
                            p.getWorld(),
                            coordinatesSystem.get().getDouble("Spawn.X"),
                            coordinatesSystem.get().getDouble("Spawn.Y"),
                            coordinatesSystem.get().getDouble("Spawn.Z"),
                            (float) coordinatesSystem.get().getDouble("Spawn.Yaw"),
                            (float) coordinatesSystem.get().getDouble("Spawn.Pitch")
                    );

                    Bukkit.getScheduler().runTaskLater(utilsFile.plugin, new Runnable() {
                        @Override
                        public void run() {
                            p.teleport(spawn);
                            p.getInventory().clear();
                            p.getInventory().setItem(utilsFile.config.getInt("Compass.Slot"), utilsFile.getSelector());
                            p.getInventory().setItem(utilsFile.config.getInt("HidePlayer.Slot"), utilsFile.getHideDisable());
                            p.getInventory().setItem(utilsFile.config.getInt("PvP.Sword.Slot"), PvPFile.getSwordPvP());
                            //p.getInventory().setItem(utilsFile.config.getInt("Workbench.Slot"), utilsFile.getMinigames());
                        }
                    }, 2);
                }
            }
        }
    }
}

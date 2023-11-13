package me.wizzo.hubutility.Events.PvPEvents;

import me.wizzo.hubutility.Utils.PvPFile;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class onDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player p = e.getEntity();
        if (p.getKiller() != null) {
            for (Player online: Bukkit.getOnlinePlayers()) {
                online.sendMessage(utilsFile.c(utilsFile.config.getString("PvP.Message.Death")
                        .replace("{killer}", p.getKiller().getName())
                        .replace("{victim}", p.getName())
                        .replace("{PvpPrefix}", utilsFile.c(utilsFile.config.getString("PvP.Prefix")
                        ))
                ));
            }

            e.setCancelled(true);
            e.setDeathMessage(null);
            e.getDrops().clear();
            if (PvPFile.PvPplayers.contains(p)) {
                PvPFile.PvPplayers.remove(p);
            }

        } else {
            e.setCancelled(true);
            e.setDeathMessage(null);
            e.getDrops().clear();
            if (PvPFile.PvPplayers.contains(p)) {
                PvPFile.PvPplayers.remove(p);
            }
        }
    }
}

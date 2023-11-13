package me.wizzo.hubutility.Events.PvPEvents;

import me.wizzo.hubutility.Utils.PvPFile;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;
import java.util.Locale;

public class onCommandListener implements Listener {

    List<String> blockedcmd = utilsFile.config.getStringList("PvP.CmdBlocked");

    @EventHandler (priority = EventPriority.LOWEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {

        Player p = e.getPlayer();
        String command = e.getMessage().toLowerCase();
        for (String cmd : blockedcmd) {
            if (PvPFile.PvPplayers.contains(p)) {
                if (command.startsWith(cmd)) {
                    e.setCancelled(true);
                    p.sendMessage(utilsFile.c(utilsFile.config.getString("PvP.Message.CmdBlocked")
                            .replace("{PvpPrefix}", utilsFile.c(utilsFile.config.getString("PvP.Prefix")
                            ))
                    ));
                }
            }
        }
    }
}

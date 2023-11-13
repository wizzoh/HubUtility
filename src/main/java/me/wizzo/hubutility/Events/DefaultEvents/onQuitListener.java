package me.wizzo.hubutility.Events.DefaultEvents;

import me.wizzo.hubutility.Utils.PvPFile;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class onQuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(null);
        //utilsFile.OnlinePlayer.remove(player);
        PvPFile.PvPplayers.remove(player);
    }
}

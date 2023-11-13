package me.wizzo.hubutility.Events.DefaultEvents;

import me.wizzo.hubutility.Files.coordinatesSystem;
import me.wizzo.hubutility.Utils.PvPFile;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(null);

        utilsFile.plugin.dbGetterStacker.createPlayer(player);
        utilsFile.plugin.dbGetterHider.createPlayer(player);


        Location spawn = new Location(
                player.getWorld(),
                coordinatesSystem.get().getDouble("Spawn.X"),
                coordinatesSystem.get().getDouble("Spawn.Y"),
                coordinatesSystem.get().getDouble("Spawn.Z"),
                (float) coordinatesSystem.get().getDouble("Spawn.Yaw"),
                (float) coordinatesSystem.get().getDouble("Spawn.Pitch")
        );

        try {
            player.teleport(spawn);
        } catch (Exception err) {
            err.printStackTrace();
        }

        if (player.getHealth() < 20) {
            player.setHealth(20);
        }
        if (player.getFoodLevel() < 10) {
            player.setFoodLevel(10);
        }

        player.getInventory().clear();
        player.setLevel(0);
        player.getInventory().setHeldItemSlot(0);

        player.getInventory().setItem(utilsFile.config.getInt("Compass.Slot"), utilsFile.getSelector());
        player.getInventory().setItem(utilsFile.config.getInt("PvP.Sword.Slot"), PvPFile.getSwordPvP());
        //player.getInventory().setItem(utilsFile.config.getInt("Workbench.Slot"), utilsFile.getMinigames());
        player.getInventory().setItem(utilsFile.config.getInt("Parkour.Slot"), utilsFile.getParkour());
        if (utilsFile.plugin.dbGetterHider.getValue(player.getUniqueId()) == 1) {
            player.getInventory().setItem(utilsFile.config.getInt("HidePlayer.Slot"), utilsFile.getHideEnable());
        } else {
            player.getInventory().setItem(utilsFile.config.getInt("HidePlayer.Slot"), utilsFile.getHideDisable());
        }

        /*Bukkit.getScheduler().scheduleAsyncDelayedTask(utilsFile.plugin, () -> {

        }, 1);*/

        if (Bukkit.getServer().getOnlinePlayers().size() == 1 && Bukkit.getServer().getOnlinePlayers().contains(player)) {

        } else if (Bukkit.getServer().getOnlinePlayers().size() == 0) {

        } else {
            for (Player online: Bukkit.getOnlinePlayers()) {
                if (utilsFile.plugin.dbGetterHider.getValue(player.getUniqueId()) == 1) {
                    player.hidePlayer(utilsFile.plugin, online);
                }
                if (utilsFile.plugin.dbGetterHider.getPlayers().contains(online.getUniqueId().toString())) {
                    online.hidePlayer(utilsFile.plugin, player);
                }
            }
        }
    }
}

package me.wizzo.hubutility.Events.DefaultEvents;

import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

public class playerInteractAtEntityListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractAtEntityEvent event) {

        Player player = event.getPlayer();
        Entity target = event.getRightClicked();
        ItemStack handItem = player.getInventory().getItemInMainHand();

        boolean isNPC = target.hasMetadata("NPC");

        if (!isNPC || target instanceof ArmorStand) {
            int pValue = utilsFile.plugin.dbGetterStacker.getValue(player.getUniqueId());
            int tValue = utilsFile.plugin.dbGetterStacker.getValue(target.getUniqueId());

            if (pValue == 1 && tValue == 1) {
                if (!target.getPassengers().contains(player)) {
                    target.addPassenger(player);
                }
            } else {
                if (pValue == 1 && tValue == 0) {
                    player.sendMessage(utilsFile.c(utilsFile.config.getString("Stacker.TargetNotEnable")
                            .replace("{prefix}", utilsFile.prefix)
                    ));
                    return;
                }
                if (pValue == 0 && tValue == 1) {
                    player.sendMessage(utilsFile.c(utilsFile.config.getString("Stacker.SelfNotEnable")
                            .replace("{prefix}", utilsFile.prefix)
                    ));
                    return;
                }
                if (pValue == 0 && tValue == 0) {
                    player.sendMessage(utilsFile.c(utilsFile.config.getString("Stacker.AllNotEnable")
                            .replace("{prefix}", utilsFile.prefix)
                    ));
                    return;
                }
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        Entity passenger = (Player) player.getPassenger();
        if (player.isSneaking() && !player.getPassengers().isEmpty()) {
            player.removePassenger(passenger);
        }
    }
}

package me.wizzo.hubutility.Events.DefaultEvents;

import me.wizzo.hubutility.Utils.PvPFile;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.UUID;

public class playerInteractListener implements Listener {

    private ArrayList<UUID> cooldowns = new ArrayList<>();

    @EventHandler
    public void onClick(PlayerInteractEvent e) {

        Player p = e.getPlayer();
        ItemStack handItem = p.getInventory().getItemInMainHand();
        Action action = e.getAction();
        boolean b = action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK);

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if (handItem != null && handItem.getType() == Material.COMPASS) {
            if (b) {
                p.performCommand(utilsFile.config.getString("Compass.Command"));
            }
        }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if (handItem != null && handItem.getType() == Material.SLIME_BALL) {
            if (b) {

                if (utilsFile.plugin.dbGetterHider.getValue(p.getUniqueId()) == 0) {
                    utilsFile.plugin.dbGetterHider.hiderOnOff(p, 1);
                    //utilsFile.OnlinePlayer.add(p);
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        p.hidePlayer(utilsFile.plugin, online);
                    }

                    p.sendMessage(utilsFile.c(utilsFile.config.getString("HidePlayer.Message.Enable")
                            .replace("{prefix}", utilsFile.prefix)
                    ));
                    p.getInventory().setItem(utilsFile.config.getInt("HidePlayer.Slot"), utilsFile.getHideEnable());

                } else {

                    utilsFile.plugin.dbGetterHider.hiderOnOff(p, 0);
                    //utilsFile.OnlinePlayer.remove(p);
                    for (Player online : Bukkit.getOnlinePlayers()) {
                        p.showPlayer(utilsFile.plugin, online);
                    }

                    p.sendMessage(utilsFile.c(utilsFile.config.getString("HidePlayer.Message.Disable")
                            .replace("{prefix}", utilsFile.prefix)
                    ));
                    p.getInventory().setItem(utilsFile.config.getInt("HidePlayer.Slot"), utilsFile.getHideDisable());
                }
            }
        }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if (handItem != null && handItem.getType() == Material.DIAMOND_SWORD) {
            if (b && p.isSneaking()) {

                if (!PvPFile.PvPplayers.contains(p)) {

                    if (utilsFile.plugin.dbGetterHider.getValue(p.getUniqueId()) == 1) {
                        utilsFile.plugin.dbGetterHider.hiderOnOff(p, 0);
                    }
                    if (p.getAllowFlight()) {
                        p.sendMessage("§cDisattiva la fly prima di attivare il pvp!");
                    } else {
                        PvPFile.PvPplayers.add(p);

                        p.getInventory().setItem(utilsFile.config.getInt("Compass.Slot"), null);
                        p.getInventory().setItem(utilsFile.config.getInt("HidePlayer.Slot"), null);
                        p.getInventory().setItem(utilsFile.config.getInt("Workbench.Slot"), null);
                        p.getInventory().setItem(utilsFile.config.getInt("Parkour.Slot"), null);
                        p.getEquipment().setHelmet(PvPFile.getHelmet());
                        p.getEquipment().setChestplate(PvPFile.getChestPlate());
                        p.getEquipment().setLeggings(PvPFile.getLeggings());
                        p.getEquipment().setBoots(PvPFile.getBoots());
                        p.sendMessage(utilsFile.c(utilsFile.config.getString("PvP.Message.Enable")
                                .replace("{PvpPrefix}", utilsFile.config.getString("PvP.Prefix")
                                        .replaceAll("&", "§")
                                )
                        ));
                    }
                } else {

                    if (handItem.getType() == Material.DIAMOND_SWORD && cooldowns.contains(p)) {
                        if (b && p.isSneaking()) {
                            if (cooldowns.contains(p.getUniqueId())) {
                                p.sendMessage(utilsFile.c(utilsFile.config.getString("PvP.Message.Cooldown")
                                        .replace("{PvpPrefix}", utilsFile.config.getString("PvP.Prefix")
                                                .replaceAll("&", "§")
                                        )
                                ));
                            }
                        }
                    }

                    if (!cooldowns.contains(p.getUniqueId())) {

                        p.setLevel(5);
                        cooldowns.add(p.getUniqueId());

                        Bukkit.getScheduler().runTaskLater(utilsFile.plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.setLevel(4);
                            }
                        }, 20);

                        Bukkit.getScheduler().runTaskLater(utilsFile.plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.setLevel(3);
                            }
                        }, 40);

                        Bukkit.getScheduler().runTaskLater(utilsFile.plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.setLevel(2);
                            }
                        }, 60);

                        Bukkit.getScheduler().runTaskLater(utilsFile.plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.setLevel(1);
                            }
                        }, 80);

                        Bukkit.getScheduler().runTaskLater(utilsFile.plugin, new Runnable() {
                            @Override
                            public void run() {
                                p.setLevel(0);
                                PvPFile.PvPplayers.remove(p);
                                cooldowns.remove(p.getUniqueId());
                                p.setHealth(20);
                                p.getInventory().setItem(utilsFile.config.getInt("Compass.Slot"), utilsFile.getSelector());
                                p.getInventory().setItem(utilsFile.config.getInt("HidePlayer.Slot"), utilsFile.getHideDisable());
                                //p.getInventory().setItem(utilsFile.config.getInt("Workbench.Slot"), utilsFile.getMinigames());
                                p.getInventory().setItem(utilsFile.config.getInt("Parkour.Slot"), utilsFile.getParkour());
                                p.getEquipment().setHelmet(null);
                                p.getEquipment().setChestplate(null);
                                p.getEquipment().setLeggings(null);
                                p.getEquipment().setBoots(null);
                                p.sendMessage(utilsFile.c(utilsFile.config.getString("PvP.Message.Disable")
                                        .replace("{PvpPrefix}", utilsFile.c(utilsFile.config.getString("PvP.Prefix")
                                        ))
                                ));
                            }
                        }, 100);

                    }
                }
            }
        }

//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        if (handItem != null && handItem.getType() == Material.WORKBENCH) {
            if (b) {
                p.performCommand(utilsFile.config.getString("Workbench.Command"));
            }
        }

        if (handItem != null && handItem.getType() == Material.LADDER) {
            if (b) {
                p.performCommand(utilsFile.config.getString("Parkour.Command"));
            }
        }
    }
}
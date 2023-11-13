package me.wizzo.hubutility.Commands;

import me.wizzo.hubutility.Files.coordinatesSystem;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ParkourCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage(utilsFile.c(utilsFile.config.getString("OnlyPlayer")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        Player p = (Player) s;
        if (args.length == 0) {

            if (utilsFile.config.getBoolean("Parkour.Enable")) {

                Location staff = new Location(
                        p.getWorld(),
                        coordinatesSystem.get().getDouble("Parkour.X"),
                        coordinatesSystem.get().getDouble("Parkour.Y"),
                        coordinatesSystem.get().getDouble("Parkour.Z"),
                        (float) coordinatesSystem.get().getDouble("Parkour.Yaw"),
                        (float) coordinatesSystem.get().getDouble("Parkour.Pitch")
                );

                try {
                    p.teleport(staff);
                    p.sendMessage(utilsFile.c(utilsFile.config.getString("Parkour.ParkourTP.Success")
                            .replace("{prefix}", utilsFile.prefix)
                    ));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}

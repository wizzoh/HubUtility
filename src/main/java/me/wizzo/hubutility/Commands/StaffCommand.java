package me.wizzo.hubutility.Commands;

import me.wizzo.hubutility.Files.coordinatesSystem;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffCommand implements CommandExecutor {
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

            if (utilsFile.config.getBoolean("StaffRoom.Enable")) {

                Location staff = new Location(
                        p.getWorld(),
                        coordinatesSystem.get().getDouble("Staff.X"),
                        coordinatesSystem.get().getDouble("Staff.Y"),
                        coordinatesSystem.get().getDouble("Staff.Z"),
                        (float) coordinatesSystem.get().getDouble("Staff.Yaw"),
                        (float) coordinatesSystem.get().getDouble("Staff.Pitch")
                );

                try {
                    p.teleport(staff);
                    p.sendMessage(utilsFile.c(utilsFile.config.getString("StaffRoom.StaffTP.Success")
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

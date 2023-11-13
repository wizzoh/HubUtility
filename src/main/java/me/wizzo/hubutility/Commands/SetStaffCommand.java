package me.wizzo.hubutility.Commands;

import me.wizzo.hubutility.Files.coordinatesSystem;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetStaffCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage(utilsFile.c(utilsFile.config.getString("OnlyPlayer")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        Player p = (Player) s;

        if (!p.hasPermission("hub.setstaff")) {
            p.sendMessage(utilsFile.c(utilsFile.config.getString("NoPerm")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        if (args.length > 0) {
            p.sendMessage(utilsFile.c(utilsFile.config.getString("StaffRoom.SetSpawn.Usage")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        Location loc = p.getLocation();
        coordinatesSystem.get().set("Staff.X", loc.getX());
        coordinatesSystem.get().set("Staff.Y", loc.getY());
        coordinatesSystem.get().set("Staff.Z", loc.getZ());
        coordinatesSystem.get().set("Staff.Yaw", loc.getYaw());
        coordinatesSystem.get().set("Staff.Pitch", loc.getPitch());
        coordinatesSystem.saving();

        p.sendMessage(utilsFile.c(utilsFile.config.getString("StaffRoom.SetSpawn.Success")
                .replace("{prefix}", utilsFile.prefix)
        ));
        return true;
    }
}

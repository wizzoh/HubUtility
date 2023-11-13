package me.wizzo.hubutility.Commands;

import me.wizzo.hubutility.Files.coordinatesSystem;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetParkourCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage(utilsFile.c(utilsFile.config.getString("OnlyPlayer")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        Player p = (Player) s;

        if (!p.hasPermission("hub.setparkour")) {
            p.sendMessage(utilsFile.c(utilsFile.config.getString("NoPerm")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        if (args.length > 0) {
            p.sendMessage(utilsFile.c(utilsFile.config.getString("Parkour.SetSpawn.Usage")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        Location loc = p.getLocation();
        coordinatesSystem.get().set("Parkour.X", loc.getX());
        coordinatesSystem.get().set("Parkour.Y", loc.getY());
        coordinatesSystem.get().set("Parkour.Z", loc.getZ());
        coordinatesSystem.get().set("Parkour.Yaw", loc.getYaw());
        coordinatesSystem.get().set("Parkour.Pitch", loc.getPitch());
        coordinatesSystem.saving();

        p.sendMessage(utilsFile.c(utilsFile.config.getString("Parkour.SetSpawn.Success")
                .replace("{prefix}", utilsFile.prefix)
        ));
        return true;
    }
}

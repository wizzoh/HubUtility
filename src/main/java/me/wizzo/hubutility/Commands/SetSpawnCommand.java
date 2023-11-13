package me.wizzo.hubutility.Commands;

import me.wizzo.hubutility.Files.coordinatesSystem;
import me.wizzo.hubutility.Hubutility;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender s, Command cmd, String label, String[] args) {

        if (!(s instanceof Player)) {
            s.sendMessage(utilsFile.c(utilsFile.config.getString("OnlyPlayer")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        Player p = (Player) s;

        if (!p.hasPermission("hub.setspawn")) {
            p.sendMessage(utilsFile.c(utilsFile.config.getString("NoPerm")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        if (args.length > 0) {
            p.sendMessage(utilsFile.c(utilsFile.config.getString("Spawn.SetSpawn.Usage")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        Location loc = p.getLocation();
        coordinatesSystem.get().set("Spawn.X", loc.getX());
        coordinatesSystem.get().set("Spawn.Y", loc.getY());
        coordinatesSystem.get().set("Spawn.Z", loc.getZ());
        coordinatesSystem.get().set("Spawn.Yaw", loc.getYaw());
        coordinatesSystem.get().set("Spawn.Pitch", loc.getPitch());
        coordinatesSystem.saving();

            p.sendMessage(utilsFile.c(utilsFile.config.getString("Spawn.SetSpawn.Success")
                    .replace("{prefix}", utilsFile.prefix)
            ));
        return true;
    }
}

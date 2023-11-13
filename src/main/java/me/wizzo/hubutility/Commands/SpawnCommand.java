package me.wizzo.hubutility.Commands;

import me.wizzo.hubutility.Files.coordinatesSystem;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
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
            Location spawn = new Location(
                    p.getWorld(),
                    coordinatesSystem.get().getDouble("Spawn.X"),
                    coordinatesSystem.get().getDouble("Spawn.Y"),
                    coordinatesSystem.get().getDouble("Spawn.Z"),
                    (float) coordinatesSystem.get().getDouble("Spawn.Yaw"),
                    (float) coordinatesSystem.get().getDouble("Spawn.Pitch")
            );

            try {
                p.teleport(spawn);
                p.sendMessage(utilsFile.c(utilsFile.config.getString("Spawn.SpawnTP.Success")
                        .replace("{prefix}", utilsFile.prefix)
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}

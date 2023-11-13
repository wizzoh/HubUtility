package me.wizzo.hubutility.Commands;

import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StackerCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(utilsFile.c(utilsFile.config.getString("OnlyPlayer")
                    .replace("{prefix}", utilsFile.prefix)
            ));
            return false;
        }

        Player player = (Player) sender;

        if (args.length == 0 && utilsFile.plugin.dbGetterStacker.getValue(player.getUniqueId()) == 0) {
            utilsFile.plugin.dbGetterStacker.stackerOnOff(player, 1);
            player.sendMessage(utilsFile.c(utilsFile.config.getString("Stacker.Enable")
                    .replace("{prefix}", utilsFile.prefix)
            ));

        } else if (args.length == 0 && utilsFile.plugin.dbGetterStacker.getValue(player.getUniqueId()) == 1){
            utilsFile.plugin.dbGetterStacker.stackerOnOff(player, 0);
            player.sendMessage(utilsFile.c(utilsFile.config.getString("Stacker.Disable")
                    .replace("{prefix}", utilsFile.prefix)
            ));
        }
        return false;
    }
}

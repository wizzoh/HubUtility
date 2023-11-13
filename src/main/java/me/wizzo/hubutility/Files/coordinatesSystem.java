package me.wizzo.hubutility.Files;

import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class coordinatesSystem {
    private static File file;
    protected static FileConfiguration conf;

    public static void setup() {
        file = new File(utilsFile.plugin.getDataFolder(), "coordinates.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                conf = YamlConfiguration.loadConfiguration(file);
                defaultConfig();
                saving();

            } catch (IOException e) {
                utilsFile.plugin.getLogger().info(ChatColor.RED + "Errore! Controlla la console per i dettagli");
            }
        }
        conf = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return conf;
    }

    public static void saving() {
        try {
            conf.save(file);
        } catch (IOException e) {
            System.out.printf(ChatColor.RED + "Errore durante il salvataggio del file");
        }
    }

    public static void reload() {
        if (file == null) {
            utilsFile.plugin.getLogger().info(ChatColor.RED + "[HubUtility] Errore durante il reload del file coordinates!");
        } else {
            conf = YamlConfiguration.loadConfiguration(file);
        }
    }

    private static void defaultConfig() {
        conf.set("Spawn.X", 0);
        conf.set("Spawn.Y", 0);
        conf.set("Spawn.Z", 0);
        conf.set("Spawn.Yaw", 0);
        conf.set("Spawn.Pitch", 0);

        conf.set("Staff.X", 0);
        conf.set("Staff.Y", 0);
        conf.set("Staff.Z", 0);
        conf.set("Staff.Yaw", 0);
        conf.set("Staff.Pitch", 0);

        conf.set("Parkour.X", 0);
        conf.set("Parkour.Y", 0);
        conf.set("Parkour.Z", 0);
        conf.set("Parkour.Yaw", 0);
        conf.set("Parkour.Pitch", 0);
    }
}

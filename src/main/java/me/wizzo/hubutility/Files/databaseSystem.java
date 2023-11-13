package me.wizzo.hubutility.Files;

import me.wizzo.hubutility.Hubutility;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class databaseSystem {

    private static File file;
    private static FileConfiguration conf;

    public static void setup() {
        file = new File(utilsFile.plugin.getDataFolder(), "database.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                conf = YamlConfiguration.loadConfiguration(file);
                defaultConfig();
                save();

            } catch (IOException e) {
                utilsFile.plugin.getLogger().info(ChatColor.RED + "Errore! Controlla la console per i dettagli");
            }
        }
        conf = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return conf;
    }

    public static void save() {
        try {
            conf.save(file);
        } catch (IOException e) {
            System.out.printf(ChatColor.RED + "Errore durante il salvataggio del file");
        }
    }

    public static void reload() {
        if (file == null) {
            utilsFile.plugin.getLogger().info(ChatColor.RED + "[HubUtility] Errore durante il reload del file database!");
        } else {
            conf = YamlConfiguration.loadConfiguration(file);
        }
    }

    private static void defaultConfig() {
        conf.set("Url", "jdbc:mariadb://{ip}:{port}/{database}");
        conf.set("ClassName", "org.mariadb.jdbc.MariaDbDataSource");
        conf.set("Host", "127.0.0.1");
        conf.set("Port", 3306);
        conf.set("Database", "database");
        conf.set("Username", "root");
        conf.set("Password", "password");
        conf.set("Tables.Stacker", "player_stacker");
        conf.set("Tables.HidePlayer", "player_hider");
        conf.set("Max-pool-size", 10);
    }
}

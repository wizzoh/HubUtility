package me.wizzo.hubutility;

import me.wizzo.hubutility.Commands.*;
import me.wizzo.hubutility.Database.DatabaseGetterHider;
import me.wizzo.hubutility.Database.DatabaseGetterStacker;
import me.wizzo.hubutility.Database.HikariCPSettings;
import me.wizzo.hubutility.Events.DefaultEvents.*;
import me.wizzo.hubutility.Events.PvPEvents.damageListener;
import me.wizzo.hubutility.Events.PvPEvents.onCommandListener;
import me.wizzo.hubutility.Events.PvPEvents.onDeathListener;
import me.wizzo.hubutility.Files.coordinatesSystem;
import me.wizzo.hubutility.Files.databaseSystem;
import me.wizzo.hubutility.Utils.utilsFile;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import java.sql.SQLException;

public final class Hubutility extends JavaPlugin {

    private ConsoleCommandSender console = Bukkit.getConsoleSender();
    public DatabaseGetterStacker dbGetterStacker;
    private HikariCPSettings hikariCPSettings;
    public DatabaseGetterHider dbGetterHider;


    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        registerFiles();
        commandRegister();
        eventsRegister();
        registerDatabase();

        try {
            hikariCPSettings.initSource();
            dbGetterHider.createTables();
            dbGetterStacker.createTables();

            console.sendMessage("");
            console.sendMessage(utilsFile.c("&a[HubUtility] Connessione al database eseguita!"));
            console.sendMessage(utilsFile.c("&a[HubUtility] Plugin online!"));
            console.sendMessage(utilsFile.c("&a[HubUtility] Created by wizzo <3"));
            console.sendMessage("");
        } catch (SQLException e) {
            console.sendMessage(utilsFile.c("&a[HubUtility] Impossibile connettersi al database! Controlla le credenziali"));
            Bukkit.getPluginManager().disablePlugin(this);
        }

        /*if (dbSettings.isConnected()) {
            dbGetterStacker.createTables();
            dbGetterHider.createTables();
        }*/


    }

    @Override
    public void onDisable() {

        //dbSettings.disconnect();
        hikariCPSettings.close(hikariCPSettings.getSource());
        console.sendMessage("");
        console.sendMessage(utilsFile.c("&a[HubUtility] Plugin offline!"));
        console.sendMessage(utilsFile.c("&a[HubUtility] Created by wizzo <3"));
        console.sendMessage("");
    }


    private void commandRegister() {

        this.getCommand("spawn").setExecutor(new SpawnCommand());
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("staff").setExecutor(new StaffCommand());
        this.getCommand("setstaff").setExecutor(new SetStaffCommand());
        this.getCommand("parkour").setExecutor(new ParkourCommand());
        this.getCommand("setparkour").setExecutor(new SetParkourCommand());
        this.getCommand("stacker").setExecutor(new StackerCommand());
    }

    private void eventsRegister() {

        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new onJoinListener(), this);
        manager.registerEvents(new onQuitListener(), this);
        manager.registerEvents(new playerInteractListener(), this);
        manager.registerEvents(new entityDamageListener(), this);
        manager.registerEvents(new InventoryListener(), this);
        manager.registerEvents(new onFoodListener(), this);
        manager.registerEvents(new playerInteractAtEntityListener(), this);

        manager.registerEvents(new onDeathListener(), this);
        manager.registerEvents(new damageListener(), this);
        manager.registerEvents(new onCommandListener(), this);
    }

    private void registerFiles() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        coordinatesSystem.setup();
        databaseSystem.setup();
    }

    private void registerDatabase() {
        this.hikariCPSettings = new HikariCPSettings();
        this.dbGetterStacker = new DatabaseGetterStacker(this);
        this.dbGetterHider = new DatabaseGetterHider(this);
    }

    public HikariCPSettings getHikariCP() {
        return hikariCPSettings;
    }
}
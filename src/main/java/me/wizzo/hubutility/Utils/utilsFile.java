package me.wizzo.hubutility.Utils;

import me.wizzo.hubutility.Hubutility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class utilsFile {

    public static String c(final String c) {
        return ChatColor.translateAlternateColorCodes('&', c);
    }

    public static Hubutility plugin = Hubutility.getPlugin(Hubutility.class);
    public static FileConfiguration config = plugin.getConfig();
    public static String prefix = utilsFile.c(config.getString("Prefix"));

//---------------------------------------------------------------------------

    public static ItemStack getSelector() {
        ItemStack compass = new ItemStack(Material.COMPASS);
        ItemMeta cpMeta = compass.getItemMeta();
        cpMeta.setDisplayName(c(utilsFile.config.getString("Compass.Name")
        ));
        cpMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        cpMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        cpMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        cpMeta.setLore(utilsFile.config.getStringList("Compass.Lore"));
        compass.setItemMeta(cpMeta);
        return compass;
    }

    public static ItemStack getHideDisable() {
        ItemStack slimeball = new ItemStack(Material.SLIME_BALL);
        ItemMeta slimeMeta =  slimeball.getItemMeta();
        slimeMeta.setDisplayName(c(config.getString("HidePlayer.Name.Disable")
        ));
        slimeMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        slimeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        slimeMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        slimeMeta.setLore(utilsFile.config.getStringList("HidePlayer.Lore"));
        slimeball.setItemMeta(slimeMeta);
        return slimeball;
    }

    public static ItemStack getHideEnable() {
        ItemStack slimeball = new ItemStack(Material.SLIME_BALL);
        ItemMeta slimeMeta =  slimeball.getItemMeta();
        slimeMeta.setDisplayName(c(config.getString("HidePlayer.Name.Enable")
        ));
        slimeMeta.setLore(utilsFile.config.getStringList("HidePlayer.Lore"));
        slimeball.setItemMeta(slimeMeta);
        return slimeball;
    }

    public static ItemStack getMinigames() {
        ItemStack workBench = new ItemStack(Material.WORKBENCH);
        ItemMeta benchMeta = workBench.getItemMeta();
        benchMeta.setDisplayName(c(utilsFile.config.getString("Workbench.Name")
        ));
        benchMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        benchMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        benchMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        benchMeta.setLore(utilsFile.config.getStringList("Workbench.Lore"));
        workBench.setItemMeta(benchMeta);
        return workBench;
    }

    public static ItemStack getParkour() {
        ItemStack ladder = new ItemStack(Material.LADDER);
        ItemMeta ladderMeta = ladder.getItemMeta();
        ladderMeta.setDisplayName(c(utilsFile.config.getString("Parkour.Name")
        ));
        ladderMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        ladderMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ladderMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        ladderMeta.setLore(utilsFile.config.getStringList("Parkour.Lore"));
        ladder.setItemMeta(ladderMeta);
        return ladder;
    }
}

package me.wizzo.hubutility.Utils;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;

public class PvPFile {

    public static ArrayList<Player> PvPplayers = new ArrayList<>();

    public static ItemStack getSwordPvP() {
        ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta swordMeta =  sword.getItemMeta();
        swordMeta.addEnchant(Enchantment.DAMAGE_ALL, utilsFile.config.getInt("PvP.Sword.SharpLvl"), true);
        swordMeta.setDisplayName(utilsFile.c(utilsFile.config.getString("PvP.Sword.Name")
        ));
        swordMeta.setUnbreakable(true);
        swordMeta.setLore(null);
        swordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        swordMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        swordMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        swordMeta.setLore(utilsFile.config.getStringList("PvP.Sword.Lore"));
        sword.setItemMeta(swordMeta);
        return sword;
    }

    public static ItemStack getHelmet() {
        ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta helmMeta =  helmet.getItemMeta();
        helmMeta.setDisplayName(utilsFile.c(utilsFile.config.getString("PvP.Helmet.Name")
        ));
        helmMeta.setUnbreakable(true);
        helmMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        helmMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        helmet.setItemMeta(helmMeta);

        return helmet;
    }

    public static ItemStack getChestPlate() {
        ItemStack chestPlate = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta chestMeta =  chestPlate.getItemMeta();
        chestMeta.setDisplayName(utilsFile.c(utilsFile.config.getString("PvP.Chestplate.Name")
        ));
        chestMeta.setUnbreakable(true);
        chestMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        chestMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        chestPlate.setItemMeta(chestMeta);
        return chestPlate;
    }

    public static ItemStack getLeggings() {
        ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta legMeta =  leggings.getItemMeta();
        legMeta.setDisplayName(utilsFile.c(utilsFile.config.getString("PvP.Leggings.Name")
        ));
        legMeta.setUnbreakable(true);
        legMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        legMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        leggings.setItemMeta(legMeta);
        return leggings;
    }

    public static ItemStack getBoots() {
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta bootsMeta =  boots.getItemMeta();
        bootsMeta.setDisplayName(utilsFile.c(utilsFile.config.getString("PvP.Boots.Name")
        ));
        bootsMeta.setUnbreakable(true);
        bootsMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        bootsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        boots.setItemMeta(bootsMeta);
        return boots;
    }
}

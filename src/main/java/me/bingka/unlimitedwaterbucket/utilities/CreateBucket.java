package me.bingka.unlimitedwaterbucket.utilities;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CreateBucket {
    public static ItemStack createUnlimitedWaterBucket() {
        ItemStack bucket = new ItemStack(Material.WATER_BUCKET);
        ItemMeta meta = bucket.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD +"Unlimited Water Bucket");
        List<String> lore = new ArrayList<>();
        lore.add("Unlimited Water Bucket for PyroFarming");
        lore.add("Use these on your growstations!");
        meta.setLore(lore);
        bucket.setItemMeta(meta);
        return bucket;
    }
}

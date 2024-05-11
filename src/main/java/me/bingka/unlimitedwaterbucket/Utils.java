package me.bingka.unlimitedwaterbucket;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import java.util.List;

public class Utils {
    public static int countEmptyBuckets(Player player) {
        int count = 0;
        Inventory inv = player.getInventory();
        for (ItemStack item : inv.getContents()) {
            if (item != null && item.getType() == Material.BUCKET) {
                count += item.getAmount();
            }
        }
        return count;
    }

    public static boolean hasUnlimitedWater(ItemStack item) {
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta.hasDisplayName() && meta.getDisplayName().equals("Unlimited Water Bucket")) {
                List<String> lore = meta.getLore();
                if (lore != null && lore.contains("Unlimited Water")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isGrowstation(Material material) {
        // Check if the material is a growstation block
        return material == Material.FLOWER_POT || material.name().startsWith("POTTED_");
    }

    // Returns the water bucket
    public static void returnBucket(Player player, ItemStack item) {
        player.getInventory().setItem(player.getInventory().getHeldItemSlot(), item);
    }

    // Destroys the water bucket
    public static void destroyEmptyBucket(Player player) {
        // Get the plugin instance
        Plugin waterBucket = Bukkit.getServer().getPluginManager().getPlugin("UnlimitedWaterBucket");

        // Check if the plugin instance is not null
        if (waterBucket != null) {
            // Schedule the inventory-related actions on the next tick
            Bukkit.getScheduler().runTask(waterBucket, new Runnable() {
                @Override
                public void run() {
                    // Destroys the empty water bucket
                    player.getInventory().removeItem(new ItemStack(Material.BUCKET));
                }
            });
        } else {
            // Handle the case where the plugin instance is null
            player.sendMessage("Error: Plugin instance not found.");
        }
    }
}

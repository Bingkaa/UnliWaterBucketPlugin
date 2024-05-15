package me.bingka.unlimitedwaterbucket.utilities;

import me.bingka.unlimitedwaterbucket.UnlimitedWaterBucket;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.stream.Collectors;

public class CreateBucket {
    public static ItemStack createUnlimitedWaterBucket() {
        FileConfiguration config = UnlimitedWaterBucket.getInstance().getConfig();
        ItemStack bucket = new ItemStack(Material.WATER_BUCKET);
        ItemMeta meta = bucket.getItemMeta();
        NamespacedKey key = UnlimitedWaterBucket.getInstance().createKey("bucket_data");
        PersistentDataContainer dataContainer = meta.getPersistentDataContainer();
        String name = ChatColor.translateAlternateColorCodes('&', config.getString("bucket.name", "&5&lUnlimited Water Bucket"));
        List<String> loreConfig = config.getStringList("bucket.lore");

        dataContainer.set(key, PersistentDataType.BOOLEAN, true); // Sets permanent metadata
        meta.setDisplayName(name);
        List<String> lore = loreConfig.stream()
                .map(line -> ChatColor.translateAlternateColorCodes('&', line))
                .collect(Collectors.toList());
        meta.setLore(lore);
        bucket.setItemMeta(meta);
        return bucket;
    }
}

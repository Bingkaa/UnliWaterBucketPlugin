package me.bingka.unlimitedwaterbucket.listeners;

import me.bingka.unlimitedwaterbucket.UnlimitedWaterBucket;
import me.bingka.unlimitedwaterbucket.utilities.BucketUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class WaterBucketListener implements Listener {
    private final UnlimitedWaterBucket plugin;

    public WaterBucketListener(UnlimitedWaterBucket plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        int emptyBucketsBefore = BucketUtils.countEmptyBuckets(player);

        // Check if the player interacts with a block while holding a water bucket
        if (item != null && item.getType() == Material.WATER_BUCKET && BucketUtils.hasUnlimitedWater(item)) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null && BucketUtils.isGrowstation(clickedBlock.getType())) {
                BucketUtils.returnBucket(player, item);

                // Schedule a delayed task to update empty bucket count after returning the bucket
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    int emptyBucketsAfter = BucketUtils.countEmptyBuckets(player);
                    if (emptyBucketsAfter > emptyBucketsBefore) {
                        player.sendMessage(ChatColor.DARK_PURPLE + "You used the unlimited water bucket on a growstation!");
                        BucketUtils.destroyEmptyBucket(player);
                    }
                }, 1); // Adjust the delay if needed, 1 tick usually suffices
            } else {
                player.sendMessage(ChatColor.RED + "You can only use this water bucket on growstations.");
                event.setCancelled(true);
            }
        }
    }
}

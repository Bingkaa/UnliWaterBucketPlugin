package me.bingka.unlimitedwaterbucket;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class WaterBucketListener implements Listener {
    private final UnlimitedWaterBucket plugin;

    public WaterBucketListener(UnlimitedWaterBucket plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        int emptyBucketsBefore = Utils.countEmptyBuckets(player);

        // Check if the player interacts with a block while holding a water bucket
        if (item != null && item.getType() == Material.WATER_BUCKET && Utils.hasUnlimitedWater(item)) {
            Block clickedBlock = event.getClickedBlock();
            if (clickedBlock != null && Utils.isGrowstation(clickedBlock.getType())) {
                player.sendMessage("You used the unlimited water bucket on a growstation!");
                Utils.returnBucket(player, item);

                // Schedule a delayed task to update empty bucket count after returning the bucket
                Bukkit.getScheduler().runTaskLater(plugin, () -> {
                    int emptyBucketsAfter = Utils.countEmptyBuckets(player);
                    if (emptyBucketsAfter > emptyBucketsBefore) {
                        Utils.destroyEmptyBucket(player);
                    }
                }, 1); // Adjust the delay if needed, 1 tick usually suffices
            } else {
                player.sendMessage("You can only use this water bucket on growstations.");
                event.setCancelled(true);
            }
        }
    }
}

package me.bingka.unlimitedwaterbucket;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {
    private final UnlimitedWaterBucket plugin;

    public Commands(UnlimitedWaterBucket plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("unliwater")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command.");
                return true;
            }
            Player player = (Player) sender;
            giveUnlimitedWater(player);
            player.sendMessage("You received an unlimited water bucket!");
            return true;
        }
        return false;
    }

    private void giveUnlimitedWater(Player player) {
        ItemStack unlimitedWaterBucket = createUnlimitedWaterBucket();
        player.getInventory().addItem(unlimitedWaterBucket);
    }

    private ItemStack createUnlimitedWaterBucket() {
        ItemStack bucket = new ItemStack(Material.WATER_BUCKET);
        ItemMeta meta = bucket.getItemMeta();
        meta.setDisplayName("Unlimited Water Bucket");
        List<String> lore = new ArrayList<>();
        lore.add("Unlimited Water");
        meta.setLore(lore);
        bucket.setItemMeta(meta);
        return bucket;
    }
}

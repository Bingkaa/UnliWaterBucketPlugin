package me.bingka.unlimitedwaterbucket.commands;

import me.bingka.unlimitedwaterbucket.utilities.CreateBucket;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player p) {
            if (p.hasPermission("unliwater.givebucket")) {
                if (args.length == 0) {
                    ItemStack bucket = CreateBucket.createUnlimitedWaterBucket();
                    ItemMeta bucketMeta = bucket.getItemMeta();
                    p.getInventory().addItem(bucket);
                    p.sendMessage("You have obtained " + bucketMeta.getDisplayName());
                } else {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    if (target == null) {
                        p.sendMessage(ChatColor.RED + "This player does not exist.");
                        return true; // Stops the function if the target is not a player
                    }

                    ItemStack bucket = CreateBucket.createUnlimitedWaterBucket();
                    ItemMeta bucketMeta = bucket.getItemMeta();
                    target.getInventory().addItem(bucket);
                    target.sendMessage("You have obtained " + bucketMeta.getDisplayName());
                }
            } else {
                p.sendMessage(ChatColor.RED + "You do not have permission to run this command.");
            }
        }

        return true;
    }
}

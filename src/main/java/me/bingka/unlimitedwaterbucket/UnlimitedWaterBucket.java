package me.bingka.unlimitedwaterbucket;

import me.bingka.unlimitedwaterbucket.commands.GiveCommand;
import me.bingka.unlimitedwaterbucket.listeners.WaterBucketListener;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class UnlimitedWaterBucket extends JavaPlugin {
    private static UnlimitedWaterBucket instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new WaterBucketListener(this), this);
        Objects.requireNonNull(getCommand("unliwater")).setExecutor(new GiveCommand());
        getLogger().info("UnlimitedWaterPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("UnlimitedWaterPlugin has been disabled!");
    }

    public static UnlimitedWaterBucket getInstance() {
        return instance;
    }

    // For the bucket's permanent metadata
    public NamespacedKey createKey(String key) {
        return new NamespacedKey(this, key);
    }
}

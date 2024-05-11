package me.bingka.unlimitedwaterbucket;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class UnlimitedWaterBucket extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WaterBucketListener(this), this);
        Objects.requireNonNull(getCommand("unliwater")).setExecutor(new Commands(this));
        getLogger().info("UnlimitedWaterPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("UnlimitedWaterPlugin has been disabled!");
    }
}

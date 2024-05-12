package me.bingka.unlimitedwaterbucket;

import me.bingka.unlimitedwaterbucket.commands.GiveCommand;
import me.bingka.unlimitedwaterbucket.listeners.WaterBucketListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class UnlimitedWaterBucket extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new WaterBucketListener(this), this);
        Objects.requireNonNull(getCommand("unliwater")).setExecutor(new GiveCommand());
        getLogger().info("UnlimitedWaterPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("UnlimitedWaterPlugin has been disabled!");
    }
}

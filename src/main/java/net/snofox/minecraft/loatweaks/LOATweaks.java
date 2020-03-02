package net.snofox.minecraft.loatweaks;

import org.bukkit.plugin.java.JavaPlugin;

public final class LOATweaks extends JavaPlugin {

    private static LOATweaks instance;

    public static LOATweaks getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new AppleListener(), this);
        getServer().getPluginManager().registerEvents(new ElytraListener(), this);
        getServer().getPluginManager().registerEvents(new EnderChestListener(), this);
        getServer().getPluginManager().registerEvents(new SlowfallNerfsListener(), this);
    }
}

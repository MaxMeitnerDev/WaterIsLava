package maxmeitner.waterislava;

import org.bukkit.plugin.java.JavaPlugin;

public final class WaterIsLava extends JavaPlugin {
    private static WaterIsLava plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    @Override
    public void onDisable() {}

    public static WaterIsLava getPlugin() {return plugin;}
}

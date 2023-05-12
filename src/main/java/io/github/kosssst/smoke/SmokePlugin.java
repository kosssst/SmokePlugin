package io.github.kosssst.smoke;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SmokePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("hookah")).setExecutor(new HookahCommand());
        getServer().getPluginManager().registerEvents(new HookahEvent(), this);
        Bukkit.getLogger().info("[SmokePlugin] Smoke Plugin enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[SmokePlugin] Smoke Plugin disabled");
    }
}

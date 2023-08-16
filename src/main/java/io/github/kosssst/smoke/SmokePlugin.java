package io.github.kosssst.smoke;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class SmokePlugin extends JavaPlugin {
    private static SmokePlugin instance;
    private final HashMap<Player, Boolean> diedFromHookah = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        Objects.requireNonNull(this.getCommand("hookah")).setExecutor(new HookahCommand());
        getServer().getPluginManager().registerEvents(new HookahEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathFromHookahEvent(), this);
        Bukkit.getLogger().info("[SmokePlugin] Smoke Plugin enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[SmokePlugin] Smoke Plugin disabled");
    }

    public static SmokePlugin getInstance() {
        return instance;
    }

    public HashMap<Player, Boolean> getDiedFromHookah() {
        return diedFromHookah;
    }

    public void addDiedFromHookah(Player player) {
        diedFromHookah.put(player, true);
    }

    public void removeDiedFromHookah(Player player) {
        diedFromHookah.remove(player);
    }
}

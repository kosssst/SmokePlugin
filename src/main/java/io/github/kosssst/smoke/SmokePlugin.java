package io.github.kosssst.smoke;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class SmokePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        Objects.requireNonNull(this.getCommand("hookah")).setExecutor(new HookahCommand());
        System.out.println("Plugin enabled");
    }

    @Override
    public void onDisable() {
        System.out.println("Plugin disabled");
    }
}

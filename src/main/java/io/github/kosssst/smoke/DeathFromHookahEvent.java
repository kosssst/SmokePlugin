package io.github.kosssst.smoke;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathFromHookahEvent implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (SmokePlugin.getInstance().getDiedFromHookah().containsKey(event.getPlayer())) {
            SmokePlugin.getInstance().removeDiedFromHookah(event.getPlayer());
            event.deathMessage(Component.text(event.getPlayer().getName() + " smoked to death"));
        }
    }
}

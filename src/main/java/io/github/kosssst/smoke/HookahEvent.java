package io.github.kosssst.smoke;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Objects;

public class HookahEvent implements Listener {

    @EventHandler
    public void HookahUsage(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            Player player = event.getPlayer();
            Block eventblock = event.getClickedBlock();
            assert eventblock != null;
            World world = eventblock.getWorld();
            Location location = eventblock.getLocation();
            location.set(location.getX() + 0.5, location.getY() + 0.5, location.getZ() + 0.5);
            location.setPitch(90);
            if (eventblock.getBlockData().getMaterial().equals(Material.BREWING_STAND)) {
                BrewingStand stand = null;
                if (eventblock.getState() instanceof BrewingStand) {
                    stand = (BrewingStand) eventblock.getState();
                } else {
                    throw new RuntimeException("failed to convert block to brewing stand");
                }
                BrewerInventory inv = stand.getInventory();
                if (Objects.equals(inv.getFuel(), new ItemStack(Material.POISONOUS_POTATO))) {
                    world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 1000);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 1, false, false));
                    event.setCancelled(true);
                }
            }
        }
    }
}

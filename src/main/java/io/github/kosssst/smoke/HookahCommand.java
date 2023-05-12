package io.github.kosssst.smoke;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.ItemStack;

import java.util.logging.Level;

public class HookahCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Block targetedBlock = player.getTargetBlockExact(4);
            if (targetedBlock == null) {
                player.sendMessage(Component.text("You aren't targeting any block"));
                return true;
            }
            if (targetedBlock.getBlockData().getMaterial().equals(Material.BREWING_STAND)) {
                BrewingStand stand = null;
                if (targetedBlock.getState() instanceof BrewingStand) {
                    stand = (BrewingStand) targetedBlock.getState();
                } else {
                    throw new RuntimeException("failed to convert block to brewing stand");
                }
                BrewerInventory inv = stand.getInventory();
                ItemStack fuel = inv.getFuel();
                if (fuel != null) {
                    if (fuel.getType() == Material.POISONOUS_POTATO) {
                        player.sendMessage(Component.text("Hookah already created"));
                        return true;
                    }
                }
                inv.setFuel(new ItemStack(Material.POISONOUS_POTATO, 1));
                player.sendMessage(Component.text("Hookah created"));
            } else {
                player.sendMessage(Component.text("This is not brewing stand"));
            }
        } else {
            Bukkit.getLogger().log(Level.SEVERE, "[SmokePlugin] This command can be used only by in-game players");
        }
        return true;
    }
}

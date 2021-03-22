package net.darkunscripted.DarkGenerators.events;

import net.darkunscripted.DarkGenerators.domain.Generator;
import net.darkunscripted.DarkGenerators.managers.GeneratorManager;
import net.darkunscripted.DarkGenerators.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class onBreak implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (GeneratorManager.getInstance().getLocations().containsKey(e.getBlock().getLocation())) {
            if (GeneratorManager.getInstance().getGeneratorOwners().containsKey(e.getBlock().getLocation())) {
                OfflinePlayer player = Bukkit.getOfflinePlayer(GeneratorManager.getInstance().getGeneratorOwners().get(e.getBlock().getLocation()));
                if (((Player) player).equals(e.getPlayer())) {
                    Generator generator = GeneratorManager.getInstance().getLocations().get(e.getBlock().getLocation());
                    GeneratorManager.getInstance().getLocations().remove(e.getBlock().getLocation());
                    GeneratorManager.getInstance().giveGenerator(generator, e.getPlayer());
                } else {
                    e.getPlayer().sendMessage(Utils.chat("&cThis is not your generator!"));
                }
            }
        }
    }

}

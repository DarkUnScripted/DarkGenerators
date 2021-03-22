package net.darkunscripted.DarkGenerators.events;

import net.darkunscripted.DarkGenerators.domain.Generator;
import net.darkunscripted.DarkGenerators.managers.GeneratorManager;
import net.darkunscripted.DarkGenerators.utils.Utils;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class onPlace implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(e.getItemInHand().hasItemMeta()){
            for(Generator generator : GeneratorManager.getInstance().getGenerators()){
                if(generator.getBlock().equals(e.getItemInHand().getType()) && generator.getName().equals(e.getItemInHand().getItemMeta().getDisplayName()) && e.getItemInHand().getItemMeta().getEnchants().containsKey(Enchantment.DURABILITY)){
                    Location location = e.getBlockPlaced().getLocation();
                    GeneratorManager.getInstance().getGeneratorOwners().put(location, e.getPlayer().getUniqueId());
                    GeneratorManager.getInstance().getLocations().put(location, generator);
                    e.getPlayer().sendMessage(Utils.chat("&b&lGenerators &7» " + generator.getName() + " has been placed!"));
                }
            }
        }
    }

}

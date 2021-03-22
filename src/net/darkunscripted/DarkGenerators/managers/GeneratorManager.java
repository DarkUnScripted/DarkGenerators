package net.darkunscripted.DarkGenerators.managers;

import net.darkunscripted.DarkGenerators.domain.Generator;
import net.darkunscripted.DarkGenerators.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.libs.it.unimi.dsi.fastutil.ints.IntSets;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class GeneratorManager {

    private static GeneratorManager gm = new GeneratorManager();
    private HashMap<Location, Generator> generatorLocation = new HashMap<Location, Generator>();
    private HashMap<Location, UUID> generatorOwner = new HashMap<>();
    private List<Generator> generatorTypes = new ArrayList<>();

    public static GeneratorManager getInstance(){
        return gm;
    }

    public Generator addLocation(Location location, Generator generator){
        return generatorLocation.put(location, generator);
    }

    public boolean removeLocation(Location location){
        try {
            generatorLocation.remove(location);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public HashMap<Location, Generator> getLocations(){
        return generatorLocation;
    }

    public HashMap<Location, UUID> getGeneratorOwners(){
        return generatorOwner;
    }

    public List<Generator> getGenerators() {
        return generatorTypes;
    }

    public Generator addGenerator(String name, ItemStack item, Material block){
        ItemStack generatorItem = new ItemStack(block, 1);
        ItemMeta generatorItemMeta = generatorItem.getItemMeta();
        generatorItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        generatorItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        generatorItemMeta.setDisplayName(name);
        generatorItem.setItemMeta(generatorItemMeta);
        Generator generator = new Generator(name, item, generatorItem);
        generatorTypes.add(generator);
        return generator;
    }

    public void giveGenerator(Generator generator, Player player){
        ItemStack generatorItem = new ItemStack(generator.getBlock(), 1);
        ItemMeta generatorItemMeta = generatorItem.getItemMeta();
        generatorItemMeta.addEnchant(Enchantment.DURABILITY, 1, true);
        generatorItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        generatorItemMeta.setDisplayName(Utils.chat(generator.getName()));
        generatorItem.setItemMeta(generatorItemMeta);
        if (player.getInventory().contains(Material.AIR)) {
            player.getInventory().addItem(generatorItem);
        } else {
            player.getLocation().getWorld().dropItemNaturally(player.getLocation(), generatorItem);
            player.sendMessage(Utils.chat("&cYour inventory is full!\nThe Generator has been dropped on the ground!"));
        }
    }

}

package net.darkunscripted.DarkGenerators.managers;

import net.darkunscripted.DarkGenerators.Main;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class PersistenceManager {

    private static Main plugin = Main.getPlugin(Main.class);

    public static void loadGeneratorTypes(){
        if(plugin.getConfig().contains("generators")){
            for(String generatorName : plugin.getConfig().getConfigurationSection("generators").getKeys(false)){
                String name = plugin.getConfig().getString("generators." + generatorName + ".name");
                Material material = Material.getMaterial(plugin.getConfig().getString("generators."+generatorName+".dropitem"));
                ItemStack dropitem = new ItemStack(material, 1);
                Material block = Material.getMaterial(plugin.getConfig().getString("generators."+generatorName+".blockitem"));
                GeneratorManager.getInstance().addGenerator(name, dropitem, block);
            }
        }
    }

}

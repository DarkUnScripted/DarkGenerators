package net.darkunscripted.DarkGenerators.listeners;

import net.darkunscripted.DarkGenerators.Main;
import net.darkunscripted.DarkGenerators.domain.Generator;
import net.darkunscripted.DarkGenerators.managers.GeneratorManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scheduler.BukkitRunnable;

public class GeneratorDropRunnable extends BukkitRunnable {

    private final Main plugin;

    public GeneratorDropRunnable(){
        this.plugin = Main.getPlugin(Main.class);
    }

    @Override
    public void run() {
        for(Location location : GeneratorManager.getInstance().getLocations().keySet()) {
            if (Bukkit.getOfflinePlayer(GeneratorManager.getInstance().getGeneratorOwners().get(location)).isOnline()) {
                Location droplocation = location;
                droplocation.setY(droplocation.getY() + 1);
                location.getWorld().dropItemNaturally(droplocation, GeneratorManager.getInstance().getLocations().get(location).getDrop());
            }
        }
    }
}

package net.darkunscripted.DarkGenerators;

import net.darkunscripted.DarkGenerators.commands.GeneratorCommand;
import net.darkunscripted.DarkGenerators.events.onBreak;
import net.darkunscripted.DarkGenerators.events.onJoin;
import net.darkunscripted.DarkGenerators.events.onPlace;
import net.darkunscripted.DarkGenerators.listeners.GeneratorDropRunnable;
import net.darkunscripted.DarkGenerators.managers.PersistenceManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerEvents();
        registerManagers();
        PersistenceManager.loadGeneratorTypes();
        GeneratorDropRunnable dropper = new GeneratorDropRunnable();
        dropper.runTaskTimer(this, 20L, 0L);
    }

    @Override
    public void onDisable() {

    }

    public void registerCommands(){
        getCommand("generator").setExecutor(new GeneratorCommand());
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new onBreak(), this);
        getServer().getPluginManager().registerEvents(new onJoin(), this);
        getServer().getPluginManager().registerEvents(new onPlace(), this);
    }

    public void registerManagers(){

    }

}

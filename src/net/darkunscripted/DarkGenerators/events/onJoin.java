package net.darkunscripted.DarkGenerators.events;

import net.darkunscripted.DarkGenerators.Main;
import net.darkunscripted.DarkGenerators.domain.PlayerProfile;
import net.darkunscripted.DarkGenerators.managers.ProfileManager;
import net.darkunscripted.DarkGenerators.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class onJoin implements Listener {

    Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        boolean check = true;
        for(PlayerProfile profile : ProfileManager.getInstance().getProfiles()){
            if(profile.getPlayer().equals(e.getPlayer().getUniqueId())){
                check = false;
            }
        }
        if(check){
            ProfileManager.getInstance().createProfile(e.getPlayer());
        }
        if(plugin.getConfig().contains("settings.toggles.disable-default-joinmessage")) {
            if(plugin.getConfig().getBoolean("settings.toggles.disable-default-joinmessage")) {
                e.setJoinMessage("");
                if (plugin.getConfig().contains("settings.messages.joinmessage")) {
                    e.setJoinMessage(Utils.chat(plugin.getConfig().getString("settings.messages.joinmessage")));
                }
            }
        }
    }

}

package net.darkunscripted.DarkGenerators.managers;

import net.darkunscripted.DarkGenerators.Main;
import net.darkunscripted.DarkGenerators.domain.PlayerProfile;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProfileManager {

    private final static ProfileManager pm = new ProfileManager();

    private List<PlayerProfile> profiles = new ArrayList<>();

    public static ProfileManager getInstance(){
        return pm;
    }

    public List<PlayerProfile> getProfiles() {
        return Collections.unmodifiableList(profiles);
    }

    public void setProfiles(List<PlayerProfile> profiles) {
        this.profiles = profiles;
    }

    public void addProfile(PlayerProfile profile){
        this.profiles.add(profile);
    }

    public PlayerProfile createProfile(Player player){
        int maxGens;
        if(Main.getPlugin(Main.class).getConfig().contains("settings.toggles.amount-per-player")){
            maxGens = Main.getPlugin(Main.class).getConfig().getInt("settings.toggles.amount-per-player");
        }else{
            maxGens = 25;
        }
        PlayerProfile profile = new PlayerProfile(player.getUniqueId(), maxGens);
        addProfile(profile);
        return profile;
    }
}

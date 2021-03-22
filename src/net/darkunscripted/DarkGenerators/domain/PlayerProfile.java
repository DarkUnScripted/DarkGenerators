package net.darkunscripted.DarkGenerators.domain;

import java.util.UUID;

public class PlayerProfile {

    private final UUID player;
    private int maxGens;

    public PlayerProfile(UUID player, int maxGens){
        this.player = player;
        this.maxGens = maxGens;
    }

    public UUID getPlayer() {
        return player;
    }

    public int getMaxGens() {
        return maxGens;
    }

    public void setMaxGens(int maxGens) {
        this.maxGens = maxGens;
    }
}

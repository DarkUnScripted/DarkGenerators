package net.darkunscripted.DarkGenerators.domain;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Generator {

    private final UUID id;
    private String name;
    private ItemStack drop;
    private Material block;

    public Generator(String name, ItemStack item, ItemStack block){
        this.id = UUID.randomUUID();
        this.name = name;
        this.drop = item;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ItemStack getDrop() {
        return drop;
    }

    public Material getBlock() {
        return block;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDrop(ItemStack drop) {
        this.drop = drop;
    }

    public void setBlock(Material block) {
        this.block = block;
    }
}

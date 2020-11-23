package com.github.pedrosodev.plugin.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class SumoWarp implements WarpsI{
    @Override
    public Material getIcon() {
        return Material.LEASH;
    }

    @Override
    public String getName() {
        return "Sumo";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(" ", "Faça combos incriveis.", " ");
    }

    @Override
    public void onJoin(Player player) {

    }
}

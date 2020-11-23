package com.github.pedrosodev.plugin.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class FishermanWarp implements WarpsI{
    @Override
    public Material getIcon() {
        return Material.FISHING_ROD;
    }

    @Override
    public String getName() {
        return "Fisherman";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(" ", "Treine sua abilidade com o fisherman.", " ");
    }

    @Override
    public void onJoin(Player player) {

    }
}

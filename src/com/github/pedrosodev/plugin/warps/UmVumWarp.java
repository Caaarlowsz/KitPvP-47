package com.github.pedrosodev.plugin.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class UmVumWarp implements WarpsI{
    @Override
    public Material getIcon() {
        return Material.BLAZE_ROD;
    }

    @Override
    public String getName() {
        return "1v1";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(" ", "Tire pvp sem medo de 2v1.", " ");
    }

    @Override
    public void onJoin(Player player) {

    }
}

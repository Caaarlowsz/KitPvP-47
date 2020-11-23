package com.github.pedrosodev.plugin.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class LavaWarp implements WarpsI{
    @Override
    public Material getIcon() {
        return Material.LAVA_BUCKET;
    }

    @Override
    public String getName() {
        return "Lava";
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(" ", "Treine seu refil e recraft.", " ");
    }

    @Override
    public void onJoin(Player player) {

    }
}

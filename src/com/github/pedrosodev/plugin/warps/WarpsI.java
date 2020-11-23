package com.github.pedrosodev.plugin.warps;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;

public interface WarpsI {

    Material getIcon();

    String getName();

    List<String> getDescription();

    void onJoin(Player player);

}

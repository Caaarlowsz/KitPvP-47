package com.github.pedrosodev.plugin.managers;

import com.github.pedrosodev.plugin.KitPvP;
import com.github.pedrosodev.plugin.enums.Warps;
import com.systemcore.pedrosogaymer.api.ConfigsAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import java.util.HashMap;
import java.util.Map;

public class WarpManager {
    static ConfigsAPI warpConfig = new ConfigsAPI(KitPvP.getJava(), "warps.yml");

    static Map<Player, Warps> playerWarpsMap = new HashMap<>();

    public static boolean existLoc(Warps warp){return warpConfig.get("Warps."+warp.name()) != null;}

    public static void saveWarp(Warps warp, Location location){
        warpConfig.setLocation("Warps."+warp.name(), location);
        warpConfig.saveConfig();
    }

    public static Location getWarpLoc (Warps warp){
        return warpConfig.getLocation("Warps."+warp.name());
    }

    public static void setPlayerWarp(Player player, Warps warp){
        playerWarpsMap.put(player, warp);
        if (existLoc(warp)){
            player.teleport(getWarpLoc(warp));
        }
        warp.getWarpsI().onJoin(player);
    }

    public static void setPlayerWarp(Player player, Warps warp, int timer){
        playerWarpsMap.put(player, warp);
        if (existLoc(warp)){
            Bukkit.getScheduler().scheduleSyncDelayedTask(KitPvP.getJava(), new Runnable() {
                @Override
                public void run() {

                    player.teleport(getWarpLoc(warp));
                }
            }, timer*20l);
        }
        warp.getWarpsI().onJoin(player);
    }

    public static Warps getPlayerWarp(Player player){
        return playerWarpsMap.get(player);
    }
}

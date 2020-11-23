package com.github.pedrosodev.plugin.commands.general;

import com.github.pedrosodev.plugin.enums.Warps;
import com.github.pedrosodev.plugin.managers.WarpManager;
import com.github.pedrosodev.plugin.score.Score;
import com.systemcore.pedrosogaymer.api.BanAPI.constructor.Ban;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.entity.Player;
import org.bukkit.material.Step;

import java.util.ArrayList;
import java.util.List;

public class WarpCommand extends CommandClass {

    @BukkitCommandFramework.Completer(name = "warp")
    public List<String> WarpCompleter(BukkitCommandFramework.CommandArgs cmd) {
        List<String> list = new ArrayList<>();
        for (Warps warp : Warps.values()) {
            if (warp.isUsable() && WarpManager.existLoc(warp))
                list.add(warp.name().substring(0, 1).toUpperCase()+warp.name().substring(1).toLowerCase());

        }
        return list;
    }

    @BukkitCommandFramework.Command(name = "warp", onlyPlayers = true)
    public void WarpComand(BukkitCommandFramework.CommandArgs cmd){
        Player player = cmd.getPlayer();
        String[] strings = cmd.getArgs();

        if (strings.length < 1){
            player.sendMessage(TextUtil.colorText("&b&lWarps &fuse /warp [warp]."));
            return;
        }

        String warpString = strings[0]
                .replace("1", "um")
                .toUpperCase();

        try {
            Warps warp = Warps.valueOf(warpString);
            if (!warp.isUsable()){
                player.sendMessage(TextUtil.colorText("&b&lWarps &fWarp não existente."));
                return;
            }

            if (!WarpManager.existLoc(warp)){
                player.sendMessage(TextUtil.colorText("&b&lWarps &fEssa warp ainda não foi setada."));
                return;
            }

            WarpManager.setPlayerWarp(player, warp);
            player.sendMessage(TextUtil.colorText("&b&lWarps &fVocê foi teleportado para a warp "+warp.getWarpsI().getName()+"."));

        }catch (Exception e){
            player.sendMessage(TextUtil.colorText("&b&lWarps &fWarp não existente."));
            return;
        }

    }

    @BukkitCommandFramework.Completer(name = "warpset", aliases = {"setwarp"})
    public List<String> WarpSetCompleter(BukkitCommandFramework.CommandArgs cmd) {
        List<String> list = new ArrayList<>();
        for (Warps warp : Warps.values()) {
            if (warp.isUsable())
                list.add(warp.name().substring(0, 1).toUpperCase()+warp.name().substring(1).toLowerCase());

        }
        return list;
    }

    @BukkitCommandFramework.Command(name = "warpset", aliases = {"setwarp"}, onlyPlayers = true)
    public void WarpSetComand(BukkitCommandFramework.CommandArgs cmd){
        Player player = cmd.getPlayer();
        String[] strings = cmd.getArgs();

        if (strings.length < 1){
            player.sendMessage(TextUtil.colorText("&b&lWarps &fuse /"+cmd.getLabel()+" [warp]."));
            return;
        }

        String warpString = strings[0]
                .replace("1", "um")
                .toUpperCase();

        try {
            Warps warp = Warps.valueOf(warpString);
            if (!warp.isUsable()){
                player.sendMessage(TextUtil.colorText("&b&lWarps &fWarp não existente."));
                return;
            }

            WarpManager.saveWarp(warp, player.getLocation());
            player.sendMessage(TextUtil.colorText("&b&lWarps &fVocê foi setou a warp "+warp.getWarpsI().getName()+"."));

        }catch (Exception e){
            player.sendMessage(TextUtil.colorText("&b&lWarps &fWarp não existente."));
            return;
        }

    }

    @BukkitCommandFramework.Command(name = "spawn", onlyPlayers = true)
    public void SpawnComand(BukkitCommandFramework.CommandArgs cmd){
        cmd.getPlayer().chat("/warp spawn");
    }

}

package com.github.pedrosodev.plugin.commands.ban;

import com.github.pedrosodev.plugin.utils.ConfigServer;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.PlayerUtils;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KickCommand extends CommandClass {

    @BukkitCommandFramework.Command(name = "kick", permission = "kitpvp.admin.kick")
    public void KickCommand(BukkitCommandFramework.CommandArgs cmd){
        CommandSender commandSender = cmd.getSender();
        String[] strings = cmd.getArgs();

        if (strings.length < 1 || strings.length > 2){
            commandSender.sendMessage(TextUtil.colorText("&c&lKICK &fUse /kick [jogador] [motivo]."));
            return;
        }


        String reason = "";
        if (strings.length > 0){
            for (int i = 1; i < strings.length; i++) {
                reason = reason + " " + strings[i];
            }
        }else {
            reason = "Sem Motivo";
        }

        if (!PlayerUtils.isOnline(strings[0])){
            commandSender.sendMessage(TextUtil.colorText("&c&lKICK &fEste jogador não está online neste momento."));
            return;
        }

        Player playerKicked = Bukkit.getPlayerExact(strings[0]);
        playerKicked.kickPlayer(ConfigServer.getKickMessage);


    }

}

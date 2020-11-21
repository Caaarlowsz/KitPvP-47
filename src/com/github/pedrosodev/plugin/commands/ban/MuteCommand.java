package com.github.pedrosodev.plugin.commands.ban;

import com.github.pedrosodev.plugin.KitPvP;
import com.systemcore.pedrosogaymer.api.BanAPI.constructor.Mute;
import com.systemcore.pedrosogaymer.api.DateAPI;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.PlayerUtils;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MuteCommand extends CommandClass {

    @BukkitCommandFramework.Command(name = "mute", permission = "kitpvp.admin.mute")
    public void MuteCommand(BukkitCommandFramework.CommandArgs cmd){
        CommandSender commandSender = cmd.getSender();
        String[] strings = cmd.getArgs();

        if (strings.length < 1) {
            commandSender.sendMessage(TextUtil.colorText("&c&lMUTE &fUse /mute [jogador] [Motivo]."));
            return;
        }

        String reason = "";
        if (strings.length > 1){
            for (int i = 1; i < strings.length; i++) {
                reason = reason + " " + strings[i];
            }
        }else {
            reason = "Sem Motivo";
        }
        String playerMutedName = strings[0];
        Player playerMuted = null;

        if (PlayerUtils.isOnline(playerMutedName))
            playerMuted = Bukkit.getPlayerExact(playerMutedName);

        if (KitPvP.getAccountManager().getUUID(playerMutedName) == null) {
            commandSender.sendMessage(TextUtil.colorText("&c&lMUTE &fJogador não encontrado!"));
            return;
        }

        if (playerMuted != null) {
            if (playerMuted.hasPermission("kitpvp.admin.tempban") ||
                    playerMuted.hasPermission("kitpvp.admin.ban") ||
                    playerMuted.hasPermission("kitpvp.admin.tempmute") ||
                    playerMuted.hasPermission("kitpvp.admin.mute") ||
                    playerMuted.hasPermission("kitpvp.admin.kick")) {
                commandSender.sendMessage(TextUtil.colorText("&c&lMUTE &fVocê não pode mutar um staff."));
                return;
            }
        }

        if (PlayerUtils.getPunishmentHistory(playerMutedName).getActualMute() != null && PlayerUtils.getPunishmentHistory(playerMutedName).getActualMute().isPermanent()) {
            commandSender.sendMessage(TextUtil.colorText("&c&lMUTE &fEste jogador já está mutado."));
            return;
        }

        Mute mute = new Mute(commandSender.getName(), reason);

        commandSender.sendMessage(TextUtil.colorText("&c&lMUTE &fVocê mutou o jogador " + playerMutedName + " pelo motivo " + reason + "."));
        KitPvP.getBanAPI().getBanManager().mute(playerMutedName, mute, true);

    }

    @BukkitCommandFramework.Command(name = "tempmute", permission = "kitpvp.admin.tempmute")
    public void TempMute(BukkitCommandFramework.CommandArgs cmd) {
        CommandSender commandSender = cmd.getSender();
        String[] strings = cmd.getArgs();

        if (strings.length < 2) {
            commandSender.sendMessage(TextUtil.colorText("&c&lTEMPMUTE &fUse /temmpmute [jogador] [tempo] [Motivo]."));
            return;
        }

        String reason = "";
        if (strings.length > 1){
            for (int i = 2; i < strings.length; i++) {
                reason = reason + " " + strings[i];
            }
        }else {
            reason = "Sem Motivo";
        }
        String playerMutedName = strings[0];
        Player playerMuted = null;
        Long expire = null;

        try {
            expire = DateAPI.getTime(strings[1]);
        } catch (Exception e) {
            commandSender.sendMessage("&c&lTEMPMUTE &fUse um formato de tempo valido (Exemplo: 30s, 1m, 2h, 5d, 2mo, 1y).");
            return;
        }

        if (PlayerUtils.isOnline(playerMutedName))
            playerMuted = Bukkit.getPlayerExact(playerMutedName);

        if (KitPvP.getAccountManager().getUUID(playerMutedName) == null) {
            commandSender.sendMessage(TextUtil.colorText("&c&lTEMPMUTE &fJogador não encontrado!"));
            return;
        }

        if (playerMuted != null) {
            if (playerMuted.hasPermission("kitpvp.admin.tempban") ||
                    playerMuted.hasPermission("kitpvp.admin.ban") ||
                    playerMuted.hasPermission("kitpvp.admin.tempmute") ||
                    playerMuted.hasPermission("kitpvp.admin.mute") ||
                    playerMuted.hasPermission("kitpvp.admin.kick")) {
                commandSender.sendMessage(TextUtil.colorText("&c&lTEMPMUTE &fVocê não pode mutar um staff."));
                return;
            }
        }

        Mute mute = new Mute(commandSender.getName(), reason, expire);

        commandSender.sendMessage(TextUtil.colorText("&c&lTEMPMUTE &fVocê mutou o jogador " + playerMutedName + " pelo motivo " + reason + " durante " + DateAPI.getTime(expire) + "."));
        KitPvP.getBanAPI().getBanManager().mute(playerMutedName, mute, true);

    }

    @BukkitCommandFramework.Command(name = "unmute", permission = "kitpvp.admin.unmute")
    public void UnMute(BukkitCommandFramework.CommandArgs cmd) {
        CommandSender commandSender = cmd.getSender();
        String[] strings = cmd.getArgs();

        if (strings.length < 1) {
            commandSender.sendMessage(TextUtil.colorText("&c&lUNMUTE &fUse /unmute [jogador]."));
            return;
        }

        String playerMutedName = strings[0];

        if (PlayerUtils.getPunishmentHistory(playerMutedName).getActualMute() == null) {
            commandSender.sendMessage(TextUtil.colorText("&c&lUNMUTE &O jogador " + playerMutedName + " não está mutado!"));
            return;
        }

        commandSender.sendMessage(TextUtil.colorText("&c&lUNMUTE &fVocê desmutou o jogador " + playerMutedName + "."));
        KitPvP.getBanAPI().getBanManager().unmute(playerMutedName, commandSender.getName(), true);

    }

}

package com.github.pedrosodev.plugin.commands.ban;

import com.github.pedrosodev.plugin.KitPvP;
import com.github.pedrosodev.plugin.player.Account;
import com.systemcore.pedrosogaymer.api.BanAPI.constructor.Ban;
import com.systemcore.pedrosogaymer.api.DateAPI;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.PlayerUtils;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BanCommand extends CommandClass {
    @BukkitCommandFramework.Command(name = "ban", permission = "kitpvp.admin.ban")
    public void BanCommand(BukkitCommandFramework.CommandArgs cmd){
        CommandSender commandSender = cmd.getSender();
        String[] strings = cmd.getArgs();

        if (strings.length < 1) {
            commandSender.sendMessage(TextUtil.colorText("&c&lBAN &fUse /ban [jogador] [Motivo]."));
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
        String playerBannedName = strings[0];
        Player playerBanned = null;
        UUID uuid = KitPvP.getAccountManager().getUUID(playerBannedName);

        if (PlayerUtils.isOnline(playerBannedName))
            playerBanned = Bukkit.getPlayerExact(playerBannedName);

        if (uuid == null) {
            commandSender.sendMessage(TextUtil.colorText("&c&lBAN &fJogador não encontrado!"));
            return;
        }

        Account playerBannedAccount = KitPvP.getAccountManager().loadAccount(uuid);

        if (playerBanned != null) {
            if (playerBanned.hasPermission("kitpvp.admin.tempban") ||
                    playerBanned.hasPermission("kitpvp.admin.ban") ||
                    playerBanned.hasPermission("kitpvp.admin.tempmute") ||
                    playerBanned.hasPermission("kitpvp.admin.mute") ||
                    playerBanned.hasPermission("kitpvp.admin.kick")) {
                commandSender.sendMessage(TextUtil.colorText("&c&lBAN &fVocê não pode banir um staff."));
                return;
            }
        }

        if (PlayerUtils.getPunishmentHistory(playerBannedName).getActualBan() != null && PlayerUtils.getPunishmentHistory(playerBannedName).getActualBan().isPermanent()) {
            commandSender.sendMessage(TextUtil.colorText("&c&lBAN &fEste jogador já está banido."));
            return;
        }

        Ban ban = new Ban(commandSender.getName(), reason);

        commandSender.sendMessage(TextUtil.colorText("&c&lBAN &fVocê baniu o jogador " + playerBannedName + " pelo motivo " + reason + "."));
        KitPvP.getBanAPI().getBanManager().ban(playerBannedName, ban, true);
        //Messages.Ban(player.getName(), playerBannedName, reason, null);

    }

    @BukkitCommandFramework.Command(name = "tempban", permission = "kitpvp.admin.tempban")
    public void TempBan(BukkitCommandFramework.CommandArgs cmd){
        CommandSender commandSender = cmd.getSender();
        String[] strings = cmd.getArgs();

        if (strings.length < 2) {
            commandSender.sendMessage(TextUtil.colorText("&c&lTEMPBAN &fUse /tempban [jogador] [tempo] [Motivo]."));
            return;
        }

        String reason = "";
        if (strings.length > 2){
            for (int i = 2; i < strings.length; i++) {
                reason = reason + " " + strings[i];
            }
        }else {
            reason = "Sem Motivo";
        }
        String playerBannedName = strings[0];
        Player playerBanned = null;
        Long expire = null;

        try {
            expire = DateAPI.getTime(strings[1]);
        } catch (Exception e) {
            commandSender.sendMessage("&c&lTEMPBAN &fUse um formato de tempo valido (Exemplo: 30s, 1m, 2h, 5d, 2mo, 1y).");
            return;
        }

        if (PlayerUtils.isOnline(playerBannedName))
            playerBanned = Bukkit.getPlayerExact(playerBannedName);

        if (KitPvP.getAccountManager().getUUID(playerBannedName) == null) {
            commandSender.sendMessage(TextUtil.colorText("&c&lTEMPBAN &fJogador não encontrado!"));
            return;
        }

        if (playerBanned != null) {
            if (playerBanned.hasPermission("kitpvp.admin.tempban") ||
                    playerBanned.hasPermission("kitpvp.admin.ban") ||
                    playerBanned.hasPermission("kitpvp.admin.tempmute") ||
                    playerBanned.hasPermission("kitpvp.admin.mute") ||
                    playerBanned.hasPermission("kitpvp.admin.kick")) {
                commandSender.sendMessage(TextUtil.colorText("&c&lTEMPBAN &fVocê não pode banir um staff."));
                return;
            }
        }

        Ban ban = new Ban(commandSender.getName(), reason, expire);

        commandSender.sendMessage(TextUtil.colorText("&c&lTEMPBAN &fVocê baniu o jogador " + playerBannedName + " pelo motivo " + reason + " durante " + DateAPI.getTime(expire) + "."));
        KitPvP.getBanAPI().getBanManager().ban(playerBannedName, ban, true);
        //Messages.Ban(player.getName(), playerBannedName, reason, DateAPI.getTime(expire));

    }

    @BukkitCommandFramework.Command(name = "unban", permission = "kitpvp.admin.unban")
    public void UnBanCommand(BukkitCommandFramework.CommandArgs cmd){
        CommandSender commandSender = cmd.getSender();
        String[] strings = cmd.getArgs();

        if (strings.length < 1) {
            commandSender.sendMessage(TextUtil.colorText("&c&lUNBAN &fUse /unban [jogador]."));
            return;
        }

        String playerBannedName = strings[0];

        if (PlayerUtils.getPunishmentHistory(playerBannedName).getActualBan() == null) {
            commandSender.sendMessage(TextUtil.colorText("&c&lUNBAN &O jogador " + playerBannedName + " não está banido!"));
            return;
        }

        commandSender.sendMessage(TextUtil.colorText("&c&lUNBAN &fVocê desbaniu o jogador " + playerBannedName + "."));
        KitPvP.getBanAPI().getBanManager().unban(playerBannedName, commandSender.getName(), true);

    }
}

package com.github.pedrosodev.plugin.commands;

import com.github.pedrosodev.plugin.KitPvP;
import com.github.pedrosodev.plugin.player.Account;
import com.systemcore.pedrosogaymer.api.BanAPI.constructor.Ban;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AccountCommand extends CommandClass {

    @BukkitCommandFramework.Command(name = "account")
    public void Teste2Command(BukkitCommandFramework.CommandArgs cmd){
        CommandSender player = cmd.getSender();
        String[] strings = cmd.getArgs();

        UUID uuid = KitPvP.getAccountManager().getUUID(player.getName());

        if (strings.length > 0){

            uuid = KitPvP.getAccountManager().getUUID(strings[0]);

        }

        if (uuid != null){
            Account account = KitPvP.getAccountManager().loadAccount(uuid);
            player.sendMessage(TextUtil.colorText("-=-=-=-=-=-=-=(*)=-=-=-=-=-=-=-"));
            player.sendMessage(TextUtil.colorText("&bUUID: &f"+account.getUuid()));
            player.sendMessage(TextUtil.colorText("&bName: &f"+account.getName()));
            player.sendMessage(TextUtil.colorText("&bGroup: &f"+account.getGroup()));
            player.sendMessage(TextUtil.colorText("&bRank: &f"+account.getRank()));
            player.sendMessage(TextUtil.colorText("&bHigh KillStreak: &f"+account.getHighKillStreak()));
            player.sendMessage(TextUtil.colorText("&bCoins: &f"+account.getCoins()));
            player.sendMessage(TextUtil.colorText("&bXP: &f"+account.getXP()));
            player.sendMessage(TextUtil.colorText("-=-=-=-=-=-=-=(*)=-=-=-=-=-=-=-"));
        }else{
            player.sendMessage(TextUtil.colorText("&eAccount &fEssa conta não existe em nosso Banco de Dados!"));
        }

    }

}

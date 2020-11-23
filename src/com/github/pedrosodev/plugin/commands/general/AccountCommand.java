package com.github.pedrosodev.plugin.commands.general;

import com.github.pedrosodev.plugin.KitPvP;
import com.github.pedrosodev.plugin.player.Account;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class AccountCommand extends CommandClass {

    @BukkitCommandFramework.Command(name = "account")
    public void AccountCommand(BukkitCommandFramework.CommandArgs cmd){
        CommandSender player = cmd.getSender();
        String[] strings = cmd.getArgs();

        UUID uuid = KitPvP.getAccountManager().getUUID(player.getName());

        if (strings.length > 0){

            uuid = KitPvP.getAccountManager().getUUID(strings[0]);

        }

        if (uuid != null){
            Account account = KitPvP.getAccountManager().getAccount(uuid);
            player.sendMessage(TextUtil.colorText("-=-=-=-=-=-=-=(*)=-=-=-=-=-=-=-"));
            player.sendMessage(TextUtil.colorText(" &bUUID: &f"+account.getUuid().toString()));
            player.sendMessage(TextUtil.colorText(" &bName: &f"+account.getName()));
            player.sendMessage(TextUtil.colorText(" &bGroup: &f"+account.getGroup().getCorTag()+account.getGroup().getTagName()));
            player.sendMessage(TextUtil.colorText(" &bRank: &f"+"&7("+account.getRank().getColor()+account.getRank().getSymbol()+"&7) "+account.getRank().getColor()+account.getRank().name()));
            player.sendMessage(TextUtil.colorText(" &bHigh KillStreak: &f"+account.getHighKillStreak()));
            player.sendMessage(TextUtil.colorText(" &bKills: &f"+account.getKills()));
            player.sendMessage(TextUtil.colorText(" &bDeaths: &f"+account.getDeaths()));
            player.sendMessage(TextUtil.colorText(" &bCoins: &f"+account.getCoins()));
            player.sendMessage(TextUtil.colorText(" &bXP: &f"+account.getXP()));
            player.sendMessage(TextUtil.colorText("-=-=-=-=-=-=-=(*)=-=-=-=-=-=-=-"));
        }else{
            player.sendMessage(TextUtil.colorText("&eAccount &fEssa conta não existe em nosso Banco de Dados!"));
        }

    }

}

package com.github.pedrosodev.plugin.commands.login;

import com.github.pedrosodev.plugin.KitPvP;
import com.systemcore.pedrosogaymer.api.Login.check.Check;
import com.systemcore.pedrosogaymer.api.Login.database.SQLManager;
import com.systemcore.pedrosogaymer.api.Login.exception.InvalidCheckException;
import com.systemcore.pedrosogaymer.api.Login.util.Util;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.entity.Player;

public class LoginCommand extends CommandClass {

    @BukkitCommandFramework.Command(name = "login", onlyPlayers = true)
    public void Login(BukkitCommandFramework.CommandArgs cmd){
        Player player = cmd.getPlayer();
        String[] strings = cmd.getArgs();

        try {
            if (Check.fastCheck(player.getName())) {
                player.sendMessage(TextUtil.colorText("&b&lLOGIN &fVocê é um jogador original."));
                return;
            }
        } catch (InvalidCheckException e) {
            e.printStackTrace();
        }

        if (!KitPvP.getLoginAPI().getSQLManager().hasOnDatabase(player.getName())) {
            player.sendMessage(TextUtil.colorText("&b&lREGISTER &fVocê não se registrou ainda."));
            player.sendMessage(TextUtil.colorText("&b&lREGISTER &fUse /register [senha] [senha]."));
            return;
        }

        if (!KitPvP.getLoginAPI().getStorage().needLogin(player.getName())){
            player.sendMessage(TextUtil.colorText("&b&lLOGIN &fVocê já está logado."));
            return;
        }

        if (strings.length == 0) {
            player.sendMessage(TextUtil.colorText("&b&lLOGIN &fUse /login [senha]."));
        } else {
            if (Util.decode(KitPvP.getLoginAPI().getSQLManager().getPassword(player.getName())).equals(strings[0])) {
                while (KitPvP.getLoginAPI().getStorage().needLogin(player.getName()))
                    KitPvP.getLoginAPI().getStorage().removeNeedLogin(player.getName());

                player.sendMessage(TextUtil.colorText("&b&lLOGIN &fVocê se logou com sucesso."));
            } else {
                player.sendMessage(TextUtil.colorText("&b&lLOGIN &fSua senha está incorreta."));
            }
        }
    }

    @BukkitCommandFramework.Command(name = "register", onlyPlayers = true)
    public void RegisterCommand(BukkitCommandFramework.CommandArgs cmd){
        Player player = cmd.getPlayer();
        String[] strings = cmd.getArgs();

        try {
            if (Check.fastCheck(player.getName())) {
                player.sendMessage(TextUtil.colorText("&b&lLOGIN &fVocê é um jogador original."));
                return;
            }
        } catch (InvalidCheckException e) {
            e.printStackTrace();
        }

        if (KitPvP.getLoginAPI().getSQLManager().hasOnDatabase(player.getName())) {
            player.sendMessage(TextUtil.colorText("&b&lREGISTER &fVocê já se registrou."));
            player.sendMessage(TextUtil.colorText("&b&lLOGIN &fUse /login [senha]."));
            return;
        }

        if (strings.length < 2 || strings.length > 3) {
            player.sendMessage(TextUtil.colorText("&b&lREGISTER &fUse /register [senha] [senha]."));
        } else {
            if (!strings[0].equals(strings[1])) {
                player.sendMessage(TextUtil.colorText("&b&lREGISTER &fAs senhas não são identicas."));
                return;
            }

            while (KitPvP.getLoginAPI().getStorage().needLogin(player.getName()))
                KitPvP.getLoginAPI().getStorage().removeNeedLogin(player.getName());

            KitPvP.getLoginAPI().getSQLManager().setPasswordAndStatus(player.getName(), SQLManager.Status.CRACKED, new String(Util.encode(strings[0])));

            player.sendMessage(TextUtil.colorText("&b&lREGISTER &fVocê se registrou com sucesso."));
        }

    }


}

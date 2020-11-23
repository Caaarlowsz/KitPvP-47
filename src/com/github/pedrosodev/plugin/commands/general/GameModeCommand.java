package com.github.pedrosodev.plugin.commands.general;

import com.systemcore.pedrosogaymer.api.BanAPI.constructor.Ban;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class GameModeCommand extends CommandClass {

    @BukkitCommandFramework.Completer(name = "gamemode", aliases = {"gm"})
    public List<String> Teste1Command(BukkitCommandFramework.CommandArgs cmd){
        return Arrays.asList("0", "1", "Creative", "Survival");
    }

    @BukkitCommandFramework.Command(name = "gamemode", aliases = {"gm"}, permission = "kitpvp.admin.gamemode", onlyPlayers = true)
    public void Teste2Command(BukkitCommandFramework.CommandArgs cmd){
        Player player = cmd.getPlayer();
        String[] strings = cmd.getArgs();

        if (strings.length < 1){
            player.sendMessage(TextUtil.colorText("&b&lGame&f&lmode &fUse /"+cmd.getLabel()+" [0, 1, Creative, Survival]."));
            return;
        }

        if (strings[0].equalsIgnoreCase("Creative") || strings[0].equalsIgnoreCase("1")){
            if (player.getGameMode() != GameMode.CREATIVE){
                player.setGameMode(GameMode.CREATIVE);
                player.sendMessage(TextUtil.colorText("&b&lGame&f&lmode &fSeu modo de jogo foi alterado para Criativo."));
            }else {
                player.sendMessage(TextUtil.colorText("&b&lGame&f&lmode &fVocê já está neste modo de jogo."));
            }
            return;
        }else if (strings[0].equalsIgnoreCase("Survival") || strings[0].equalsIgnoreCase("0")){
            if (player.getGameMode() != GameMode.ADVENTURE){
                player.setGameMode(GameMode.ADVENTURE);
                player.sendMessage(TextUtil.colorText("&b&lGame&f&lmode &fSeu modo de jogo foi alterado para Sobrevivencia."));
            }else {
                player.sendMessage(TextUtil.colorText("&b&lGame&f&lmode &fVocê já está neste modo de jogo."));
            }
            return;
        }else{
            player.sendMessage(TextUtil.colorText("&b&lGame&f&lmode &fModo de jogo não existente."));
            return;
        }

    }

}

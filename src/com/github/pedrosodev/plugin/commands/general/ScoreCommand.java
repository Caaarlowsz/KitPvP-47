package com.github.pedrosodev.plugin.commands.general;

import com.github.pedrosodev.plugin.score.Score;
import com.systemcore.pedrosogaymer.api.BanAPI.constructor.Ban;
import com.systemcore.pedrosogaymer.api.ScoreAPI;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import javafx.application.Platform;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ScoreCommand extends CommandClass {

    @BukkitCommandFramework.Command(name = "score", aliases = {"scoreboard"}, onlyPlayers = true)
    public void ScoreCommand(BukkitCommandFramework.CommandArgs cmd){
        Player player = cmd.getPlayer();

        if (Score.hideScore.contains(player)){
            player.sendMessage(TextUtil.colorText("&b&lScore&f&lboard &fA scoreboard foi desabilitada."));
            ScoreAPI.remove(player);
            Score.hideScore.remove(player);
        }else {
            player.sendMessage(TextUtil.colorText("&b&lScore&f&lboard &fA scoreboard foi habilitada."));
            Score.updateScore(player);
            Score.hideScore.add(player);
        }

    }

}

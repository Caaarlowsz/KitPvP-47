package com.github.pedrosodev.plugin.score;

import com.github.pedrosodev.plugin.KitPvP;
import com.github.pedrosodev.plugin.player.Account;
import com.github.pedrosodev.plugin.utils.ConfigServer;
import com.systemcore.pedrosogaymer.api.ScoreAPI;
import com.systemcore.pedrosogaymer.utils.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Score {

    public static List<Player> hideScore = new ArrayList<>();

    public static void updateScore(Player player){
        if (hideScore.contains(player)) return;

        String[] site = Utils.divText(ConfigServer.getServerSite);

        UUID uuid = KitPvP.getAccountManager().getUUID(player.getName());
        Account account = KitPvP.getAccountManager().getAccount(uuid);

        ScoreAPI score = new ScoreAPI(ConfigServer.getServerName, "spawn");
        score.blankLine();
        score.addLine(" &fGroup: ",account.getGroup().getCorTag()+account.getGroup().getTagName());
        score.addLine(" &fRank: ", account.getRank().getColor() + account.getRank().getSymbol() + " " + account.getRank().name());
        score.blankLine();
        score.addLine(" &fKillStreak: &b", account.getKillStreak());
        score.addLine(" &fKills: &b", account.getKills());
        score.addLine(" &fDeaths: &b", account.getDeaths());
        score.blankLine();
        score.addLine(" &fCoins: &b", account.getCoins());
        score.addLine(" &fXP: &b", account.getXP());
        score.blankLine();
        score.addLine(site[0], site[1]);
        score.update(player);

    }

}

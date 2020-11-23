package com.github.pedrosodev.plugin.listener;

import com.github.pedrosodev.plugin.KitPvP;
import com.github.pedrosodev.plugin.enums.Groups;
import com.github.pedrosodev.plugin.enums.Warps;
import com.github.pedrosodev.plugin.managers.WarpManager;
import com.github.pedrosodev.plugin.player.Account;
import com.github.pedrosodev.plugin.score.Score;
import com.systemcore.pedrosogaymer.api.TagAPI;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (KitPvP.getAccountManager().loadAccount(uuid) == null){
            System.out.println("[Account] Criando uma nova conta ao jogador "+player.getName()+".");
            Account newAcount = new Account(player);
            KitPvP.getAccountManager().loadAccount(uuid, newAcount);
            KitPvP.getAccountManager().saveAccount(newAcount);
        }else {
            System.out.println("[Account] A conta do jogador "+player.getName()+" foi carregada com sucesso.");
        }

        Account account = KitPvP.getAccountManager().loadAccount(uuid);

        player.setGameMode(GameMode.ADVENTURE);

        event.setJoinMessage(null);

        Score.updateScore(player);

        TagAPI.setTag(player,
                (account.getTag() == Groups.MEMBRO ? "&7" : account.getTag().getCorTag()+account.getTag().getTagName()+account.getTag().getCor()+" "),
                " &7("+account.getRank().getColor()+account.getRank().getSymbol()+"&7)",
                account.getTag().getOrder()+account.getRank().getOrder());

        if (WarpManager.existLoc(Warps.SPAWN)){
            WarpManager.setPlayerWarp(player, Warps.SPAWN);
        }else {
            player.sendMessage(TextUtil.colorText("&b&lWarps &fA warp spawn ainda não foi setada."));
        }

        Warps.SPAWN.getWarpsI().onJoin(player);
    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event){
        event.setQuitMessage(null);
        Account account = KitPvP.getAccountManager().getAccount(event.getPlayer().getUniqueId());
        KitPvP.getAccountManager().saveAccount(account);
        KitPvP.getAccountManager().unloadAccount(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onChangeFood(FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

    @EventHandler
    public void onChanWeather(WeatherChangeEvent event){
        event.setCancelled(true);
    }
}

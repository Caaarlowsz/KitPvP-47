package com.github.pedrosodev.plugin.listener;

import com.github.pedrosodev.plugin.KitPvP;
import com.github.pedrosodev.plugin.player.Account;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event){
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (KitPvP.getAccountManager().loadAccount(player.getUniqueId()) == null){
            System.out.println("[Account] Criando uma nova conta ao jogador "+player.getName()+".");
            Account newAcount = new Account(player);
            KitPvP.getAccountManager().loadAccount(uuid, newAcount);
            KitPvP.getAccountManager().saveAccount(newAcount);
        }else {
            System.out.println("[Account] A conta do jogador "+player.getName()+" foi carregada com sucesso.");
        }

        

    }
}

package com.github.pedrosodev.plugin.player;

import com.github.pedrosodev.plugin.enums.Groups;
import com.github.pedrosodev.plugin.enums.Ranks;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Account {

    int kill, deaths, killStreak, highKillStreak, coins, xp;
    String name, ip;
    UUID uuid;
    Ranks rank;
    Groups group, tag;

    public Account(Player player) {
        this.kill = 0;
        this.deaths = 0;
        this.killStreak = 0;
        this.highKillStreak = 0;
        this.coins = 0;
        this.xp = 0;
        this.name = player.getName();
        this.uuid = player.getUniqueId();
        this.rank = Ranks.UNRANKED;
        this.group = Groups.MEMBRO;
        this.tag = Groups.MEMBRO;
        this.ip = player.getAddress().getHostName();
    }

    public String getIp() {
        return ip;
    }

    public Ranks getRank() {
        return rank;
    }

    public void setRank(Ranks rank) {
        this.rank = rank;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Groups getTag() {
        return tag;
    }

    public void setTag(Groups tag) {
        this.tag = tag;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public int getKills() {
        return kill;
    }

    public void setKills(int kill) {
        this.kill = kill;
    }

    public void addKills(int amount) {
        setKills(getKills()+amount);
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void addDeaths(int amount) {
        setDeaths(getDeaths() + amount);
    }

    public int getKillStreak() {
        return killStreak;
    }

    public void setKillStreak(int killStreak) {
        this.killStreak = killStreak;
    }

    public void addKillStreak(int amount) {
        setKillStreak(getKillStreak()+amount);
        if (getKillStreak() > getHighKillStreak()){
            setHighKillStreak(getKillStreak());
        }
    }

    public int getHighKillStreak() {
        return highKillStreak;
    }

    private void setHighKillStreak(int highKillStreak) {
        this.highKillStreak = highKillStreak;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public void addCoins(int amount) {setCoins(getCoins()+amount);}

    public void removeCoins(int amount) {setCoins(getCoins()-amount);}

    public int getXP() {
        return xp;
    }

    public void setXP(int xp) {
        this.xp = xp;
    }

    public void addXP(int amount) {setXP(getXP()+amount);}

    public void removeXP(int amount) {setXP(getXP()-amount);}

}

package com.github.pedrosodev.plugin.enums;

import com.systemcore.pedrosogaymer.utils.TextUtil;

public enum Ranks {

    UNRANKED("-", "&f", 0, "j"),
    NOVATO("=", "&a", 600, "i"),
    APRENDIZ("☲", "&e", 1300, "h"),
    EXPERIENTE("✹", "&5", 2400, "g"),
    VETERANO("✻", "&5", 3500, "f"),
    RYPPER("❃", "&c", 4900, "e"),
    RYZEN("✦", "&b", 6000, "d"),
    COLOSSUS("✺", "&5", 8100, "c"),
    DRAGON("❆", "&0", 12000, "b"),
    DEMON("❁", "&3", 18000, "a");

    String symbol, color, order;
    int xp;

    Ranks(String symbol, String color, int xp, String order) {
        this.symbol = symbol;
        this.color = color;
        this.xp = xp;
        this.order = order;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getColor() {
        return TextUtil.colorText(color);
    }

    public int getXp() {
        return xp;
    }

    public String getOrder() {
        return order;
    }

    public Ranks getNextRank(){

        Ranks nextRank = null;
        for (Ranks rank : Ranks.values()){
            if (rank.ordinal() == (ordinal()+1)){
                nextRank = rank;
            }
        }

        return nextRank;
    }
}

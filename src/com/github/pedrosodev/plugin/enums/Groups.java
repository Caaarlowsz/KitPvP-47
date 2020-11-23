package com.github.pedrosodev.plugin.enums;

import com.systemcore.pedrosogaymer.utils.TextUtil;

public enum Groups {

    DEV("DEV", "&9", "a"),
    DONO("DONO", "&4", "b"),
    DIRETOR("DIRETOR", "&c", "c"),
    ADMIN("ADMIN", "&c", "d"),
    GERENTE("GERENTE", "&1", "e"),
    MODERADORPLUS("MOD+", "&5", "f"),
    MODERADORGC("MODGC", "&5", "g"),
    TRIALGC("TRIALGC", "&d", "h"),
    MODERADOR("MOD", "&5", "i"),
    TRIAL("TRIAL", "&d", "j"),
    BUILDER("BUILDER", "&e", "k"),
    HELPER("HELPER", "&9", "l"),
    DESIGNER("DESIGNER", "&1", "m"),
    YOUTUBERPLUS("YT+", "&b", "n"),
    YOUTUBER("YT", "&b", "o"),
    MINIYOUTUBER("MINIYT", "&b", "p"),
    BETA("BETA", "&1", "q"),
    KING("KING", "&e", "r"),
    MAGE("MAGE", "&6", "s"),
    WARRIOR("WARRIOR", "&a", "t"),
    TOP1("TOP1", "&a", "u"),
    TOP2("TOP2", "&e", "v"),
    TOP3("TOP3", "&c", "w"),
    MEMBRO("MEMBRO", "&7", "x");

    private String tag;
    private String cor;
    private String ordem;

    Groups(String tag, String cor, String ordem){

        this.tag = tag;
        this.cor = cor;
        this.ordem = ordem;

    }

    public String getTagName() {
        return TextUtil.colorText(tag);
    }

    public String getCor() {
        return TextUtil.colorText(cor);
    }

    public String getOrder() {
        return TextUtil.colorText(ordem);
    }

    public String getCorTag() {
        return TextUtil.colorText(cor + "&l");
    }
}

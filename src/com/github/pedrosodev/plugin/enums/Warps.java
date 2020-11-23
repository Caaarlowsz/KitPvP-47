package com.github.pedrosodev.plugin.enums;

import com.github.pedrosodev.plugin.warps.*;

public enum Warps {

    SPAWN(new SpawnWarp(), false, true),
    UMVUM(new UmVumWarp(), true, true),
    SUMO(new SumoWarp(), true, true),
    FISHERMAN(new FishermanWarp(), true, true),
    FPS(new FpsWarp(), true, true),
    LAVA(new LavaWarp(), true, true);
    WarpsI warpsI;
    boolean showInMenu, isUsable;

    Warps(WarpsI warpsI, boolean showInMenu, boolean isUsable) {
        this.warpsI = warpsI;
        this.showInMenu = showInMenu;
        this.isUsable = isUsable;
    }

    public WarpsI getWarpsI() {
        return warpsI;
    }

    public boolean isShowInMenu() {
        return showInMenu;
    }

    public boolean isUsable() {
        return isUsable;
    }
}

package com.github.pedrosodev.plugin.inventory;

import com.github.pedrosodev.plugin.enums.Warps;
import com.github.pedrosodev.plugin.warps.WarpsI;
import com.systemcore.pedrosogaymer.utils.MakeItem;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import com.systemcore.pedrosogaymer.utils.inventoryGUI.buttons.ItemButton;
import com.systemcore.pedrosogaymer.utils.inventoryGUI.menus.InventoryGUI;
import com.systemcore.pedrosogaymer.utils.inventoryGUI.menus.PaginatedGUI;
import javafx.print.PageLayout;
import org.bukkit.Material;
import org.bukkit.entity.LeashHitch;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;

import java.util.Arrays;
import java.util.List;

public class SpawnInventorys {

    static void LayoutWarps(InventoryGUI inv){

        List<Integer> slotsBlueGlass = Arrays.asList(0, 1, 2, 3, 5, 6, 7, 8, 9, 17, 18, 19, 20, 21, 23, 24, 25, 26);

        MakeItem item;
        item = new MakeItem(Material.STAINED_GLASS_PANE);
        item.setDurability((short)11);
        item.setName(" ");
        for (int slot : slotsBlueGlass){
            inv.setButton(slot, new ItemButton(item.build()));
        }
        item = new MakeItem(Material.LONG_GRASS);
        item.setDurability((short)2);
        item.setName(" ");
        inv.setButton(4, new ItemButton(item.build()));
        inv.setButton(10, new ItemButton(item.build()));
        inv.setButton(16, new ItemButton(item.build()));
        inv.setButton(22, new ItemButton(item.build()));
    }

    public static void WarpsInv(Player player){

        List<Integer> slotsWarps = Arrays.asList(11, 12, 13, 14, 15);

        InventoryGUI warpInv = new InventoryGUI("&bWarps", 3*9);
        MakeItem item;
        ItemButton itemButton;
        LayoutWarps(warpInv);
        int counter = 0;
        for (Warps warp : Warps.values()){
            if (warp.isShowInMenu()){
                WarpsI warpsI = warp.getWarpsI();
                item = new MakeItem(warpsI.getIcon());
                item.setName("&a"+ warpsI.getName());
                item.setLore(TextUtil.colorTextList("&7", warpsI.getDescription()));
                itemButton = new ItemButton(item.build());
                itemButton.addAction(ClickType.LEFT, inventoryClickEvent -> {
                   player.chat("/warp "+warp.name());
                });
                warpInv.setButton(slotsWarps.get(counter), itemButton);
                counter++;
            }
        }

        warpInv.show(player);

    }

    static void LayoutKits(InventoryGUI inv){
        List<Integer> slotsBlueGlass = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 15, 16, 17, 18, 26,
                27, 31, 35, 36, 37, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53);
        MakeItem item;
        item = new MakeItem(Material.STAINED_GLASS_PANE);
        item.setDurability((short)11);
        item.setName(" ");
        for (int slot : slotsBlueGlass){
            inv.setButton(slot, new ItemButton(item.build()));
        }
    }

    public static void KitsInv(Player player){

        List<Integer> slotsKits = Arrays.asList(12, 13, 14, 19, 20, 21, 22, 23, 24, 25, 28, 29,
                30, 32, 33, 34, 38, 42);

        PaginatedGUI kitsInv = new PaginatedGUI();
        MakeItem item;
        ItemButton itemButton;

        InventoryGUI kitsPag1 = new InventoryGUI("&bKits Pag. 1", 6*9);
        LayoutKits(kitsPag1);
        item = new MakeItem(Material.LONG_GRASS);
        item.setDurability((short)2);
        item.setName("&aVoltar Pag.");
        itemButton = new ItemButton(item.build());
        itemButton.addAction(ClickType.LEFT, inventoryClickEvent -> {
           kitsInv.showPrevious(player);
        });
        kitsPag1.setButton(0, itemButton);
        item.setName("&aProxima Pag.");
        itemButton = new ItemButton(item.build());
        itemButton.addAction(ClickType.LEFT, inventoryClickEvent -> {
            kitsInv.showNext(player);
        });
        kitsPag1.setButton(8, itemButton);

        kitsInv.addPage(kitsPag1);

        kitsInv.show(player);

    }

    static void LayoutUtilits(InventoryGUI inv){
        List<Integer> slotsBlueGlass = Arrays.asList(0, 1, 4, 7, 8, 9, 17, 18, 26, 27, 35, 36, 44,
            45, 46, 49, 52, 53);
        List<Integer> slotsWhiteGlass = Arrays.asList(2, 3, 5, 6, 10, 11, 15, 16, 19, 25, 28, 34,
                37, 38, 42, 43, 47, 48, 50, 51);
        MakeItem item;
        item = new MakeItem(Material.STAINED_GLASS_PANE);
        item.setDurability((short)11);
        item.setName(" ");
        for (int slot : slotsBlueGlass){
            inv.setButton(slot, new ItemButton(item.build()));
        }

        item.setDurability((short)0);
        for (int slot : slotsWhiteGlass){
            inv.setButton(slot, new ItemButton(item.build()));
        }

        item = new MakeItem(Material.IRON_FENCE);
        item.setName(" ");
        inv.setButton(12, new ItemButton(item.build()));
        inv.setButton(14, new ItemButton(item.build()));
        inv.setButton(20, new ItemButton(item.build()));
        inv.setButton(24, new ItemButton(item.build()));
        inv.setButton(29, new ItemButton(item.build()));
        inv.setButton(33, new ItemButton(item.build()));
        inv.setButton(39, new ItemButton(item.build()));
        inv.setButton(41, new ItemButton(item.build()));


        item = new MakeItem(Material.NETHER_STAR);
        item.setName(" ");
        inv.setButton(13, new ItemButton(item.build()));
        inv.setButton(40, new ItemButton(item.build()));

    }

    public static void UtilitsInv(Player player){
        InventoryGUI utilsInv = new InventoryGUI("&bUtilidades", 6*9);
        LayoutUtilits(utilsInv);
        utilsInv.show(player);
    }

}

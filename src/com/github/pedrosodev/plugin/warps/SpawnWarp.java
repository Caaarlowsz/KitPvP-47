package com.github.pedrosodev.plugin.warps;

import com.github.pedrosodev.plugin.inventory.SpawnInventorys;
import com.systemcore.pedrosogaymer.utils.CustomItens.buttons.ClickActionItemNoEntity;
import com.systemcore.pedrosogaymer.utils.CustomItens.buttons.CustomItemButton;
import com.systemcore.pedrosogaymer.utils.MakeItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class SpawnWarp implements WarpsI{
    @Override
    public Material getIcon() {
        return null;
    }

    @Override
    public String getName() {
        return "Spawn";
    }

    @Override
    public List<String> getDescription() {
        return null;
    }

    @Override
    public void onJoin(Player player) {
        setItens(player);
    }

    public void setItens(Player player){

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

        CustomItemButton button;
        MakeItem item;

        //Layout
        item = new MakeItem(Material.STAINED_GLASS_PANE);
        item.setName(" ");
        item.setDurability((short)0);
        player.getInventory().setItem(0, item.build());
        player.getInventory().setItem(8, item.build());
        item.setDurability((short)11);
        player.getInventory().setItem(1, item.build());
        player.getInventory().setItem(7, item.build());

        //Warps
        item = new MakeItem(Material.COMPASS);
        item.setName("&bWarps &7(Clique para abrir o menu)");
        button = new CustomItemButton(item.build());
        button.addActionNoEntity(click -> {
            SpawnInventorys.WarpsInv(player);
        });
        player.getInventory().setItem(2, button.getItemStack());

        //Kits
        item = new MakeItem(Material.ENDER_CHEST);
        item.setName("&bKits &7(Clique para abrir o menu)");
        button = new CustomItemButton(item.build());
        button.addActionNoEntity(click ->{
            SpawnInventorys.KitsInv(player);
        });
        player.getInventory().setItem(4, button.getItemStack());

        //Utilidades
        item = new MakeItem(player.getName());
        item.setName("&bUtilidades &7(Clique para abrir o menu)");
        button = new CustomItemButton(item.build());
        button.addActionNoEntity(click ->{
            SpawnInventorys.UtilitsInv(player);
        });
        player.getInventory().setItem(6, button.getItemStack());

    }

}

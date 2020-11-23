package com.github.pedrosodev.plugin.commands.general;

import com.github.pedrosodev.plugin.KitPvP;
import com.github.pedrosodev.plugin.enums.Groups;
import com.github.pedrosodev.plugin.player.Account;
import com.systemcore.pedrosogaymer.api.ClickChatAPI;
import com.systemcore.pedrosogaymer.api.TagAPI;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import com.systemcore.pedrosogaymer.utils.TextUtil;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TagCommand extends CommandClass {

    @BukkitCommandFramework.Completer(name = "tag")
    public List<String> TagCompleter(BukkitCommandFramework.CommandArgs cmd){
        Player player = cmd.getPlayer();
        List<String> tagsList = new ArrayList<>();
        for (Groups tag : Groups.values()){
            if (player.hasPermission("kitpvp.tag."+tag.name().toLowerCase())){
                tagsList.add((tag.getTagName(). substring(0,1).toUpperCase()+tag.getTagName().substring(1).toLowerCase()));
            }
        }
        return tagsList;
    }

    @BukkitCommandFramework.Command(name = "tag")
    public void TagCommand(BukkitCommandFramework.CommandArgs cmd){
        Player player = cmd.getPlayer();
        String[] strings = cmd.getArgs();

        if (strings.length == 0){
            for (Groups tag : Groups.values()){
                if (player.hasPermission("kitpvp.tag."+tag.name().toLowerCase())){
                    ClickChatAPI.comando(player, tag.getCorTag()+tag.getTagName(),
                            "/tag "+tag.name(),
                            "&7Clique para mudar de tag!");
                }
            }
            return;
        }

        String tagString = strings[0]
                .replace("+", "plus")
                .replace("adm", "admin")
                .toUpperCase();

        try{
            Groups tag = Groups.valueOf(tagString);
            Account account = KitPvP.getAccountManager().getAccount(player.getUniqueId());
            if (account.getTag() != tag){
                TagAPI.setTag(player, tag == Groups.MEMBRO ? "&7" : tag.getCorTag() + tag.getTagName() + tag.getCor() + " ",
                        TagAPI.getSuffix(player), tag.getOrder() + account.getRank().getOrder());
                account.setTag(tag);
                player.sendMessage(TextUtil.colorText("&b&lTag &fSua tag foi alterada com sucesso."));
                return;
            }else {
                player.sendMessage(TextUtil.colorText("&b&lTag &fVocê já está utiliizando esse tag."));
                return;
            }
        }catch (Exception e){
            player.sendMessage(TextUtil.colorText("&b&lTag &fTag não existente."));
            return;
        }

    }

}

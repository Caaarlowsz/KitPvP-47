package com.github.pedrosodev.plugin.commands;

import com.github.pedrosodev.plugin.KitPvP;
import com.systemcore.pedrosogaymer.api.BanAPI.constructor.Ban;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import javafx.css.CssMetaData;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class TesteCommand extends CommandClass {

    @BukkitCommandFramework.Command(name = "teste")
    public void Teste1Command(BukkitCommandFramework.CommandArgs cmd){
        CommandSender player = cmd.getSender();
        String[] strings = cmd.getArgs();

        Ban ban = new Ban(player.getName(), "Teste");
    }

    @BukkitCommandFramework.Command(name = "teste2")
    public void Teste2Command(BukkitCommandFramework.CommandArgs cmd){
        CommandSender player = cmd.getSender();
        String[] strings = cmd.getArgs();

    }

}

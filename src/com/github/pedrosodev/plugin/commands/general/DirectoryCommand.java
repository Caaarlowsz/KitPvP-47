package com.github.pedrosodev.plugin.commands.general;

import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.CommandClass;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class DirectoryCommand extends CommandClass {

    @BukkitCommandFramework.Completer(name = "template")
    public List<String> TemplateCompleter(BukkitCommandFramework.CommandArgs cmd){
        return Arrays.asList("TabCompler1", "TabCompler2", "e TabCompler2");
    }

    @BukkitCommandFramework.Command(name = "template", onlyPlayers = true)
    public void TemplateCommands(BukkitCommandFramework.CommandArgs cmd){
        Player player = cmd.getPlayer();
        String[] strings = cmd.getArgs();

    }
}

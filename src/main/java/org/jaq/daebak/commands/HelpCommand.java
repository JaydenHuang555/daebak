package org.jaq.daebak.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jaq.daebak.Constants;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.Client;
import org.jetbrains.annotations.NotNull;

public class HelpCommand extends CommandTemplate implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Client client = Global.tryToGet((Player) commandSender);
        for(int i = 0; i < Constants.commands.getSize(); i++){
            CommandTemplate com = Constants.commands.get(i);
            client.sendf("name: %s, usage: %s, description: %s", com.toString(), com.usage(), com.description());
        }
        return true;
    }

    @Override
    public String description() {
        return "displays all commands useful to the server";
    }

    @Override
    public String usage() {
        return "/help";
    }
}

package org.jaq.daebak.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jaq.util.math.Math;
import org.jetbrains.annotations.NotNull;

import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

public class EvalCommand extends CommandTemplate {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 0){
            ((Player)sender).sendMessage("usage: %s", usage());
            return false;            
        }
        ((Player)sender).sendMessage(String.valueOf(Math.evalf(args[0])));
        return true;
    }

    @Override
    public @NotNull String description() {
        return "evals shit";
    }

    @Override
    public @NotNull String usage() {
        return "/eval <item>";
    }

    @Override
    public @NotNull String toString(){
        return "eval";
    }
    
}

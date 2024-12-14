package org.jaq.daebak.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jaq.daebak.Constants;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.Client;
import org.jetbrains.annotations.NotNull;

public class EndCommand extends CommandTemplate {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(isOnlyOp() && !((Player)commandSender).isOp()) return false;
        while(true){
            try {
                Global.broadCastf("ending in %d seconds", 5);
                Thread.sleep(1000);
                Global.broadCastf("ending in %d seconds", 4);
                Thread.sleep(1000);
                Global.broadCastf("ending in %d seconds", 3);
                Thread.sleep(1000);
                Global.broadCastf("ending in %d seconds", 2);
                Thread.sleep(1000);
                Global.broadCastf("ending in %d seconds", 1);
                Thread.sleep(1000);
                Bukkit.getServer().shutdown();
                break;
            } catch (Exception e){
                Bukkit.getServer().shutdown();
            }
        }
        return true;
    }

    @Override
    public @NotNull String description() {
        return "ends server";
    }

    @Override
    public @NotNull String usage() {
        return "/end";
    }

    @Override
    public @NotNull String toString(){
        return "end";
    }
    @Override
    public boolean isOnlyOp(){
        return true;
    }
}

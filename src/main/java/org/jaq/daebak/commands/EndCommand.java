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
    public boolean handle(@NotNull Client client, @NotNull String args[]){
        try {
            for (int i = 0; i < 5; i++) {
                Thread.sleep(1000);
                Global.broadCastf("ending in %d seconds", 5 - i);
            }
        } catch (Exception e){
            Global.warnf(e.toString());
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

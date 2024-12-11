package org.jaq.daebak.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.Client;
import org.jetbrains.annotations.NotNull;

public class DepositCommand implements CommandExecutor {
    public DepositCommand(){

    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String args[]) {
        try {
            Client sender = Global.tryToGet((Player) commandSender);
            if(args.length == 0){
                sender.send("/deposit: /deposit <amount>");
                return false;
            }
            sender.deposit(Double.parseDouble(args[0]));
        } catch (Exception e){
            Global.warn(e.toString());
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        return "deposit";
    }

}
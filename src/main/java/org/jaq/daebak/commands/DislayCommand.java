package org.jaq.daebak.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jaq.daebak.Constants;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.Client;

public class DislayCommand implements CommandExecutor {
    public DislayCommand(){}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
        Client client = Global.tryToGet((Player) sender);
        try {
            client.send(String.format("the bank has %s%f", Constants.currencySymbol, Global.getBank().getHeldMoney(client).amount));
            client.send(String.format("you have %s%f", Constants.currencySymbol, client.getStats().money.amount));
        } catch (Exception e){
            Global.warning(e.toString());
        }
        return true;
    }

    @Override
    public String toString(){
        return "display";
    }

}
package org.jaq.daebak.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.Client;

public class WithDrawCommand implements CommandExecutor {
    public WithDrawCommand(){}
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String args[]){
        Client client = Global.tryToGet((Player) sender);
        if(args.length == 0) {
            client.send("valid usage: /withdraw <amount>");
            return false;
        }
        try {
            Global.getBank().withDraw(client, Double.parseDouble(args[0]));
        } catch (Exception e){
            client.send("unable to withdraw money");
        }
        return true;
    }

    @Override
    public String toString(){
        return "withdraw";
    }

}
package org.jaq.daebak;

import org.bukkit.entity.Player;
import org.jaq.daebak.bank.Bank;
import org.jaq.daebak.client.Client;
import org.jaq.util.HashTable;
import org.jaq.util.OrderedList;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class Global {
    public final static OrderedList<Client> clients = new OrderedList<>();
    public final static HashMap<Player, Client> clientMap = new HashMap<>();
    public final static Bank bank = new Bank();

    // public final static boolean isWin = System.getProperty("os name").contains("win");

    public static void addClient(@NotNull Player player){
        Client client = new Client(player);
        clients.add(client);
        clientMap.put(player, client);
    }

    public static Client tryToGet(@NotNull Player key){
        if(!clientMap.containsKey(key)) return new Client(null);
        return clientMap.get(key);
    }

    public static Bank getBank(){
        return bank;
    }

    /* public static boolean isWindows(){
        return isWin;
    } */

}
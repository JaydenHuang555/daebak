package org.jaq.daebak;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jaq.daebak.bank.Bank;
import org.jaq.daebak.client.Client;
import org.jaq.util.HashTable;
import org.jaq.util.OrderedList;
import org.jetbrains.annotations.NotNull;
import java.io.File;

import java.io.File;
import java.util.HashMap;

public class Global {
    public final static OrderedList<Client> clients = new OrderedList<>();
    public final static HashMap<Player, Client> clientMap = new HashMap<>();
    public final static Bank bank = new Bank();

    public static Daebak daebak;


    public static void addClient(@NotNull Player player){
        Client client = new Client(player);
        clients.add(client);
        clientMap.put(player, client);
        Global.logf("added player %s to clients", player.getName());
    }

    public static Client tryToGet(@NotNull Player key){
        if(!clientMap.containsKey(key)) return new Client(null);
        return clientMap.get(key);
    }

    public static Bank getBank(){
        return bank;
    }

    public static Daebak getDaebak(){
        return daebak;
    }

    public static void log(String s){
        Bukkit.getLogger().info(s);
    }

    public static void logf(String format, Object ...args){
        log(String.format(format, args));
    }

    public static void warning(String s){
        Bukkit.getLogger().warning(s);
    }

}
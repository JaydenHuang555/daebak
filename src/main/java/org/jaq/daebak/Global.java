package org.jaq.daebak;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jaq.daebak.bank.Bank;
import org.jaq.daebak.client.Client;
import org.jaq.util.OrderedList;
import org.jaq.util.PropertyType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.logging.Level;

public class Global {
    public final static OrderedList<Client> clients = new OrderedList<>();
    public final static HashMap<Player, Client> clientMap = new HashMap<>();
    public final static Bank bank = new Bank();

    public static Daebak daebak;
    public final static boolean isWindows =
            System.getProperty(PropertyType.OS_NAME.key()) != null &&
            System.getProperty(PropertyType.OS_NAME.key()).contains("win");

    public final static boolean isWindows(){
        return isWindows;
    }


    public static void addClient(@NotNull Player player){
        Client client = new Client(player);
        clients.add(client);
        clientMap.put(player, client);
        logf("added player %s to clients", player.getName());
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

    public static void log(@NotNull Level level, @NotNull String s){
        Bukkit.getLogger().log(level, s);
        for(int i = 0; i < clients.getSize(); i++)
            if(clients.get(i).isOp()) clients.get(i).sendf("console: %s", s);
    }

    public static void log(@NotNull String s){
        log(Level.INFO, s);
    }

    @SafeVarargs
    public static void logf(@NotNull String format, Object ...args){
        log(String.format(format, args));
    }

    public static void warn(@NotNull String s){
        log(Level.WARNING, s);
    }

}
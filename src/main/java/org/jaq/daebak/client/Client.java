package org.jaq.daebak.client;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jaq.daebak.Global;
import org.jetbrains.annotations.NotNull;

public class Client {
    private final Player player;
    private final ClientPhone phone;

    private final Stats stats;

    public Client(@NotNull Player player){
        this.player = player;
        this.phone = new ClientPhone(this);
        this.stats = new Stats();
        this.stats.money = new Money(23);

    }

    public void openPhone(){
        phone.display(this);
    }

    public void send(@NotNull String message){
        player.sendMessage(message);
        Global.logf("sending to %s: %s", player.getName(), message);
    }

    public void sendf(@NotNull String format, Object ... args){
        player.sendMessage(String.format(format, args));
        Global.logf("sending to %s: %s", player.getName(), String.format(format, args));
    }

    public void spawnAt(@NotNull Location loc){
        player.spawnAt(loc);
    }

    public Player getPlayer(){
        return player;
    }

    public void openInventory(@NotNull Inventory inventory){
        player.openInventory(inventory);
    }

    public ClientPhone getClientPhone(){
        return phone;
    }

    public Stats getStats(){
        return stats;
    }

    public void deposit(double amount) throws Exception {
        Global.getBank().deposit(this,amount);
    }

    public void deposit(Money money) throws Exception {
        Global.getBank().deposit(this,money);
    }

    public void withdraw(Money money) throws Exception {
        Global.getBank().withdraw(this, money);
    }

    public void withDraw(double amount) throws Exception{
        Global.getBank().withDraw(this, amount);
    }

    public boolean isOp(){
        return player.isOp();
    }

    @Override
    public String toString(){
        return player.getName();
    }

    public class Stats {
        public Money money;
        public boolean isWanted = false;
    }
}
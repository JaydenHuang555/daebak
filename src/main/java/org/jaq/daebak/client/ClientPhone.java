package org.jaq.daebak.client;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.jaq.daebak.Constants;
import org.jaq.daebak.client.app.App;
import org.jaq.daebak.client.app.BankApp;
import org.jaq.daebak.client.app.StatusApp;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ClientPhone {
    private final Inventory inventory;
    private final ArrayList<App> apps = new ArrayList<>();
    private Client client;

    private void registerDefaultApps(){
        this.addApp(new BankApp(client));
        this.addApp(new StatusApp(client));
    }

    public ClientPhone(Client client){
        this.client = client;
        this.registerDefaultApps();
        inventory = Bukkit.createInventory(null, InventoryType.HOPPER, Constants.ClientPhoneConstants.title);
    }

    public void addApp(App app){
        inventory.addItem(app.getItemStack());
        apps.add(app);
    }

    public void handle(@NotNull InventoryClickEvent event){
        getApp(event.getSlot()).handle(event);
    }

    public App getApp(int index){
        return apps.get(index);
    }

    public void display(@NotNull Client client){
        client.openInventory(inventory);
    }



}
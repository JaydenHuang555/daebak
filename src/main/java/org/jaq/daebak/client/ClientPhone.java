package org.jaq.daebak.client;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jaq.daebak.Constants;
import org.jaq.daebak.Daebak;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.app.App;
import org.jaq.daebak.client.app.BankApp;
import org.jaq.daebak.client.app.StatusApp;
import org.jaq.util.OrderedList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ClientPhone {
    private final Inventory inventory;
    private final OrderedList<App> apps = new OrderedList<>();
    private Client client;

    private void registerDefaultApps(){
        this.addApp(new BankApp(client));
        this.addApp(new StatusApp(client));
    }

    public ClientPhone(@NotNull Client client){
        this.client = client;
        this.registerDefaultApps();
        Global.log("ClientPhone called");
        inventory = Bukkit.createInventory(null, InventoryType.HOPPER, Constants.ClientPhoneConstants.title);
        ItemStack stack = new ItemStack(Material.LODESTONE);
        ItemMeta meta = stack.getItemMeta();
        meta.displayName(Component.text(Constants.ClientPhoneConstants.title));
        stack.setItemMeta(meta);
        client.getPlayer().getWorld().dropItem(client.getPlayer().getLocation(), stack);
    }

    public void addApp(@NotNull App app){
        apps.add(app);
    }

    public void handle(@NotNull InventoryClickEvent event){
        getApp(event.getSlot()).handle(event);
    }

    public App getApp(int index){
        return apps.get(index);
    }

    public void display(@NotNull Client client){
        for(int i = 0; i < apps.getSize(); i++) inventory.addItem(apps.get(i).getItemStack());
        client.openInventory(inventory);
    }



}
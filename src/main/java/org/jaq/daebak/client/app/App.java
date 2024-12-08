package org.jaq.daebak.client.app;

import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.jaq.daebak.client.Client;

public abstract class App {

    private ItemStack itemStack;
    protected final Client client;
    App(Client client, ItemStack itemStack, String name){
        this.itemStack = itemStack;
        this.itemStack.getItemMeta().displayName(Component.text(name));
        this.client = client;
    }

    public ItemStack getItemStack(){
        return itemStack;
    }

    public abstract void handle(InventoryClickEvent event);
    

}
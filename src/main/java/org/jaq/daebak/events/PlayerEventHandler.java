package org.jaq.daebak.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.jaq.daebak.client.Client;
import org.jaq.daebak.Constants.*;
import org.jaq.daebak.Constants;
import org.jaq.daebak.Global;

public class PlayerEventHandler implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Global.addClient(event.getPlayer());
        Client client = Global.tryToGet(event.getPlayer());

        client.spawnAt(Constants.spawnPointLocation);
        client.sendMessage("welcome to this server");
        
    }

    @EventHandler
    public void onPlayerClickEvent(PlayerInteractEvent event){
        Client client = Global.tryToGet(event.getPlayer());
        ItemMeta metaData = client.getPlayer().getActiveItem().getItemMeta();
        if(event.getAction().isRightClick()){
            if(metaData.displayName().toString().compareToIgnoreCase(ClientPhoneConstants.title) == 0) client.openPhone();
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Client client = Global.tryToGet((Player) event.getWhoClicked());
        if((client.getPlayer()) == null) return;
        if(client.getPlayer().getOpenInventory().title().toString().compareToIgnoreCase(Constants.ClientPhoneConstants.title) == 0)
            client.getClientPhone().handle(event);
    }

}
package org.jaq.daebak.client.app;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jaq.daebak.Constants;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.Client;

public class StatusApp extends App  {

    public StatusApp(Client client) {
        super(client, new ItemStack(Constants.StatusAppConstants.MATERIAL), Constants.StatusAppConstants.NAME);
    }

    private ItemStack buildBook(){
        ItemStack book = new ItemStack(Material.BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.title(Component.text(String.format("Wanted: %s", client.getStats().isWanted ? "true" : "false")));
        meta.setAuthor("Police");
        return book;
    }
    public void handle(InventoryClickEvent event){
        Global.tryToGet((Player) event.getWhoClicked()).getPlayer().openBook(buildBook());
    }

}
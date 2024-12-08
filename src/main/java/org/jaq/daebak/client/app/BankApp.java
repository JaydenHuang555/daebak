package org.jaq.daebak.client.app;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jaq.daebak.Constants.BankAppConstants;
import org.jaq.daebak.client.Client;

public class BankApp extends App  {

    public BankApp(Client client) {
        super(client, new ItemStack(BankAppConstants.material), BankAppConstants.name);

    }

    public void handle(InventoryClickEvent event){
        ItemStack book = new ItemStack(Material.BOOK);
        BookMeta meta = (BookMeta) book.getItemMeta();
        meta.setTitle("Bank");
        meta.setAuthor("Bank");
        meta.addPages(
                Component.text(String.format("amount %d\n", client.getStats().money.amount))
        );
        client.getPlayer().openBook(book);
    }
}
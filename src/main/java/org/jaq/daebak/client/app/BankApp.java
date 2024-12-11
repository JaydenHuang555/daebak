package org.jaq.daebak.client.app;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.jaq.daebak.Constants;
import org.jaq.daebak.Constants.BankAppConstants;
import org.jaq.daebak.Daebak;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.Client;
import org.jetbrains.annotations.NotNull;

public class BankApp extends App  implements Listener {

    private enum BookFunction {
        WITHDRAW(BankAppConstants.WITHDRAW_TITLE, new ItemStack(Material.BOOK)),
        DEPOSIT(BankAppConstants.DEPOSIT_TITLE, new ItemStack(Material.BOOK))
        ;
        public final String name;
        public final ItemStack book;
        private BookFunction(@NotNull String name, @NotNull ItemStack book){
            this.name = name;
            this.book = book;
        }
    }

    private ItemStack withDrawBook = BookFunction.WITHDRAW.book;
    private ItemStack depositBook = BookFunction.DEPOSIT.book;

    private boolean withDrawOpen = false;
    private boolean depositOpen = false;

    private void createBooks(){
        withDrawBook.getItemMeta().displayName(Component.text(BookFunction.WITHDRAW.name));
        depositBook.getItemMeta().displayName(Component.text(BookFunction.DEPOSIT.name));

        ((BookMeta) withDrawBook.getItemMeta()).setAuthor("Bank");
        ((BookMeta) depositBook.getItemMeta()).setAuthor("Bank");

        ((BookMeta)withDrawBook.getItemMeta()).setTitle("");
        ((BookMeta)withDrawBook.getItemMeta()).setTitle("");
        

    }

    public BankApp(Client client) {
        super(client, new ItemStack(BankAppConstants.MATERIAL), BankAppConstants.NAME);
        Daebak.registerEvent(this);
        this.createBooks();

    }

    private ItemStack getItem(Material material, String displayName){
        ItemStack stack = new ItemStack(material);
        stack.getItemMeta().displayName(Component.text(displayName));
        return stack;
    }

    public void handle(InventoryClickEvent event){
        Inventory inventory = Bukkit.createInventory(null, InventoryType.DISPENSER, BankAppConstants.INV_TITLE);
        Client client = Global.tryToGet((Player) event.getWhoClicked());
        inventory.addItem(withDrawBook);

        inventory.addItem(getItem(Material.STICK, String.valueOf(Global.getBank().getHeldMoney(client))));
        inventory.addItem(depositBook);

    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        if(!event.getClickedInventory().toString().contentEquals(BankAppConstants.INV_TITLE)) return;
        Client client = Global.tryToGet((Player) event.getWhoClicked());
        if(event.getCurrentItem().getItemMeta().displayName().toString().contentEquals(BankAppConstants.INV_TITLE)){
            withDrawOpen = true;
            client.getPlayer().openBook(withDrawBook);
        }

        if(event.getCurrentItem().getItemMeta().displayName().toString().contentEquals(BankAppConstants.INV_TITLE)){
            depositOpen = true;
            client.getPlayer().openBook(depositBook);
        }

    }

    @EventHandler
    public void onInventoryExit(InventoryCloseEvent event){
        if(!event.getInventory().toString().contentEquals(BankAppConstants.INV_TITLE)) return;
        if(!(depositOpen || withDrawOpen)) return;
        Client client = Global.tryToGet((Player) event.getPlayer());
        if(depositOpen){
            try {
                Global.getBank().deposit(client, Double.parseDouble(((BookMeta)depositBook.getItemMeta()).getTitle()));
            } catch (Exception e){
                Global.warning(e.toString());
            }
            depositOpen = false;
        }
        if(withDrawOpen){
            try {
                Global.getBank().withDraw(client, Double.parseDouble(((BookMeta)withDrawBook).getTitle()));
            } catch (Exception e){
                Global.warning(e.toString());
            }
        }

    }

}
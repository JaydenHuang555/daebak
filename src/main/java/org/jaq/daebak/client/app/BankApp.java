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
import org.jaq.daebak.Constants.BankAppConstants;
import org.jaq.daebak.Daebak;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.Client;

public class BankApp extends App  implements Listener {

    private final String INV_TITLE = "BankApp";
    private final String WITHDRAW_TITLE = "withdraw";
    private final String DEPOSIT_TITLE  = "deposit";

    private ItemStack withDrawBook, depositBook;

    private boolean withDrawOpen = false;
    private boolean depositOpen = false;

    private void createBooks(){
        withDrawBook = new ItemStack(Material.BOOK);
        depositBook = new ItemStack(Material.BOOK);
        withDrawBook.getItemMeta().displayName(Component.text(WITHDRAW_TITLE));
        depositBook.getItemMeta().displayName(Component.text(DEPOSIT_TITLE));

        ((BookMeta) withDrawBook.getItemMeta()).setAuthor("Bank");
        ((BookMeta) depositBook.getItemMeta()).setAuthor("Bank");

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
        Inventory inventory = Bukkit.createInventory(null, InventoryType.DISPENSER, INV_TITLE);
        Client client = Global.tryToGet((Player) event.getWhoClicked());
        inventory.addItem(withDrawBook);

        inventory.addItem(getItem(Material.STICK, String.valueOf(Global.getBank().getHeldMoney(client))));
        inventory.addItem(depositBook);

    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event){
        if(!event.getClickedInventory().toString().contentEquals(INV_TITLE)) return;
        Client client = Global.tryToGet((Player) event.getWhoClicked());
        if(event.getCurrentItem().getItemMeta().displayName().toString().contentEquals(WITHDRAW_TITLE)){
            withDrawOpen = true;
            client.getPlayer().openBook(withDrawBook);
        }

        if(event.getCurrentItem().getItemMeta().displayName().toString().contentEquals(DEPOSIT_TITLE)){
            depositOpen = true;
            client.getPlayer().openBook(depositBook);
        }

    }

    @EventHandler
    public void onInventoryExit(InventoryCloseEvent event){
        if(!event.getInventory().toString().contentEquals(INV_TITLE)) return;
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
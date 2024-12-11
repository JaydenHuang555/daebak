package org.jaq.daebak.bank;

import com.google.gson.Gson;
import org.jaq.daebak.Constants;
import org.jaq.daebak.Global;
import org.jaq.daebak.client.Client;
import org.jaq.daebak.client.Money;
import org.jaq.util.OrderedList;
import org.jetbrains.annotations.NotNull;

import java.io.PrintWriter;
import java.util.HashMap;

public final class Bank {
    private class PeriodicThread extends Thread {
        PeriodicThread(){
            super();
        }

        @Override
        public void run(){
            try {
                Thread.sleep(5400000);
                Global.getBank().flush();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private class BankAccount {
        Money bankMoney;
        Client holder;

        public BankAccount(Client holder){
            this.holder = holder;
            this.bankMoney = new Money(0d);
        }

    }

    private final HashMap<Client, BankAccount> accounts = new HashMap<>();
    private final OrderedList<BankAccount> accountsList = new OrderedList<>();

    public Bank(){
        new PeriodicThread().start();
    }

    private void put(@NotNull Client client){
        BankAccount account = new BankAccount(client);
        accounts.put(client, account);
        accountsList.add(account);
    }

    public void addAccount(@NotNull Client client){
        put(client);
    }

    public void deposit(@NotNull Client client, double amount) throws Exception{
        if(amount > client.getStats().money.amount) throw new Exception(String.format("unable to deposit money for client %s", client.getPlayer().name()));
        client.getStats().money.amount -= amount;
        accounts.get(client).bankMoney.amount += amount;

        Global.logf("%s deposited %s%f", client.toString(), Constants.currencySymbol, amount);

    }

    public void deposit(@NotNull Client client, @NotNull  Money amount) throws Exception {
        deposit(client, amount.amount);
    }

    public void withDraw(@NotNull Client client, double amount) throws Exception {
        if(accounts.get(client).bankMoney.amount < amount) throw new Exception(String.format("unable to withdraw money for client %s", client.getPlayer().name()));
        client.getStats().money.amount += amount;
        accounts.get(client).bankMoney.amount -= amount;
        Global.logf("%s withdrew %s%f", client.toString(), Constants.currencySymbol, amount);
    }

    public void withdraw(@NotNull Client client, @NotNull Money money) throws Exception{
        withDraw(client, money.amount);
    }

    public Money getHeldMoney(@NotNull Client client){
        return accounts.get(client).bankMoney;
    }

    public void flush(){
        try {
            String firstCommand = Global.isWindows() ? "powershell -Command rm -bank.json" : "rm -rf bank.json";
            String secondCommand = Global.isWindows() ? "powershell -Command mv bank.json.bp bank.json" : "mv bank.json.bp bank.json";

            PrintWriter writer = new PrintWriter("bank.json.bp");
            Gson gson = new Gson();
            for(int i = 0; i < accountsList.getSize(); i++) gson.toJson(accountsList.get(i), writer);
            writer.flush();
            writer.close();

            if(Runtime.getRuntime().exec(firstCommand).exitValue() != 0) throw new Exception("unable to remove bank.json file");
            if(Runtime.getRuntime().exec(secondCommand).exitValue() != 0) throw new Exception("unable to mv bank.json.bp file to bank.json");

        } catch (Exception e){
            Global.warn(e.toString());
        }
    }

}
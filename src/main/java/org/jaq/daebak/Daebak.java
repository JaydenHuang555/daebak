package org.jaq.daebak;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jaq.daebak.commands.DepositCommand;
import org.jaq.daebak.commands.DislayCommand;
import org.jaq.daebak.events.PlayerEventHandler;
import org.jaq.daebak.Constants.AdminConstants;


public final class Daebak extends JavaPlugin {

    public Daebak(){
        super();
        Global.daebak = this;
    }

    public void writeCommand(String command){
        Bukkit.dispatchCommand(getServer().getConsoleSender(), String.format(command));
    }

    public static void registerEvent(Listener listener){
        Bukkit.getServer().getPluginManager().registerEvents(listener, Global.getDaebak());
    }

    /*TODO: add checks for valid command */
    private void initCommands(){
        for(int i = 0; i < Constants.commands.getSize(); i++){
            getCommand(Constants.commands.get(i).toString()).setExecutor((CommandExecutor) Constants.commands.get(i));
        }
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerEventHandler(), this);

        initCommands();

        for(int i = 0; i < AdminConstants.adminNames.getSize(); i++)
            writeCommand(String.format("op %s", AdminConstants.adminNames.get(i)));

    }

    @Override
    public void onDisable() {
        Global.getBank().flush();
        Global.log("disabling plugin");
    }
}

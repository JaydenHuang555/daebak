package org.jaq.daebak;

import org.bukkit.plugin.java.JavaPlugin;
import org.jaq.daebak.events.PlayerEventHandler;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerEventHandler(), this);
        getServer().getConsoleSender().sendMessage("able to load plugin");

    }

    @Override
    public void onDisable() {
        Global.getBank().flush();
        getServer().getConsoleSender().sendMessage("disabling plugin");
    }
}

package org.jaq.daebak;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.jaq.daebak.commands.DepositCommand;
import org.jaq.daebak.commands.DislayCommand;
import org.jaq.daebak.commands.WithDrawCommand;
import org.jaq.util.OrderedList;

import java.io.File;

public final class Constants {
    public static Location spawnPointLocation = new Location(Bukkit.getWorld(""), 0.0, 0.0, 0.0);
    public final static OrderedList<CommandExecutor> commands = new OrderedList<>();

    public final static String currencySymbol = "₩";

    public final static boolean isWindows = File.separator.equals("\\");

    public final static boolean isWindows(){
        return isWindows;
    }

    static {
        commands.add(new DepositCommand());
        commands.add(new DislayCommand());
        commands.add(new WithDrawCommand());
    }

    public final class AdminConstants {
        public final static OrderedList<String> adminNames = new OrderedList<>();

        static {
            adminNames.add("reavontsg");
            adminNames.add("stan");
            adminNames.add("jaq");
            adminNames.add("volcano");
            adminNames.add("john Doe321");
        }

    }

    public final class ClientPhoneConstants {
        public static String title = "Phone";
    }



    public final class BankAppConstants {
        public static Material MATERIAL = Material.RED_DYE;
        public static String NAME = "Bank";

        public final static String INV_TITLE = "BankApp";
        public final static String WITHDRAW_TITLE = "withdraw";
        public final static String DEPOSIT_TITLE  = "deposit";
    }

    public final class StatusAppConstants {
        public static Material MATERIAL = Material.BLUE_DYE;
        public static String NAME = "Status";
    }

}
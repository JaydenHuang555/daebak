package org.jaq.daebak;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

public final class Constants {
    public static Location spawnPointLocation = new Location(Bukkit.getWorld(""), 0.0, 0.0, 0.0);

    public final class ClientPhoneConstants {
        public static String title = "Phone";
    }

    public final class BankAppConstants {
        public static Material material = Material.RED_DYE;
        public static String name = "Bank";
    }

    public final class StatusAppConstants {
        public static Material material = Material.BLUE_DYE;
        public static String name = "Status";
    }

}
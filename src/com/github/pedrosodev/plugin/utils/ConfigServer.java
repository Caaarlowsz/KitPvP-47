package com.github.pedrosodev.plugin.utils;

import com.github.pedrosodev.plugin.KitPvP;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigServer {

    static FileConfiguration config = KitPvP.getJava().getConfig();

    public static void createConfig(){
        KitPvP.getJava().saveDefaultConfig();
    }

    public static String getMySQLHost = config.getString("MySQL.Host");
    public static String getMySQLUsername = config.getString("MySQL.Username");
    public static String getMySQLPassword = config.getString("MySQL.Password");
    public static String getMySQLDataBase = config.getString("MySQL.DataBase");
    public static int getMySQLPort = config.getInt("MySQL.Port");


    public static String getTokenDiscord = config.getString("Discord.Token");
    public static String getStatusDiscord = config.getString("Discord.Status");


    public static boolean hasLoginSystem = config.getBoolean("Servidor.LoginSystem");
    public static boolean PremiumAutoLogin = config.getBoolean("Servidor.PremiumAutoLogin");
    public static boolean hasBanSystem = config.getBoolean("Servidor.BanSystem");
    public static String getServerName = config.getString("Servidor.NomeDoServidor");
    public static String getServerSite = config.getString("Servidor.Site");


    public static String getBanMessage = config.getString("Messages.Ban").replace("{breakline}", "\n");
    public static String getTempBanMessage = config.getString("Messages.TempBan").replace("{breakline}", "\n");
    public static String getMuteMessage = config.getString("Messages.Mute").replace("{breakline}", "\n");
    public static String getTempMuteMessage = config.getString("Messages.TempMute").replace("{breakline}", "\n");
    public static String getKickMessage = config.getString("Messages.Kick").replace("{breakline}", "\n");
    public static String getNoPermMessage = config.getString("Messages.NoPermission");

}

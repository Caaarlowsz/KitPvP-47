package com.github.pedrosodev.plugin;

import com.github.pedrosodev.plugin.commands.DirectoryCommand;
import com.github.pedrosodev.plugin.commands.ban.BanCommand;
import com.github.pedrosodev.plugin.commands.login.LoginCommand;
import com.github.pedrosodev.plugin.database.MySQLManager;
import com.github.pedrosodev.plugin.listener.PlayerListener;
import com.github.pedrosodev.plugin.player.AccountManager;
import com.github.pedrosodev.plugin.utils.ConfigServer;
import com.google.gson.Gson;
import com.systemcore.pedrosogaymer.Core;
import com.systemcore.pedrosogaymer.CorePlugin;
import com.systemcore.pedrosogaymer.api.BanAPI.BanAPI;
import com.systemcore.pedrosogaymer.api.Login.LoginAPI;
import com.systemcore.pedrosogaymer.command.BukkitCommandFramework;
import com.systemcore.pedrosogaymer.command.BukkitCommandLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class KitPvP extends CorePlugin {

    public KitPvP() {
        super("KitPvP", "1.0", "PedrosoDev");
    }

    static KitPvP java;
    static LoginAPI loginAPI;
    static BanAPI banAPI;
    static MySQLManager mySQLManager;
    static Gson gson;
    static AccountManager accountManager;

    public static KitPvP getJava() {
        return java;
    }

    public static LoginAPI getLoginAPI() {
        return loginAPI;
    }

    public static BanAPI getBanAPI() {
        return banAPI;
    }

    public static MySQLManager getMySQLManager() {
        return mySQLManager;
    }

    public static Gson getGson() {
        return gson;
    }

    public static AccountManager getAccountManager() {
        return accountManager;
    }

    @Override
    public void start() {
        java = this;
        gson = new Gson();
        accountManager = new AccountManager();

        Core.KickPlayerExist(this);

        saveDefaultConfig();
        debug("Config Carregada Com Sucesso.");
        if (ConfigServer.hasLoginSystem){
            loginAPI = new LoginAPI(ConfigServer.PremiumAutoLogin, this);
            new BukkitCommandLoader(new BukkitCommandFramework(this)).loadCommandsFromPackage(LoginCommand.class.getPackage().toString().replace("package ", ""));
            debug("Sistema de Login Carregado Com Sucesso.");
        }
        if (ConfigServer.hasBanSystem){
            banAPI = new BanAPI(ConfigServer.getBanMessage, ConfigServer.getTempBanMessage,
                    ConfigServer.getMuteMessage, ConfigServer.getTempMuteMessage, this);
            new BukkitCommandLoader(new BukkitCommandFramework(this)).loadCommandsFromPackage(BanCommand.class.getPackage().toString().replace("package ", ""));

            debug("Sistema de Bans Carregado Com Sucesso.");
        }
        debug("Conectando ao Banco de Dados.");
        mySQLManager = new MySQLManager();
        debug("Conexão Foi Concluida Com Sucesso.");
        RegisterListeners();
        debug("Eventos Carregados Com Sucesso.");
        new BukkitCommandLoader(new BukkitCommandFramework(this)).loadCommandsFromPackage(DirectoryCommand.class.getPackage().toString().replace("package ", ""));
        Core.setNotPermMessage(Core.getNotPermMessage());
    }

    @Override
    public void load() {

    }

    @Override
    public void stop() {

    }

    void RegisterListeners(){
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new PlayerListener(), this);

    }

    public static void debug(String message){
        System.out.println("[DEBUG] "+message);
    }
}

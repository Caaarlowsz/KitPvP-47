package com.github.pedrosodev.plugin.database;

import com.github.pedrosodev.plugin.KitPvP;
import com.github.pedrosodev.plugin.utils.ConfigServer;
import com.systemcore.pedrosogaymer.utils.MySQL;

public class MySQLManager {

    MySQL mySQL;

    public MySQL getMySQL() {
        return mySQL;
    }

    public MySQLManager() {
        mySQL = new MySQL(ConfigServer.getMySQLHost,
                ConfigServer.getMySQLUsername,
                ConfigServer.getMySQLPassword,
                ConfigServer.getMySQLDataBase,
                ConfigServer.getMySQLPort);
        mySQL.tryConnection();
        mySQL.createTable("account", "`Uuid` VARCHAR(128) PRIMARY KEY, `json` VARCHAR(8192))");
        mySQL.createTable("players", "(`Name` VARCHAR(128) PRIMARY KEY, `Uuid` VARCHAR(128))");
        KitPvP.debug("Tabelas Criadas Com Sucesso.");
    }
}

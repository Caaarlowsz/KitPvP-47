package com.github.pedrosodev.plugin.player;

import com.github.pedrosodev.plugin.KitPvP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class AccountManager {
    private HashMap<UUID, Account> players;

    public AccountManager() {
        this.players = new HashMap<>();
    }

    public Account loadAccount(UUID uuid) {
        try {
            Connection connection = KitPvP.getMySQLManager().getMySQL().getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `account` WHERE `Uuid`='" + uuid.toString() + "';");
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                Account player = KitPvP.getGson().fromJson(result.getString("json"), Account.class);
                loadAccount(uuid, player);
            }
            result.close();
            stmt.close();
            result = null;
            stmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return getAccount(uuid);
    }

    public Account loadAccount(UUID uuid, boolean value) {
        Account player = null;
        try {
            Connection connection = KitPvP.getMySQLManager().getMySQL().getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `account` WHERE `Uuid`='" + uuid.toString() + "';");
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                player = KitPvP.getGson().fromJson(result.getString("json"), Account.class);
            }
            result.close();
            stmt.close();
            result = null;
            stmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    public UUID getUUID(String userName) {
        try {
            Connection connection = KitPvP.getMySQLManager().getMySQL().getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM `players` WHERE `Name`='" + userName.toLowerCase() + "';");
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                UUID uuid = UUID.fromString(result.getString("Uuid"));
                return uuid;
            }
            result.close();
            stmt.close();
            result = null;
            stmt = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void loadAccount(UUID uuid, Account player) {
        if (this.players.containsKey(uuid)) {
            return;
        }
        this.players.put(uuid, player);
    }

    public Account getAccount(UUID uuid) {
        if (!this.players.containsKey(uuid)) {
            return null;
        }
        return this.players.get(uuid);
    }

    public void unloadAccount(UUID uuid) {
        if (this.players.containsKey(uuid)) {
            this.players.remove(uuid);
        } else {
            System.out.println("Não foi possivel encontrar o jogador");
        }
    }

    public void saveAccount(Account player) {
        String json = KitPvP.getGson().toJson(player);

        try {
            PreparedStatement stmt = KitPvP.getMySQLManager().getMySQL().prepareStatment("INSERT INTO `account`(`uuid`, `json`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `json` = ?;");
            stmt.setString(1, player.getUuid().toString());
            stmt.setString(2, json);
            stmt.setString(3, json);
            stmt.executeUpdate();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        try {
            PreparedStatement stmt = KitPvP.getMySQLManager().getMySQL().prepareStatment("INSERT INTO `players`(`name`, `uuid`) VALUES (?, ?) ON DUPLICATE KEY UPDATE `uuid` = ?;");
            stmt.setString(1, player.getName().toLowerCase());
            stmt.setString(2, player.getUuid().toString());
            stmt.setString(3, player.getUuid().toString());
            stmt.executeUpdate();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}

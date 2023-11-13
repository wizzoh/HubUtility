package me.wizzo.hubutility.Database;

import me.wizzo.hubutility.Hubutility;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DatabaseGetterHider {

    private Hubutility main;

    public DatabaseGetterHider(Hubutility main) {
        this.main = main;
    }

    public void createTables() {
        String query = "CREATE TABLE IF NOT EXISTS " + main.getHikariCP().pHideTable + " (UUID CHAR(100) NOT NULL PRIMARY KEY,NAME CHAR(100) NOT NULL,VALORE INTEGER NOT NULL DEFAULT 0);";
        try (Connection connection = main.getHikariCP().getSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        String query = "INSERT INTO " + main.getHikariCP().pHideTable + " (UUID,NAME,VALORE) VALUES (?,?,?)";
        if (!exists(uuid)) {
            try (Connection connection = main.getHikariCP().getSource().getConnection();
                 PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, uuid.toString());
                ps.setString(2, player.getName());
                ps.setInt(3, 0);
                ps.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean exists(UUID uuid) {
        String query = "SELECT * FROM " + main.getHikariCP().pHideTable + " WHERE UUID=?";
        try (Connection connection = main.getHikariCP().getSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();

            if (results.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void hiderOnOff(Player player, int value) {
        String query = "UPDATE " + main.getHikariCP().pHideTable + " SET VALORE=? WHERE UUID=?";
        try (Connection connection = main.getHikariCP().getSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            if (getValue(player.getUniqueId()) == 0) {
                value = 1;
            } else {
                value = 0;
            }
            ps.setInt(1, value);
            ps.setString(2, player.getUniqueId().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getValue(UUID uuid) {
        String query = "SELECT VALORE FROM " + main.getHikariCP().pHideTable + " WHERE UUID=?";
        try (Connection connection = main.getHikariCP().getSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, uuid.toString());
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                return results.getInt("VALORE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<String> getPlayers() {
        String query = "SELECT UUID FROM " + main.getHikariCP().pHideTable + " WHERE VALORE=1";
        try (Connection connection = main.getHikariCP().getSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {
            List<String> players = new ArrayList<>();
            ResultSet results = ps.executeQuery();

            while (results.next()) {
                players.add(results.getString("UUID"));
            }
            return players;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package de.elia.systemclasses;

import java.sql.*;

public class DatabaseManager {

    private static Connection connection;

    // Replace with your actual database credentials
    private static final String HOST = "localhost";
    private static final String PORT = "3306";
    private static final String DATABASE = "minecraft ";
    private static final String USERNAME = "soul";
    private static final String PASSWORD = "34ka(edj3!\"@E32";

    public void connect() {
        try {
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            System.out.println("Connected to database!");
        } catch (SQLException e) {
            System.err.println("Failed to connect to database: " + e.getMessage());
        }
    }

    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close connection: " + e.getMessage());
        }
    }

    // Example: Get punishment type for a player
    public String getPunishmentType(String playerName) {
        String punishmentType = null;

        try {
            String query = "SELECT punishmentType FROM Punishments WHERE name = \"" + playerName + "\"";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                punishmentType = resultSet.getString("punishmentType");
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.err.println("Error retrieving punishment type: " + e.getMessage());
        }

        return punishmentType;
    }

    public String getIP(String playerName) {
      String ipaddr = null;

      try {
          String query =
            "SELECT ipaddr FROM altdetector_iptable\n" +
            "WHERE playerid = (\n" +
            "SELECT id FROM altdetector_playertable \n" +
            "WHERE name = \"" + playerName + "\"\n" +
            ")\n" +
            "ORDER BY date DESC\n" +
            "LIMIT 1;";
          PreparedStatement statement = connection.prepareStatement(query);
          ResultSet resultSet = statement.executeQuery();

          if (resultSet.next()) {
            ipaddr = resultSet.getString("ipaddr");
          }

          resultSet.close();
          statement.close();
      } catch (SQLException e) {
          System.err.println("Error retrieving ip address: " + e.getMessage());
      }

      return ipaddr;
    }
}

package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connect;

    public static Connection getConnection() {
        if (connect == null) {
            try {
                Database.connect = DriverManager.getConnection("jdbc:postgresql:mediatheque");
            } catch (SQLException e) {
                System.err.println("Could not initialize database connection" + e.getMessage());
                System.exit(1);
            }
        }
        return Database.connect;
    }
}

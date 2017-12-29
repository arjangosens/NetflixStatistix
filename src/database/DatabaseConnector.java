package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {

    /**
     * This are the settings for the connection with the database.
     * Change this url to the settings that you need for your connection.
     * Because this string will not be the same for everyone.
     */
    private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS2017:1444;databaseName=NetflixStatistics;user=;password=;";

    /**
     * This is the connection with the database.
     */
    private Connection connection = null;

    /**
     * Tries to create a connection with the database with the connectionUrl variable.
     * @return Connection The connection if the connection is successfully created. Else null
     */
    public Connection getConnection() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(connectionUrl);
        } catch (Exception e) {
            System.out.println(e);
        }

        return connection;
    }
}

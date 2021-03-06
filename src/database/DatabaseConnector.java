package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnector {

    /**
     * These are the settings for the connection with the database.
     * Change this url to the setting that you need for your connection.
     * Because this string will not be the same for everyone.
     * If you're using a specifc username and password,
     * you should use the second line and change user and password in that line
     */
    private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=NetflixStatistics;integratedSecurity=true;";
//    private final String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS2017:1444;databaseName=NetflixStatistics;user=SchoolProject;password=school;";


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

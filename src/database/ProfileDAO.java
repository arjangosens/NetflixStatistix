package database;

import applicationLogic.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProfileDAO {
    private DatabaseConnector databaseConnector;

    public ProfileDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    public UserProfile getProfile(int profileID) {
        UserProfile profile = null;
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to search for Subscription
            String query = "SELECT * FROM UserProfile WHERE profileName = " + profile.getProfileName();

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int profileId = resultSet.getInt("profileId");
                int subId = resultSet.getInt("subId");
                String profileName = resultSet.getString("profileName");
                int age = resultSet.getInt("age");

                profile = new UserProfile(profileName, age);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return profile;
    }
}

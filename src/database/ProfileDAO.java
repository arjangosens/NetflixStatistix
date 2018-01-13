package database;

import applicationLogic.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class ProfileDAO {
    private DatabaseConnector databaseConnector;

    public ProfileDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Get a single Profile from the database
     *
     * @param profileID the id of the Profile that you would like to get
     * @return UserProfile The profile that is found in the database by the id
     */
    public UserProfile getProfile(int profileID) {
        UserProfile profile = null;
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to search for Subscription
            String query = "SELECT * FROM UserProfile WHERE profileId = " + profileID;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int profileId = resultSet.getInt("profileId");
                int subId = resultSet.getInt("subId");
                String profileName = resultSet.getString("profileName");
                int age = resultSet.getInt("age");

                profile = new UserProfile(subId, profileName, age);
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

    /**
     * Get a single Profile from the database
     *
     * @param subscriptionId The id of the Subscription that the profile is in
     * @param profileName The name of the profile
     * @return UserProfile The profile in the database that is found by the subscriptionId and profileName
     */
    public UserProfile getProfile(int subscriptionId, String profileName) {
        UserProfile profile = null;
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to search for Subscription
            String query = "SELECT * FROM UserProfile WHERE subId = " + subscriptionId + " AND profileName = " + profileName;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int profileId = resultSet.getInt("profileId");
                int subId = resultSet.getInt("subId");
                profileName = resultSet.getString("profileName");
                int age = resultSet.getInt("age");

                profile = new UserProfile(subId, profileName, age);
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

    /**
     * Get the connected profiles of a specific Subscription
     *
     * @param subscriptionId The Id of the Subscription that the profiles are in.
     * @return Set<UserProfile> A Set filled with the connected profiles of the specified Subscription
     */
    public Set getProfilesOfSubscription(int subscriptionId) {
        Set<UserProfile> userProfileSet = new HashSet<UserProfile>();
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to search for Subscription
            String query = "SELECT * FROM UserProfile WHERE subId = " + subscriptionId;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int profileId = resultSet.getInt("profileId");
                int subId = resultSet.getInt("subId");
                String profileName = resultSet.getString("profileName");
                int age = resultSet.getInt("age");

                userProfileSet.add(new UserProfile(subId, profileName, age));
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
        return userProfileSet;
    }
}

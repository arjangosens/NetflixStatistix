package database;

import applicationLogic.UserProfile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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

            // Form SQL query to search for Profile
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

            // Form SQL query to search for Profile
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
    public ArrayList<UserProfile> getProfilesOfSubscription(int subscriptionId) {
        ArrayList<UserProfile> userProfileSet = new ArrayList<>();
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to search for Profiles
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

    /**
     * Update an existing profile in the database
     *
     * @param profileName The new name of the profile
     * @param age The new age of the profile
     * @param profileId The current Id of the profile
     */
    public void update(String profileName, int age, int profileId) {
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to update Profile
            String query = String.format("UPDATE UserProfile SET profileName = %s, age = %d WHERE profileId = %d)",
                    profileName,
                    age,
                    profileId);

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute query
            statement.executeQuery(query);
            System.out.println("Update complete");

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
    }

    public void insert(int subId, String profileName, int age) {
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to insert Profile
            String query = String.format("INSERT INTO UserProfile (subId, profileName, age) VALUES('%d', '%s', '%d');",
                    subId,
                    profileName,
                    age);

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute query
            statement.executeUpdate(query);
            System.out.println("Insert complete");

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
    }

    /**
     * Delete an existing profile from the database
     *
     * @param profileId The profileId of the profile that will be deleted
     */
    public void delete(int profileId) {
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to delete profile
            String query = "DELETE FROM userProfile WHERE profileId = " + profileId;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();
            statement.execute(query);

            // Execute the query. After executing a Resultset will be stored in this variable
            statement.executeQuery(query);
            System.out.println("Profile successfully deleted");

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
    }
}

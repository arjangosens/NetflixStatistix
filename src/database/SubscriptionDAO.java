package database;

import applicationLogic.Subscription;
import applicationLogic.UserProfile;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SubscriptionDAO {

    private DatabaseConnector databaseConnector;

    public SubscriptionDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Get a single Subscription from the database
     * @param id the id of the Subscription that you would like to get
     * @return Subscription The Subscription that is found in the database by the id
     */
    public Subscription getSubscription(int id) {
        Subscription subscription = null;

        // Create connection with database
        Connection connection = databaseConnector.getConnection();

        try {
            // Form SQL query to search for Subscription
            String query = "SELECT * FROM Subscription WHERE subscriptionId = " + id;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int subscriptionId = resultSet.getInt("subscriptionId");
                String nameSubscriber = resultSet.getString("nameSubscriber");
                String streetName = resultSet.getString("streetName");
                String houseNumber = resultSet.getString("houseNumber");
                String city = resultSet.getString("city");

                subscription = new Subscription(subscriptionId, nameSubscriber, streetName, houseNumber, city);

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

        return subscription;
    }

    /**
     *
     * Get all subscriptions from the database
     * @return ArrayList<Subscription> An ArrayList with all the Subscriptions from the database
     */
    public ArrayList<Subscription> getAll() {
        ArrayList<Subscription> subscriptionArrayList = new ArrayList<>();

        // Creates a new connection with the database
        Connection connection = databaseConnector.getConnection();

        try {
            // Form SQL query to search for Subscriptions
            String query = "SELECT * FROM Subscription";

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int subscriptionId = resultSet.getInt("subscriptionId");
                String nameSubscriber = resultSet.getString("nameSubscriber");
                String streetName = resultSet.getString("streetName");
                String houseNumber = resultSet.getString("houseNumber");
                String city = resultSet.getString("city");

                subscriptionArrayList.add(new Subscription(subscriptionId, nameSubscriber, streetName, houseNumber, city));
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
        return subscriptionArrayList;
    }

    /**
     * Updates a Subscription in the database based on it's subscriptionId
     * @param nameSubscriber The new or current name of the subscriber
     * @param street The new or current street of the subscriber
     * @param houseNumber The new or current houseNumber of the subscriber
     * @param city The new or current city of the subscriber
     * @param id The current subscriptionId of the Subscription that's to be changed
     */
    public void update(String nameSubscriber, String street, String houseNumber, String city, int id) {
        // Create connection with the database
        Connection connection = databaseConnector.getConnection();

        try {
            // Form SQL query to update Subscription
            String query = String.format("UPDATE Subscription SET nameSubscriber = '%s', streetName = '%s', houseNumber = '%s', city = '%s' WHERE subscriptionId = %d;",
                    nameSubscriber,
                    street,
                    houseNumber,
                    city,
                    id);

            // Create statement used to execute the query
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

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
     * This method inserts a new subscription into the database.
     * All these variables are needed if we would like to do that.
     * @param subName The name of the subscription (The name of the user that buys a subscription)
     * @param street The street name of the user that buys a subscription
     * @param houseNumber The houseNumber of the user that buys a subscription
     * @param city And last but not least, the city where the user lives
     */
    public void insert(String subName, String street, String houseNumber, String city) {
        // Create connection with database
        Connection connection = databaseConnector.getConnection();

        try {
            // Form SQL query to search for Subscriptions
            String query = String.format("INSERT INTO Subscription (nameSubscriber, streetName, houseNumber, city) VALUES('%s', '%s', '%s', '%s');",
                    subName,
                    street,
                    houseNumber,
                    city);

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute query
            statement.executeUpdate(query);

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
     * Delete a single subscription
     * @param subscriptionID The subscriptionID is given, else this method does not know what to delete...
     */
    public boolean delete(int subscriptionID) {
        // Create a new connection
        Connection connection = databaseConnector.getConnection();

        try {
            // Form SQL query to search for Subscriptions
            String query = "DELETE FROM Subscription WHERE subscriptionId = " + subscriptionID;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();
            statement.execute(query);

            return true;
            // Execute the query. After executing a Resultset will be stored in this variable
//            ResultSet resultSet = statement.executeQuery(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You can not delete the subscription while there are connected profiles");
            return false;
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
     * Gets all connected UserProfiles from the database
     *
     * @return ArrayList<UserProfile> An ArrayList with all connected profiles
     */
    public ArrayList<UserProfile> getAllUserProfiles() {
        // Create a new connection with the database
        Connection connection = databaseConnector.getConnection();

        ArrayList<UserProfile> userProfiles = new ArrayList<>();

        try {
            // Create the SQL query
            String query = "SELECT subscriptionId, profileId, profileName, age\n" +
                    "FROM Subscription\n" +
                    "\tJOIN UserProfile ON Subscription.subscriptionId = UserProfile.subId\n";

            // Create a statement. Without a statement we can not execute the query
            Statement statement = connection.createStatement();

            // Create a resultSet. Without a resultSet we would nog be able to view the content that we just selected
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int subId = resultSet.getInt("subscriptionId");
                int profileId = resultSet.getInt("profileId");
                String profileName = resultSet.getString("profileName");
                int age = resultSet.getInt("age");

                userProfiles.add(new UserProfile(subId, profileId, profileName, age));
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

        return userProfiles;
    }

}

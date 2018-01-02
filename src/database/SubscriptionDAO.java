package database;

import applicationLogic.Subscription;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SubscriptionDAO {

    private DatabaseConnector databaseConnector;

    public SubscriptionDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Get a single Subscription from the database
     * @param ID the id of the Subscription that you would like to get
     * @return Subscription The Subscription that is found in the database by the id
     */
    public Subscription getSubscription(int id) {
        Subscription subscription = null;
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to search for Subscription
            String query = "SELECT * FROM Subscription WHERE subscriptionId = " + id;

            // Create statement used to execute the query
            Statement stamement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = stamement.executeQuery(query);

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
}

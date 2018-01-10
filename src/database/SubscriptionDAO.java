package database;

import applicationLogic.Subscription;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();
            System.out.println("Connecting");

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

    public Set getAll() {
        Set<Subscription> subscriptionSet = new HashSet<Subscription>();
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

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

                subscriptionSet.add(new Subscription(subscriptionId, nameSubscriber, streetName, houseNumber, city));
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
        return subscriptionSet;
    }

    public void update(String nameSubscriber, String street, String houseNumber, String city, int id) {
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

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

    public void insert(String subName, String street, String houseNumber, String city) {
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

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

    public void delete(int subscriptionID) {
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to search for Subscriptions
            String query = "DELETE FROM Subscription WHERE subscriptionId = " + subscriptionID;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();
            statement.execute(query);

            // Execute the query. After executing a Resultset will be stored in this variable
//            ResultSet resultSet = statement.executeQuery(query);

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

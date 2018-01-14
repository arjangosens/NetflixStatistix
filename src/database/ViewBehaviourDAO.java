package database;

import applicationLogic.ViewBehaviour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class ViewBehaviourDAO {
    private DatabaseConnector databaseConnector;

    public ViewBehaviourDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Get a single ViewBehaviour from the database
     *
     * @param viewBehaviourId The ViewBehaviour that is found in the database by the viewBehaviourId
     * @return ViewBehaviour the ViewBehaviour that is found in the database
     */
    public ViewBehaviour getViewBehaviour(int viewBehaviourId) {
        ViewBehaviour viewBehaviour = null;
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to search for ViewBehaviour
            String query = "SELECT * FROM ViewBehaviour WHERE viewBehaviourId = " + viewBehaviourId;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                viewBehaviourId = resultSet.getInt("viewBehaviourId");
                int profileId = resultSet.getInt("profileId");
                int programId = resultSet.getInt("programId");
                double progressPerct = resultSet.getDouble("progressPerct");

                viewBehaviour = new ViewBehaviour(viewBehaviourId, profileId, programId, progressPerct);
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
        return viewBehaviour;
    }

    /**
     * Get all ViewBehaviour objects of a specific profile
     *
     * @param profileId The Id of the Profile that the ViewBehaviours are from
     * @return Set<ViewBehaviour> A Set filled with the connected ViewBehaviours of the specified Profile
     */
    public Set getViewBehavioursOfProfile(int profileId) {
        Set<ViewBehaviour> viewBehaviourSet = new HashSet<ViewBehaviour>();
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to search for ViewBehaviours
            String query = "SELECT * FROM ViewBehaviour WHERE profileId = " + profileId;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int viewBehaviourId = resultSet.getInt("viewBehaviourId");
                profileId = resultSet.getInt("profileId");
                int programId = resultSet.getInt("programId");
                double progressPerct = resultSet.getDouble("progressPerct");

                viewBehaviourSet.add(new ViewBehaviour(viewBehaviourId, profileId, programId, progressPerct));
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
        return viewBehaviourSet;
    }

    /**
     * Update an existing ViewBehaviour in the database1
     *
     * @param profileId The current profileId
     * @param progressPerct The new progressPerct
     */
    public void update(int profileId, double progressPerct) {
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to update ViewBehaviour
            String query = String.format("UPDATE ViewBehaviour SET progressPerct = %f WHERE profileId = %d)",
                    progressPerct,
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
}

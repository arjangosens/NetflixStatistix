package database;

import applicationLogic.ViewBehaviour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
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
    public ArrayList getViewBehavioursOfProfile(int profileId) {
        ArrayList<ViewBehaviour> viewBehaviourSet = new ArrayList<>();
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
     * Insert a ViewBehavior into the database
     *
     * @param profileId The profileId of the new ViewBehaviour
     * @param programId The programId of the new ViewBehaviour
     * @param progressPerct The progressPerct of the new ViewBehaviour
     */
    public void insert(int profileId, int programId, double progressPerct) {
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to insert Profile
            String query = String.format("INSERT INTO ViewBehaviour (profileId, programId, progressPerct) VALUES('%d', '%d', '%f');",
                    profileId,
                    programId,
                    progressPerct);

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
     * Delete an existing ViewBehaviour from the database
     *
     * @param viewBehaviourId The viewBehaviourId of the ViewBehaviour that needs deletion
     */
    public void delete(int viewBehaviourId) {
        Connection connection = null;

        try {
            // Create connection with database
            connection = databaseConnector.getConnection();

            // Form SQL query to delete ViewBehaviour
            String query = "DELETE FROM ViewBehaviour WHERE profileId = " + viewBehaviourId;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();
            statement.execute(query);

            // Execute the query. After executing a Resultset will be stored in this variable
            statement.executeQuery(query);

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

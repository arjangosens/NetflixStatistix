package database;

import applicationLogic.TVshow;

import javax.xml.transform.Result;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class TvShowDAO {

    private DatabaseConnector databaseConnector;

    public TvShowDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Get a single TVShow from the database
     * @param id the id of the TVShow that you would like to get
     * @return TVshow The TVShow that is found in the database by the id
     */
    public TVshow get(int id) {
        // The tvShow that will be returned when it is found.
        TVshow tvshow = null;
        Connection connection = null;

        try {
            // Create a connection
            connection = databaseConnector.getConnection();

            // Form the SQL query to search for the single TVShow.
            String query = "SELECT * FROM TVshow WHERE tvshowId = " + id;

            // Create a statement, without a statement you can not execute the query.
            Statement statement = connection.createStatement();

            // Execute the query. After executing there will be a resultSet stored in this variable.
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int tvShowId = resultSet.getInt("tvshowId");
                int backup = resultSet.getInt("tvshowBackUp");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String language = resultSet.getString("languageTvShow");
                int age = resultSet.getInt("age");

                // Store the new TVshow in the variable.
                tvshow = new TVshow(tvShowId, backup, title, genre, language, age);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch(Exception e) {}
        }

        return tvshow;
    }

    /**
     * Gets all TVshows from the database
     *
     * @return HashSet<TVshow> A HashSet with all tvshows from the database
     */
    public Set<TVshow> getAll() {
        HashSet<TVshow> tVshows = new HashSet<>();
        Connection connection = null;

        try {
            // Get the connection with the database
            connection = databaseConnector.getConnection();

            // Create the SQL query that will be used to find all the tvshows
            String query = "SELECT * FROM TVshow";

            // Create a statement. Without a Statement you can not execute the query.
            Statement statement = connection.createStatement();

            // Store the results of the query in this variable.
            ResultSet resultSet = statement.executeQuery(query);

            // Loop through every result
            while (resultSet.next()) {
                int tvShowId = resultSet.getInt("tvshowId");
                int backup = resultSet.getInt("tvshowBackUp");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String language = resultSet.getString("languageTvShow");
                int age = resultSet.getInt("age");

                tVshows.add(new TVshow(tvShowId, backup, title, genre, language, age));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (Exception e) {e.printStackTrace();}
        }

        return tVshows;
    }
}

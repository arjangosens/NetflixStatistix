package database;

import applicationLogic.TVshow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class TvShowDAO {

    private DatabaseConnector databaseConnector;
    private Connection connection;

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

        try {
            // Create a connection
            connection = databaseConnector.getConnection();

            // Form the SQL query to search for the single TVShow.
            String query = "SELECT * FROM TVshow WHERE tvshowId = " + id;

            // Create a statement, without a statement you can not execute the query.
            Statement statement = connection.createStatement();

            // Execute the query. After executing there will be a resultSet stored in this variable.
            ResultSet resultSet = statement.executeQuery(query);



//            System.out.print(String.format("| %2s | %2s | %20s | %20s | %10s | %2s |\n", " ", " ", " ", " ", " ", " ").replace(" ", "-"));

            while (resultSet.next()) {
                int tvShowId = resultSet.getInt("tvshowId");
                int backup = resultSet.getInt("tvshowBackUp");
                String title = resultSet.getString("title");
                String genre = resultSet.getString("genre");
                String language = resultSet.getString("languageTvShow");
                int age = resultSet.getInt("age");

                tvshow = new TVshow(tvShowId, backup, title, genre, language, age);
//                System.out.format("| %2d | %2d | %20s | %20s | %10s | %2d | \n", tvShowId, backup, title, genre, language, age);
            }

//            System.out.print(String.format("| %2s | %2s | %20s | %20s | %10s | %2s |\n", " ", " ", " ", " ", " ", " ").replace(" ", "-"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch(Exception e) {}
        }

        return tvshow;
    }
}

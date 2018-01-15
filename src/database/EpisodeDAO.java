package database;

import applicationLogic.Episode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class EpisodeDAO {

    private DatabaseConnector databaseConnector;

    public EpisodeDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

//    public Episode get(int id) {
//        Connection connection = databaseConnector.getConnection();
//        Episode episode = null;
//
//        try {
//            // Create SQL query that will be executed
//            String query = "SELECT * FROM Episode";
//
//            // Create Statement. Without statement you can not execute the query
//            Statement statement = connection.createStatement();
//
//            // The results of the executed query will be stored in the ResultSet.
//            ResultSet resultSet = statement.executeQuery(query);
//
//            // Loop through the results as long as there are results
//            while (resultSet.next()) {
//                int episodeId = resultSet.getInt("epidoseId");
//                int programId = resultSet.getInt("programId");
//                int tvshowId = resultSet.getInt("tvshowId");
//                String episodeNr = resultSet.getString("episodeNr");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) try { connection.close(); } catch (Exception e) {e.printStackTrace();}
//        }
//
//        return episode;
//    }

    /**
     * Gets all the episodes from the database
     *
     * @return Set<Episode> A Set filled with all episodes from the database
     */
    public Set<Episode> getAll() {
        // Create connection with the database
        Connection connection = databaseConnector.getConnection();
        Set<Episode> episodes = new HashSet<>();

        try {
            //Form SQL query to search for episodes
            String query = "SELECT episodeId, Episode.programId, Episode.tvshowId, episodeNr, Program.title, duration\n" +
                    "FROM Episode\n" +
                    "JOIN Program ON Episode.programId = Program.programId\n" +
                    "JOIN TVshow ON Episode.tvshowId = TVshow.tvshowId";

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int episodeId = resultSet.getInt("episodeId");
                int programId = resultSet.getInt("programId");
                int tvshowId = resultSet.getInt("tvshowId");
                String episodeNr = resultSet.getString("episodeNr");
                String title = resultSet.getString("title");
                double duration = resultSet.getInt("duration");

                episodes.add(new Episode(episodeId, programId, tvshowId, title, duration, episodeNr));
            }
        } catch (Exception e) {

        } finally {
            if (connection != null) try { connection.close(); } catch (Exception e){ e.printStackTrace();}
        }

        return episodes;
    }
}

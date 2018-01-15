package database;

import applicationLogic.Film;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class FilmDAO {

    private DatabaseConnector databaseConnector;

    public FilmDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * TODO: Change String languae to language
     *
     * Gets all films from the database
     *
     * @return Set<Film> a set containing all films of the database
     */
    public Set<Film> getAll() {

        // Create connection with database
        Connection connection = databaseConnector.getConnection();
        Set<Film> films = new HashSet<>();

        try {
            // Form SQL query to search for films
            String query = "SELECT *\n" +
                    "FROM Film\n" +
                    "JOIN Program ON Film.programId = Program.programId;";

            // Create statement used to execute the query
            Statement statement = connection.createStatement();

            // Execute the query. After executing a Resultset will be stored in this variable
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int filmId = resultSet.getInt("filmId");
                int programId = resultSet.getInt("programId");
                String title = resultSet.getString("title");
                double duration = resultSet.getInt("duration");
                String genre = resultSet.getString("genre");
                String languae = resultSet.getString("filmLanguage");
                int age = resultSet.getInt("age");

                films.add(new Film(filmId, programId, title, duration, genre, languae, age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return films;
    }
}

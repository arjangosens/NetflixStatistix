package database;

import applicationLogic.Episode;
import applicationLogic.Film;
import applicationLogic.Program;
import applicationLogic.TVshow;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProgramDAO {

    private DatabaseConnector databaseConnector;

    public ProgramDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }

    /**
     * Get a single program from the database
     *
     * @param programId The id of the Program that you would like to get
     * @return Program The program that is found in the batabase by the id
     */
    public Program getProgramById(int programId) {
        // Create connection with database
        Connection connection = databaseConnector.getConnection();
        Program program = null;

        try {
            // Form SQL queries to search for Program
            String programQuery = "SELECT * FROM Program WHERE programId = " + programId;
            String episodeQuery = "SELECT * FROM Episode WHERE programId = " + programId;
            String filmQuery = "SELECT * FROM Film WHERE programId = " + programId;

            // Create statement used to execute the query
            Statement statement = connection.createStatement();
            Statement episodeStatement = connection.createStatement();
            Statement filmStatement = connection.createStatement();

            // Execute the query. After executing Resultsets will be stored in these variables
            ResultSet programResultSet = statement.executeQuery(programQuery);
            ResultSet episodeResultSet = episodeStatement.executeQuery(episodeQuery);
            ResultSet filmResultSet = filmStatement.executeQuery(filmQuery);

            int id = 0;
            String title = "";
            double duration = 0.0;

            while (programResultSet.next()) {
                id = programResultSet.getInt("programId");
                title = programResultSet.getString("title");
                duration = programResultSet.getDouble("duration");
            }

            while (episodeResultSet.next()) {
                int episodeId = episodeResultSet.getInt("episodeId");
                int episodeProgramId = episodeResultSet.getInt("programId");
                int tvshowId = episodeResultSet.getInt("tvshowId");
                String episodeNumber = episodeResultSet.getString("episodeNr");

                program =  new Episode(episodeId, episodeProgramId, tvshowId, title, duration, episodeNumber);
            }

            while (filmResultSet.next()) {
                int filmId = filmResultSet.getInt("filmId");
                int filmProgramId = filmResultSet.getInt("programId");
                String genre = filmResultSet.getString("genre");
                String filmLanguage = filmResultSet.getString("filmLanguage");
                int age = filmResultSet.getInt("age");

                program = new Film(filmId, filmProgramId, title, duration, genre, filmLanguage, age);
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

        return program;
    }

}

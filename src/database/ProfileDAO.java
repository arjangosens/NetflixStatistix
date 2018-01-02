package database;

public class ProfileDAO {
    private DatabaseConnector databaseConnector;

    public ProfileDAO(DatabaseConnector databaseConnector) {
        this.databaseConnector = databaseConnector;
    }
}

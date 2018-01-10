package applicationLogic;

import database.DatabaseConnector;
import database.FilmDAO;

import java.util.Set;

public class Film extends Program {

    private int filmId;
    private String genre;
    private String language;
    private int age;

    public Film(int filmId, int programId, String title, double duration, String genre, String language, int age) {
        super(programId, title, duration);
        this.filmId = filmId;
        this.genre = genre;
        this.language = language;
        this.age = age;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public int getAge() {
        return age;
    }

    public static Set<Film> getAll() {
        FilmDAO filmDAO = new FilmDAO(new DatabaseConnector());
        return filmDAO.getAll();
    }
}

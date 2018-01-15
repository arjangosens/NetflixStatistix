package applicationLogic;

import database.DatabaseConnector;
import database.FilmDAO;

import java.util.Set;

public class Film extends Program {

    /**
     * This is the unique id that identifies a single Film.
     */
    private int filmId;

    /**
     * This is the genre for a single film.
     * I hope this film is not a horror film!
     */
    private String genre;

    /**
     * This variable stores the language that is spoken in the film.
     */
    private String language;

    /**
     * This variable stores the minimal age of the film.
     * A film that has a minimal age of 16 is not meant to be viewed by a 6 year old kid.
     */
    private int age;

    public Film(int filmId, int programId, String title, double duration, String genre, String language, int age) {
        super(programId, title, duration);
        this.filmId = filmId;
        this.genre = genre;
        this.language = language;
        this.age = age;
    }

    /**
     * Simple getter method that returns the {@link Film#filmId}.
     * @return {@link Film#filmId}
     */
    public int getFilmId() {
        return filmId;
    }

    /**
     * Simple getter method that returns the {@link Film#genre}.
     * @return {@link Film#genre}
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Simple getter method that returns the {@link Film#language}.
     * @return {@link Film#language}
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Simple getter methdo that returns the {@link Film#age}
     * @return {@link Film#age}
     */
    public int getAge() {
        return age;
    }

    /**
     * Static method that returns all the films that exist in the database.
     * @return a set of {@link Film} objects.
     */
    public static Set<Film> getAll() {
        FilmDAO filmDAO = new FilmDAO(new DatabaseConnector());
        return filmDAO.getAll();
    }
}

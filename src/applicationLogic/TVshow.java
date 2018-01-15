package applicationLogic;

import database.DatabaseConnector;
import database.TvShowDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class TVshow {

    /**
     * The unique identifier for a single {@link TVshow}.
     */
    private int tvshowId;

    /**
     * This variable holds the id for a single {@link TVshow}.
     */
    private int backup;

    /**
     * This is the title for a single {@link TVshow}.
     */
    private String title;

    /**
     * This is the genre for a single {@link TVshow}.
     */
    private String genre;

    /**
     * This is the language that is spoken in the {@link TVshow}.
     */
    private String language;

    /**
     * This is the minimal age for the {@link TVshow}.
     */
    private int age;

    /**
     * This ArrayList holds all the episodes that are linked to the {@link TVshow}.
     */
    private ArrayList<Episode> episodes;

    public TVshow(int tvshowId, int backup, String title, String genre, String language, int age) {
        this.tvshowId = tvshowId;
        this.backup = backup;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.age = age;
    }

    /**
     * Simple getter method to get the {@link TVshow#tvshowId}.
     * @return {@link TVshow#tvshowId}
     */
    public int getTvshowId() {
        return tvshowId;
    }

    /**
     * Simple getter method to get the {@link TVshow#title}
     * @return {@link TVshow#title}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Simple getter method to get the {@link TVshow#genre}.
     * @return {@link TVshow#genre}
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Simple getter method to get the {@link TVshow#language}
     * @return {@link TVshow#language}
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Simple getter methdo to get the {@link TVshow#age}.
     * @return {@link TVshow#age}
     */
    public int getAge() {
        return age;
    }

    /**
     * Simple geter method to get the {@link TVshow#episodes}.
     * @return {@link TVshow#episodes}
     */
    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    /**
     * Simple setter method to set the {@link TVshow#episodes} for a single {@link TVshow}
     * @param episodes
     */
    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    /**
     * This method calls the {@link TvShowDAO} and gets a single tv show from the database.
     * @param id The id that is used to select the {@link TVshow}.
     * @return {@link TVshow}
     */
    public static TVshow get(int id) {
        TvShowDAO tvShowDAO = new TvShowDAO(new DatabaseConnector());
        return tvShowDAO.get(id);
    }

    /**
     * This method calls the {@link TvShowDAO} and gets all the tvShows from the database
     * @return ArrayList with {@link TVshow} objects
     */
    public static ArrayList<TVshow> getAll() {
        TvShowDAO tvShowDAO = new TvShowDAO(new DatabaseConnector());
        Set<TVshow> tvShows = tvShowDAO.getAll();
        ArrayList<TVshow> allTvShows = new ArrayList<>();

        for (TVshow tVshow : tvShows) {
            Collections.addAll(allTvShows, tVshow);
        }

        return allTvShows;
    }

    @Override
    public String toString() {
        return title;
    }
}

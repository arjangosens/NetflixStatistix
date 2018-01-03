package applicationLogic;

import database.DatabaseConnector;
import database.TvShowDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class TVshow {

    private int tvshowId;
    private int backup;
    private String title;
    private String genre;
    private String language;
    private int age;
    private ArrayList<Episode> episodes;

    public TVshow(int tvshowId, int backup, String title, String genre, String language, int age) {
        this.tvshowId = tvshowId;
        this.backup = backup;
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.age = age;
    }

    public int getTvshowId() {
        return tvshowId;
    }

    public String getTitle() {
        return title;
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

    public ArrayList<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(ArrayList<Episode> episodes) {
        this.episodes = episodes;
    }

    public static TVshow get(int id) {
        TvShowDAO tvShowDAO = new TvShowDAO(new DatabaseConnector());
        return tvShowDAO.get(id);
    }

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

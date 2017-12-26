package applicationLogic;

import java.util.ArrayList;

public class TVshow {

    private int tvshowId;
    private String title;
    private String genre;
    private String language;
    private int age;
    private ArrayList<Episode> episodes;
    //TODO: Add object variable for relatedTO

    public TVshow(String title, String genre, String language, int age, ArrayList<Episode> episodes) {
        this.title = title;
        this.genre = genre;
        this.language = language;
        this.age = age;
        this.episodes = episodes;
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
}

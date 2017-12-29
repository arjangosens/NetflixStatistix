package applicationLogic;

import java.util.ArrayList;

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

    @Override
    public String toString() {
        return "TVshow{" +
                "tvshowId=" + tvshowId +
                ", backup=" + backup +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", language='" + language + '\'' +
                ", age=" + age +
                ", episodes=" + episodes +
                '}';
    }
}

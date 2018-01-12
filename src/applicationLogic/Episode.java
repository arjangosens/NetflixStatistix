package applicationLogic;

import database.DatabaseConnector;
import database.EpisodeDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Episode extends Program {

    private int episodeId;
    private int tvShowId;
    private String episodeNumber;
    private TVshow tVshow;

    public Episode(int episodeId, int programId, int tvShowId, String title, double duration, String episodeNumber) {
        super(programId, title, duration);
        this.episodeId = episodeId;
        this.tvShowId = tvShowId;
        this.episodeNumber = episodeNumber;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public int getTvShowId() {
        return tvShowId;
    }

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public TVshow getTvshow() {
        return tVshow;
    }

    public void setTvshow(TVshow tVshow) {
        this.tVshow = tVshow;
    }

    public static ArrayList<Episode> getAll() {
        EpisodeDAO episodeDAO = new EpisodeDAO(new DatabaseConnector());
        Set<Episode> episodes = episodeDAO.getAll();

        ArrayList<Episode> allEpisodes = new ArrayList<>();
        for (Episode episode : episodes) {
            Collections.addAll(allEpisodes, episode);
        }

        return allEpisodes;
    }

    @Override
    public String toString() {
        return getTitle();
    }
}

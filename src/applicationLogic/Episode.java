package applicationLogic;

import database.DatabaseConnector;
import database.EpisodeDAO;

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

    public static Set<Episode> getAll() {
        EpisodeDAO episodeDAO = new EpisodeDAO(new DatabaseConnector());
        return episodeDAO.getAll();
    }

    @Override
    public String toString() {
        return getTitle();
    }
}

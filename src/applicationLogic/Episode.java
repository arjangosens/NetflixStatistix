package applicationLogic;

import database.DatabaseConnector;
import database.EpisodeDAO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Episode extends Program {

    /**
     * This is the unique id for a single episode.
     */
    private int episodeId;

    /**
     * This is the unique id for the tvShow were the episode is linked to.
     */
    private int tvShowId;

    /**
     * This is the well known "S1E1" format for a single episode. The format "S1E1" is an abbreviation for Season 1 Episode 1.
     */
    private String episodeNumber;

    /**
     * This is the actual tvShow were the episode is linked to.
     */
    private TVshow tVshow;

    public Episode(int episodeId, int programId, int tvShowId, String title, double duration, String episodeNumber) {
        super(programId, title, duration);
        this.episodeId = episodeId;
        this.tvShowId = tvShowId;
        this.episodeNumber = episodeNumber;
    }

    /**
     * Simple getter method to get the {@link Episode#episodeId}.
     * @return {@link Episode#episodeId}
     */
    public int getEpisodeId() {
        return episodeId;
    }

    /**
     * Simple getter method to get the {@link Episode#tvShowId}.
     * @return {@link Episode#tvShowId}
     */
    public int getTvShowId() {
        return tvShowId;
    }

    /**
     * Simple getter method to get the {@link Episode#episodeNumber} ("S1E1").
     * @return  {@link Episode#episodeNumber} ("S1E1")
     */
    public String getEpisodeNumber() {
        return episodeNumber;
    }

    /**
     * Simple getter method to get the actual {@link Episode#tVshow} were the episode is linked to.
     * @return {@link Episode#tVshow}
     */
    public TVshow getTvshow() {
        return tVshow;
    }

    /**
     * Simple setter method to set the tvShow for a single episode.
     * if the tvShow is set for a single episode, than the episode is happy because it is not homeless anymore.
     * @param tVshow a sinlge episode will be set the this tvShow.
     */
    public void setTvshow(TVshow tVshow) {
        this.tVshow = tVshow;
    }

    /**
     * Static method that gets all the episodes that exist in the database.
     * @return An episode ArrayList with all the episodes that exist in the database.
     */
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

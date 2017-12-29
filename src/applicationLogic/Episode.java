package applicationLogic;

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

    public String getEpisodeNumber() {
        return episodeNumber;
    }

    public TVshow gettVshow() {
        return tVshow;
    }

    public void settVshow(TVshow tVshow) {
        this.tVshow = tVshow;
    }
    
}

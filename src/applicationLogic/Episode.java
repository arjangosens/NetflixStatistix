package applicationLogic;

public class Episode extends Program {

    private int episodeId;
    private int episodeNumber;
    private TVshow tVshow;

    public Episode(String title, double duration, int episodeNumber, TVshow tVshow) {
        super(title, duration);
        this.episodeNumber = episodeNumber;
        this.tVshow = tVshow;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public TVshow gettVshow() {
        return tVshow;
    }
}

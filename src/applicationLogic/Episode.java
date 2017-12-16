package applicationLogic;

public class Episode extends Program {

    private int episodeId;
    private int episodeNumber;

    public Episode(String title, double duration, int episodeNumber) {
        super(title, duration);
        this.episodeNumber = episodeNumber;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }
}

package applicationLogic;

public abstract class Program {

    private int programId;
    private String title;
    private double duration;

    public Program(int programId, String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }
}

package applicationLogic;

public class Program {

    private String title;
    private double duration;

    public Program(String title) {
        this(title, 0);
    }

    public Program(String title, double duration) {
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

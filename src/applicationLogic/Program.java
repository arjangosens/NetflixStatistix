package applicationLogic;

import database.DatabaseConnector;
import database.ProgramDAO;

public abstract class Program {

    private int programId;
    private String title;
    private double duration;

    public Program(int programId, String title, double duration) {
        this.programId = programId;
        this.title = title;
        this.duration = duration;
    }

    public int getProgramId() {
        return programId;
    }

    public String getTitle() {
        return title;
    }

    public double getDuration() {
        return duration;
    }

    public static Program getProgramById(int programId) {
        ProgramDAO programDAO = new ProgramDAO(new DatabaseConnector());
        return programDAO.getProgramById(programId);
    }
}

package applicationLogic;

import database.DatabaseConnector;
import database.ProgramDAO;

/**
 * This is the abstract parent class for the child classes {@link Film} and {@link Episode}.
 */
public abstract class Program {

    /**
     * This is the unique identifier for a single {@link Program}.
     */
    private int programId;

    /**
     * This is the title for the program.
     */
    private String title;

    /**
     * This is the duration of the program in minutes.
     */
    private double duration;

    public Program(int programId, String title, double duration) {
        this.programId = programId;
        this.title = title;
        this.duration = duration;
    }

    /**
     * Simple getter method to get the {@link Program#programId}.
     * @return {@link Program#programId}
     */
    public int getProgramId() {
        return programId;
    }

    /**
     * Simple getter method to get the {@link Program#title}.
     * @return {@link Program#title}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Simple getter method to get the {@link Program#duration}
     * @return {@link Program#duration}
     */
    public double getDuration() {
        return duration;
    }

    /**
     * The static method that gets a single {@link Program} from the {@link ProgramDAO}.
     * @param programId The {@link Program#programId} that is used to get the program by it's id.
     * @return a single {@link Program}
     */
    public static Program getProgramById(int programId) {
        ProgramDAO programDAO = new ProgramDAO(new DatabaseConnector());
        return programDAO.getProgramById(programId);
    }
}

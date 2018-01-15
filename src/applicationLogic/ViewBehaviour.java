package applicationLogic;

/**
 * The viewBehaviour class is used to store the content that is selected by the database.
 * Later this class is used to get the program and to get the profile.
 * The program will be viewed in a table for the specific userProfile.
 */
public class ViewBehaviour {
    /**
     * The unique identifier for a single viewBehaviour
     */
    private int viewBehaviourId;

    /**
     * The unique identifier for a userProfile.
     */
    private int profileId;

    /**
     * The unique identifier for a program.
     */
    private int programId;

    /**
     * The progress percentage is used to indicate the progress of the program.
     */
    private double progressPerct;

    public ViewBehaviour(int viewBehaviourId, int profileId, int programId, double progressPerct) {
        this.viewBehaviourId = viewBehaviourId;
        this.profileId = profileId;
        this.programId = programId;
        this.progressPerct = progressPerct;
    }

    public int getViewBehaviourId() {
        return viewBehaviourId;
    }

    public void setViewBehaviourId(int viewBehaviourId) {
        this.viewBehaviourId = viewBehaviourId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    /**
     * Simple getter method to get the {@link ViewBehaviour#programId}.
     * @return {@link ViewBehaviour#programId}
     */
    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public double getProgressPerct() {
        return progressPerct;
    }

    public void setProgressPerct(double progressPerct) {
        this.progressPerct = progressPerct;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (getClass() != object.getClass()) {
            return false;
        }

        ViewBehaviour compared = (ViewBehaviour) object;

        if (this.getViewBehaviourId() != compared.getViewBehaviourId()) {
            return false;
        }

        if (this.getProfileId() != compared.getProfileId()) {
            return false;
        }

        if (this.getProgramId() != compared.getProgramId()) {
            return false;
        }

        return true;
    }
}

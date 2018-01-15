package applicationLogic;

public class ViewBehaviour {
    private int viewBehaviourId;
    private int profileId;
    private int programId;
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

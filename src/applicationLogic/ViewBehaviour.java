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
}

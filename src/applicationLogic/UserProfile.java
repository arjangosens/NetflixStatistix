package applicationLogic;

public class UserProfile {
    private int subId;
    private String profileName;
    private int age;
    private ViewBehaviour viewBehaviour;

    public UserProfile(String profileName, int age) {
        this.profileName = profileName;
        this.age = age;
        this.viewBehaviour = new ViewBehaviour();
    }

    public UserProfile(int subId, String profileName, int age) {
        this.subId = subId;
        this.profileName = profileName;
        this.age = age;
        this.viewBehaviour = new ViewBehaviour();
    }

    public int getSubId() {
        return subId;
    }

    public String getProfileName() {
        return profileName;
    }

    public int getAge() {
        return age;
    }

    public ViewBehaviour getViewBehaviour() {
        return viewBehaviour;
    }
}

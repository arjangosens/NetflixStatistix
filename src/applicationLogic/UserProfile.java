package applicationLogic;

public class UserProfile {
    private String profileName;
    private int age;
    private ViewBehaviour viewBehaviour;

    public UserProfile(String profileName, int age) {
        this.profileName = profileName;
        this.age = age;
        this.viewBehaviour = new ViewBehaviour();
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

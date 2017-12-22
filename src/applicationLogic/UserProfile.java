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

}

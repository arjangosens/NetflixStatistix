package applicationLogic;

public class UserProfile {
    private int subscriptionId;
    private String profileName;
    private int age;
    private ViewBehaviour viewBehaviour;

    public UserProfile(int subscriptionId, String profileName, int age) {
        this.subscriptionId = subscriptionId;
        this.profileName = profileName;
        this.age = age;
        this.viewBehaviour = new ViewBehaviour();
    }

    public int getSubscriptionId() {
        return subscriptionId;
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

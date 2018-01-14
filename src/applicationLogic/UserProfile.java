package applicationLogic;

import database.DatabaseConnector;
import database.ProfileDAO;

import java.util.ArrayList;

public class UserProfile {
    private int subId;
    private String profileName;
    private int age;
    private ViewBehaviour viewBehaviour;

//    public UserProfile(int subscriptionId, String profileName, int age) {
//        this.subscriptionId = subscriptionId;
//        this.profileName = profileName;
//        this.age = age;
//        this.viewBehaviour = new ViewBehaviour();
//    }

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

    public static ArrayList<UserProfile> getUserProfilesBySubscriptionId(int subId) {
        ProfileDAO profileDAO = new ProfileDAO(new DatabaseConnector());
        return profileDAO.getProfilesOfSubscription(subId);
    }
}

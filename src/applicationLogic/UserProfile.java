package applicationLogic;

import database.DatabaseConnector;
import database.ProfileDAO;
import database.ViewBehaviourDAO;

import java.util.ArrayList;

public class UserProfile {
    private int subId;
    private int profileId;
    private String profileName;
    private int age;
    private ArrayList<ViewBehaviour> viewBehaviourArrayList;

    public UserProfile(int subId, int profileId, String profileName, int age) {
        this.subId = subId;
        this.profileId = profileId;
        this.profileName = profileName;
        this.age = age;
        this.viewBehaviourArrayList = new ArrayList<>();
    }

    /**
     * Adds a ViewBehaviour object to the ArrayList of this UserProfile
     *
     * @param viewBehaviour The ViewBehaviour that will be added
     */
    public void addViewBehaviour(ViewBehaviour viewBehaviour) {
        if (!viewBehaviourArrayList.contains(viewBehaviour)) {
            viewBehaviourArrayList.add(viewBehaviour);
            System.out.println("viewBehaviour successfully added");
        } else {
            System.out.println("The specified viewBehaviour already exists!");
        }
    }

    /**
     * Removes a specific ViewBehaviour from the ArrayList of this UserProfile
     *
     * @param viewBehaviour The ViewBehaviour that will get removed
     */
    public void deleteViewBehaviour(ViewBehaviour viewBehaviour) {
        if (viewBehaviourArrayList.contains(viewBehaviour)) {
            viewBehaviourArrayList.remove(viewBehaviour);
            System.out.println("Deletion of viewBehaviour successful");
        } else {
            System.out.println("The specified viewBehaviour doesn't exist!");
        }
    }

    /**
     * Updates a specific ViewBehaviour with a new one
     *
     * @param oldVB The ViewBehaviour that needs to change
     * @param newVB The ViewBehaviour that replaces the oldVB
     */
    public void updateViewBehaviour(ViewBehaviour oldVB, ViewBehaviour newVB) {
        for (ViewBehaviour viewBehaviour : viewBehaviourArrayList) {
            if (viewBehaviour.equals(oldVB)) {
                int index = viewBehaviourArrayList.indexOf(viewBehaviour);
                viewBehaviourArrayList.set(index, newVB);
            }
        }
    }

    public int getProfileId() {
        return profileId;
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
    public ArrayList<ViewBehaviour> getViewBehaviourArrayList() {
        return viewBehaviourArrayList;
    }

    public static ArrayList<UserProfile> getUserProfilesBySubscriptionId(int subId) {
        ProfileDAO profileDAO = new ProfileDAO(new DatabaseConnector());
        return profileDAO.getProfilesOfSubscription(subId);
    }

    public static ArrayList<ViewBehaviour> getViewbehaviourByUserProfileId(int profileId) {
        ViewBehaviourDAO viewBehaviourDAO = new ViewBehaviourDAO(new DatabaseConnector());
        return viewBehaviourDAO.getViewBehavioursOfProfile(profileId);
    }
}

package applicationLogic;

import database.DatabaseConnector;
import database.ProfileDAO;
import database.ViewBehaviourDAO;

import java.util.ArrayList;

public class UserProfile {
    /**
     * The unique identifier of the {@link Subscription}.
     */
    private int subId;

    /**
     * The unique identifier for a single {@link UserProfile}. Maybe this {@link UserProfile} is as single as I am...
     */
    private int profileId;

    /**
     * The profile name of the user.
     */
    private String profileName;

    /**
     * The age of the user that owens the profile name.
     */
    private int age;

    /**
     * The list of programs that the user watched on his UserProfile.
     */
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

    /**
     * Simple getter method that returns the {@link UserProfile#profileId}.
     * @return {@link UserProfile#profileId}
     */
    public int getProfileId() {
        return profileId;
    }

    /**
     * Simple getter method that returns the {@link UserProfile#subId}.
     * @return {@link UserProfile#subId}
     */
    public int getSubId() {
        return subId;
    }

    /**
     * Simple getter method that returns the {@link UserProfile#profileName}.
     * @return {@link UserProfile#profileName}
     */
    public String getProfileName() {
        return profileName;
    }

    /**
     * Simple getter method that returns the {@link UserProfile#age}.
     * @return {@link UserProfile#age}
     */
    public int getAge() {
        return age;
    }

    public ArrayList<ViewBehaviour> getViewBehaviourArrayList() {
        return viewBehaviourArrayList;
    }

    /**
     * This method calls the {@link ProfileDAO} and asks the database for as single {@link UserProfile}.
     * @param subId The {@link Subscription#subscriptionId} that is used to get a {@link UserProfile} that belongs to the subscription with this id.
     * @return An ArrayList of {@link UserProfile} objects.
     */
    public static ArrayList<UserProfile> getUserProfilesBySubscriptionId(int subId) {
        ProfileDAO profileDAO = new ProfileDAO(new DatabaseConnector());
        return profileDAO.getProfilesOfSubscription(subId);
    }

    /**
     * This method calls the {@link ViewBehaviourDAO} and asks the database for an ArrayList of {@link ViewBehaviour} object
     * @param profileId The profileId that will be used to get the viewbehaviour for the profile
     * @return An ArrayList with {@link ViewBehaviour} objects
     */
    public static ArrayList<ViewBehaviour> getViewbehaviourByUserProfileId(int profileId) {
        ViewBehaviourDAO viewBehaviourDAO = new ViewBehaviourDAO(new DatabaseConnector());
        return viewBehaviourDAO.getViewBehavioursOfProfile(profileId);
    }
}

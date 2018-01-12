package applicationLogic;

import database.DatabaseConnector;
import database.SubscriptionDAO;

import java.util.ArrayList;

public class Subscription {
    private int subscriptionId;
    private String subName;
    private String street;
    private String houseNumber;
    private String city;
    private ArrayList profiles;

    public Subscription(int subscriptionId, String subName, String street, String houseNumber, String city) {
        this.subscriptionId = subscriptionId;
        this.subName = subName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.profiles = new ArrayList();
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubName() {
        return subName;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public ArrayList getProfiles() {
        return profiles;
    }

    public void createProfile(String profileName, int age) {
        UserProfile profile = new UserProfile(profileName, age);
        profiles.add(profile);
    }

    /**
     * Calls the database method that will select all the connected userProfiles
     * @param id the subscription id that the userProfiles have in common
     * @return ArrayList The arrayList of UserProfiles that will be used in the view
     */
    public static ArrayList<UserProfile> getAllUserProfiles() {
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO(new DatabaseConnector());
        return subscriptionDAO.getAllUserProfiles();
    }
}

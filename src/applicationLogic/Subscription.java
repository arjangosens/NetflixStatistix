package applicationLogic;

import database.DatabaseConnector;
import database.SubscriptionDAO;

import java.util.ArrayList;

//TODO: Delete all the unused methods

public class Subscription {
    /**
     * The unique identifier for a single subscription.
     */
    private int subscriptionId;

    /**
     * This is the name of the user that bought the subscription.
     */
    private String subName;

    /**
     * This is the street name of the user that bought the subscription.
     */
    private String street;

    /**
     * This is the house number of the user that bought the subscription.
     */
    private String houseNumber;

    /**
     * This is the name of the city were the user lives his awesome life.
     */
    private String city;

    /**
     * This variable stores all the profiles that make use of this subscription.
     */
    private ArrayList profiles;

    public Subscription(int subscriptionId, String subName, String street, String houseNumber, String city) {
        this.subscriptionId = subscriptionId;
        this.subName = subName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.profiles = new ArrayList();
    }

    /**
     * Simple getter method to get the {@link Subscription#subscriptionId}.
     * @return {@link Subscription#subscriptionId}
     */
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

    public void createProfile(int profileId, String profileName, int age) {
        UserProfile profile = new UserProfile(subscriptionId, profileId, profileName, age);
        profiles.add(profile);
    }

    /**
     * Calls the database method that will select all the connected userProfiles
     * @return ArrayList The arrayList of {@link UserProfile} that will be used in the view
     */
    public static ArrayList<UserProfile> getAllUserProfiles() {
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO(new DatabaseConnector());
        return subscriptionDAO.getAllUserProfiles();
    }

    /**
     * Calls the database method that will select all the subscriptions that exist on the database.
     * @return an {@link ArrayList} with {@link Subscription} objects
     */
    public static ArrayList<Subscription> getAllSubscriptions() {
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO(new DatabaseConnector());
        return subscriptionDAO.getAll();
    }
}

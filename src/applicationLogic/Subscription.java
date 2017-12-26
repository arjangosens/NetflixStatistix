package applicationLogic;

import java.util.ArrayList;

public class Subscription {
    private String subName;
    private String street;
    private String houseNumber;
    private String city;
    private ArrayList profiles;

    public Subscription(String subName, String street, String houseNumber, String city) {
        this.subName = subName;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.profiles = new ArrayList();
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
}

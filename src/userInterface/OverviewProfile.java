package userInterface;

import applicationLogic.Subscription;
import applicationLogic.UserProfile;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OverviewProfile extends JPanel implements Overview {

    private JComboBox subscriptionDropDown;
    private ArrayList<Subscription> allSubscriptions;
    private JComboBox profileDropDown;
    private ArrayList<UserProfile> allUserProfiles;
    private JTable episodesTable;
    private JTextField nameTextField;
    private JTextField ageTextField;
    private JButton createNewProfileButton;
    private JButton saveChangesButton;
    private JButton deleteProfileButton;
    private String[] columnNames;
    private Object[][] data;

    public OverviewProfile() {
        allSubscriptions = Subscription.getAllSubscriptions();
        createTestData();
        createComponents();
    }

    /**
     * Create the testData for the table.
     * TODO: Fill the profiles object variable in this function.
     */
    private void createTestData() {
        columnNames = new String[]{
                "Watched programs",
                "",
        };

        data = new Object[][]{
                {"Show 1"},
                {"Show 2"},
                {"Show 3"},
        };
    }

    /**
     * Load the data that is viewed in the subscriptionDropDown JComboBox
     */
    private void loadSubscriptionDropDown() {
        // This array holds the id's for that will be viewed in the comboBox.
        List<Integer> subscriptionIDs = new ArrayList<>();

        // Now we update the allSubscriptions ArrayList.
        allSubscriptions = Subscription.getAllSubscriptions();

        // Loop through allSubscriptions to get the subscriptionId.
        for (Subscription subscription : allSubscriptions) {
            // Store the subscriptionId in the arrayList that holds this id's.
            subscriptionIDs.add(subscription.getSubscriptionId());
        }

        // Sort the arrayList
        Collections.sort(subscriptionIDs);

        // And at last, add the id's to the comboBox.
        for (Integer id : subscriptionIDs) {
            subscriptionDropDown.addItem(id);
        }
    }

    /**
     * Load the data that is viewed in the profileDropDown JComboBox.
     */
    private void loadProfileDropDown() {
        List<String> profileNames = new ArrayList<>();
        profileDropDown.removeAllItems();

        allUserProfiles = UserProfile.getUserProfilesBySubscriptionId((int)subscriptionDropDown.getSelectedItem());

        for (UserProfile userProfile : allUserProfiles) {
            profileNames.add(userProfile.getProfileName());
        }

        for (String profileName : profileNames) {
            profileDropDown.addItem(profileName);
        }
    }

    @Override
    public void createComponents() {
        JLabel subscriptionDropDownLabel = new JLabel("Select Subscription:");
        subscriptionDropDown = new JComboBox();
        subscriptionDropDownLabel.setLabelFor(subscriptionDropDown);

        // Fill the subscriptionDropDown with id's
        loadSubscriptionDropDown();

        subscriptionDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadProfileDropDown();
            }
        });

        JLabel profileDropDownLabel = new JLabel("Select Profile:");
        profileDropDown = new JComboBox();
        profileDropDownLabel.setLabelFor(profileDropDown);

        // Fill the profileDropDown with profileNames based on the subscriptionDropDown id
        loadProfileDropDown();

        /**
         * TODO: Create DAO for profiles and convert the commented out code to fit profiles instead of subscriptions
         */
//        // Create DAO for getting all the registered subscriptions
//        SubscriptionDAO getSubs = new SubscriptionDAO(new DatabaseConnector());
//        // Create Set to store subscriptions
//        Set<Subscription> setOfSubs = new HashSet<Subscription>();
//        // Get all subscriptions from the database and add them to the set
//        setOfSubs.addAll(getSubs.getAll());
//
//        // Create Arraylist to store all subscription ID's from the setOfSubs
//        List<Integer> subIDs = new ArrayList<>();
//
//        // Loop through the setOfSubs and add all found subscriptionID's to the arrayList of subID's
//        for (Subscription sub : setOfSubs) {
//            subIDs.add(sub.getSubscriptionId());
//        }
//
//        // Sort all subID's from smallest to greatest
//        Collections.sort(subIDs);
//
//        // Loop through the arrayList of subID's, put each found SubID in the dropdown menu
//        for (Integer subID : subIDs) {
//            profileDropDown.addItem(subID);
//        }

        // This button opens an input screen where users can make a new profile in the current subscription
        createNewProfileButton = new JButton("Create new profile");
        createNewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean isRunning = true;
                // First the input fields are declared (profileName and profileAge)
                JTextField profileName = new JTextField(40);
                JTextField profileAge = new JTextField(40);
                // A new input panel is created here
                JPanel inputPanel = new JPanel();
                // The layout is set to BoxLayout and the positioning is set to the Y_Axis, so the items will be stacked vertically
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
                // Labels are created and added to the input panel
                inputPanel.add(new JLabel("Name:"));
                inputPanel.add(profileName);
                inputPanel.add(new JLabel("Age:"));
                inputPanel.add(profileAge);
                inputPanel.add(new JLabel("NOTE: The new profile will automatically be placed in the currently selected subscription." +
                        "You can change the currently selected subscription in the \'Subscriptions\' overview."));
                // The input panel is shown to the user

                /**
                 * TODO: Convert the commented out code to fit profiles instead of subscriptions
                 */
//                // Create SubDao to enter data into database
//                SubscriptionDAO subDao = new SubscriptionDAO(new DatabaseConnector());
//                // The while loop is used to show the inputfields again in case of wrongly entered data
//                while (isRunning) {
//                    try {
//                        // Show input fields to user where he can enter his subscription information
//                        int n = JOptionPane.showConfirmDialog(null, inputPanel, "Enter your information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
//                        // If user presses cancel or exit button, close dialog
//                        if (n == JOptionPane.CANCEL_OPTION || n == JOptionPane.CLOSED_OPTION) {
//                            isRunning = false;
//                            break;
//                        }
//                        // Check if all fields have been filled in
//                        if (profileName.getText().length() > 0 && streetName.getText().length() > 0 && houseNumber.getText().length() > 0 && houseNumber.getText().length() <= 5 && city.getText().length() > 0) {
//                            // Call the insert() method, which inserts the data into the Subscription table in the database
//                            subDao.insert(profileName.getText(), streetName.getText(), houseNumber.getText(), city.getText());
//                            isRunning = false;
//                            // Check if the entered houseNumber doesn't exceed the limit. If it does, show an error message
//                        } else if (houseNumber.getText().length() > 5) {
//                            JOptionPane.showMessageDialog(inputPanel, "The housenumber can only be 5 characters long");
//                        } else {
//                            // If the fields are empty, show an error message
//                            JOptionPane.showMessageDialog(inputPanel, "These fields cannot be empty");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        });

        episodesTable = new JTable();
        DefaultTableModel defaultTableModel = (DefaultTableModel) episodesTable.getModel();
        defaultTableModel.setDataVector(data, columnNames);
        JScrollPane jScrollPane = new JScrollPane(episodesTable);

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField("[NAME]", 10);
        nameLabel.setLabelFor(nameTextField);

        JLabel ageLabel = new JLabel("Age:");
        ageTextField = new JTextField("[AGE]", 10);
        nameLabel.setLabelFor(ageTextField);


        // This button should save the input from the textfields above.
        saveChangesButton = new JButton("Save changes");

        /**
         * TODO: Convert the commented out code to fit profiles instead of subscriptions
         */
//        saveChangesButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                SubscriptionDAO subDao = new SubscriptionDAO(new DatabaseConnector());
//                try {
//                    int subID = Integer.parseInt(profileDropDown.getSelectedItem().toString());
//                    String subName = nameTextField.getText();
//                    String subStreet = streetTextField.getText();
//                    String houseNumber = houseNrTextField.getText();
//                    String city = cityTextField.getText();
//
//                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", "", JOptionPane.OK_CANCEL_OPTION);
//                    if (confirm == JOptionPane.YES_OPTION) {
//                        subDao.update(subName, subStreet, houseNumber, city, subID);
//                        System.out.println("Subscription information updated");
//                    }
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        // This button should delete the currently selected subscription from the database (and the application).
        deleteProfileButton = new JButton("Delete");

        /**
         * TODO: Convert the commented out code to fit profiles instead of subscriptions
         */
//        deleteProfileButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//                SubscriptionDAO subDAO = new SubscriptionDAO(new DatabaseConnector());
//                try {
//                    // Get the selected Subscription ID
//                    int subID = Integer.parseInt(profileDropDown.getSelectedItem().toString());
//                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", "", JOptionPane.OK_CANCEL_OPTION);
//                    if (confirm == JOptionPane.YES_OPTION) {
//                        subDAO.delete(subID);
//                        System.out.println("Subscription deleted");
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        this.add(subscriptionDropDownLabel);
        this.add(subscriptionDropDown);

        this.add(profileDropDownLabel);
        this.add(profileDropDown);

        this.add(createNewProfileButton);

        this.add(jScrollPane);

        this.add(nameLabel);
        this.add(nameTextField);
        this.add(ageLabel);
        this.add(ageTextField);

        this.add(saveChangesButton);
        this.add(deleteProfileButton);
    }
}


package userInterface;

import applicationLogic.*;
import database.DatabaseConnector;
import database.ProfileDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OverviewProfile extends JPanel implements Overview {

    private JComboBox subscriptionDropDown;
    private ArrayList<Subscription> allSubscriptions;
    private JComboBox profileDropDown;
    private ArrayList<UserProfile> allUserProfiles;
    private JTable viewBehaviourTable;
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
                "EpisodeNumber",
                "Title",
                "Genre",
                "Language",
                "Age",
                "Duration"
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

    public void loadViewBehaviour() {
        UserProfile currentUserProfile = null;
        ArrayList<ViewBehaviour> viewBehaviours = new ArrayList<>();

        for (UserProfile userProfile : allUserProfiles) {
            if (userProfile.getProfileName().equals(profileDropDown.getSelectedItem())) {
                currentUserProfile = userProfile;
            }
        }

        viewBehaviours.clear();
        viewBehaviours = UserProfile.getViewbehaviourByUserProfileId(currentUserProfile.getProfileId());

        for (ViewBehaviour viewBehaviour : viewBehaviours) {
            currentUserProfile.addViewBehaviour(viewBehaviour);
        }

        data = new Object[viewBehaviours.size()][6];
        for (int i = 0; i < viewBehaviours.size(); i++) {
            Program program = Program.getProgramById(viewBehaviours.get(i).getProgramId());

            Object[] y = new Object[6];
            if (program instanceof Episode) {


                y[0] = ((Episode) program).getEpisodeNumber();
                y[1] = ((Episode) program).getTitle();

                TVshow tVshow = TVshow.get(((Episode) program).getTvShowId());
                y[2] = tVshow.getGenre();
                y[3] = tVshow.getLanguage();
                y[4] = tVshow.getAge();
                y[5] = program.getDuration();

                data[i] = y;
            } else {
                Film film = (Film) program;

                y[0] = "";
                y[1] = film.getTitle();
                y[2] = film.getGenre();
                y[3] = film.getLanguage();
                y[4] = film.getAge();
                y[5] = film.getDuration();

                data[i] = y;
            }
        }
    }

    private void createProfileDialog() {
        boolean isRunning = true;

        // Create input fields for entering new profile data (Name and age)
        JTextField profileName = new JTextField(40);
        JTextField age = new JTextField(3);

        // Create input panel for entering profile data
        JPanel inputPanel = new JPanel();
        // Set the layout to BoxLayout and let it the inputboxes align vertically
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        // Create labels to placed above the corresponding textfield and add them to the inputPanel
        inputPanel.add(new JLabel("Name: "));
        inputPanel.add(profileName);
        inputPanel.add(new JLabel("Age: "));
        inputPanel.add(age);


        // Create ProfileDao to enter data into the database
        ProfileDAO profileDao = new ProfileDAO(new DatabaseConnector());
        // The while loop is used to show the inputfields again in case of wrongly entered data
        while (isRunning) {
            try {
                // Show input fields to user where he can enter his profile information
                int n = JOptionPane.showConfirmDialog(null, inputPanel, "Enter your information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                // If user presses cancel or exit button, close dialog
                if (n == JOptionPane.CANCEL_OPTION || n == JOptionPane.CLOSED_OPTION) {
                    isRunning = false;
                }
                // Check if all fields have been filled in
                if (profileName.getText().length() > 0 && profileName.getText().length() <= 40 && age.getText().length() > 0 && age.getText().length() <= 3) {

                    // Call the insert() method, which inserts the data into the UserProfile table in the database
                    profileDao.insert(subscriptionDropDown.getSelectedIndex(), profileName.getText(), Integer.parseInt(age.getText()));
                    isRunning = false;
                } else {
                    // If the fields are empty, show an error message
                    JOptionPane.showMessageDialog(inputPanel, "These fields cannot be empty");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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

        // This button opens an input screen where users can make a new profile in the current subscription
        createNewProfileButton = new JButton("Create new profile");
        createNewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                createProfileDialog();
            }
        });

        viewBehaviourTable = new JTable();
        DefaultTableModel defaultTableModel = (DefaultTableModel) viewBehaviourTable.getModel();

        loadViewBehaviour();

        profileDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (profileDropDown.getSelectedItem() != null)
                    loadViewBehaviour();

                defaultTableModel.setDataVector(data, columnNames);
            }
        });

        defaultTableModel.setDataVector(data, columnNames);
        JScrollPane jScrollPane = new JScrollPane(viewBehaviourTable);

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


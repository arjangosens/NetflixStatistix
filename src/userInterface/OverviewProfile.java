package userInterface;

import applicationLogic.*;
import database.DatabaseConnector;
import database.ProfileDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
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
        // Define ArrayList to store subscriptions, add all subscriptions found in the database to the ArrayList
        allSubscriptions = Subscription.getAllSubscriptions();
        // Call createTestData()
        createTestData();
        createComponents();
    }

    /**
     * Create the testData for the table.
     * TODO: Change createTestData name to createColumnData
     */
    private void createTestData() {
        // Define String Array to store column names
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
        // This array holds the subscription id's that will be shown in the Subscription dropdown-menu.
        List<Integer> subscriptionIDs = new ArrayList<>();

        // Fill the Subscription ArrayList with all the subscriptions from the database
        allSubscriptions = Subscription.getAllSubscriptions();

        // Loop through allSubscriptions to get the subscriptionId.
        for (Subscription subscription : allSubscriptions) {
            // Store the subscriptionId in the arrayList that holds this id's.
            subscriptionIDs.add(subscription.getSubscriptionId());
        }

        // Sort the arrayList
        Collections.sort(subscriptionIDs);

        // And at last, add the id's to the Subscription dropdownmenu.
        for (Integer id : subscriptionIDs) {
            subscriptionDropDown.addItem(id);
        }
    }

    /**
     * Load the data that is viewed in the profileDropDown JComboBox.
     */
    private void loadProfileDropDown() {
        // Define ArrayList to store profileNames
        List<String> profileNames = new ArrayList<>();
        // Clear the dropdown menu
        profileDropDown.removeAllItems();

        // Store all userprofiles which correspond to the selected Subscription
        allUserProfiles = UserProfile.getUserProfilesBySubscriptionId((int)subscriptionDropDown.getSelectedItem());

        // Loop through all UserProfiles
        for (UserProfile userProfile : allUserProfiles) {
            // Add all UserProfile names to the list of ProfileNames
            profileNames.add(userProfile.getProfileName());
        }

        // Loop through profileName list
        for (String profileName : profileNames) {
            // Add all profileNames to the profile dropdown-menu
            profileDropDown.addItem(profileName);
        }
    }

    public void loadViewBehaviour() {
        // Define a UserProfile object
        UserProfile currentUserProfile = null;
        // Define ArrayList to store all viewBehaviour
        ArrayList<ViewBehaviour> viewBehaviours = new ArrayList<>();

        // Loop through list of UserProfiles
        for (UserProfile userProfile : allUserProfiles) {
            // Check if UserProfile name is equal to the selected profileName from the dropdown menu
            if (userProfile.getProfileName().equals(profileDropDown.getSelectedItem())) {
                // Set currentUserProfile object to userProfile
                currentUserProfile = userProfile;
            }
        }

        // Clear the viewBehaviour list
        viewBehaviours.clear();
        // get viewbehaviour list from database and store it in this variable
        viewBehaviours = UserProfile.getViewbehaviourByUserProfileId(currentUserProfile.getProfileId());

        // Loop through the list of viewbehaviour
        for (ViewBehaviour viewBehaviour : viewBehaviours) {
            // Add every viewbehaviour object to the viewBehaviour list
            currentUserProfile.addViewBehaviour(viewBehaviour);
        }

        // Define object array to store viewbehaviour table data
        data = new Object[viewBehaviours.size()][6];
        // Loop through viewBehaviour array
        for (int i = 0; i < viewBehaviours.size(); i++) {
            // Define program object and get the program corresponding to programID
            Program program = Program.getProgramById(viewBehaviours.get(i).getProgramId());

            // Define object array to store viewBehaviour information
            Object[] y = new Object[6];
            // Check if program is an instance of Episode
            if (program instanceof Episode) {
                // Set the first column to the Episodenumber
                y[0] = ((Episode) program).getEpisodeNumber();
                // Set the second column to the title of the Episode
                y[1] = ((Episode) program).getTitle();
                // Define a TVshow object to show in the table
                TVshow tVshow = TVshow.get(((Episode) program).getTvShowId());
                // Set the third column to the genre of the TvShow
                y[2] = tVshow.getGenre();
                // Set the fourth column to the language of the TvShow
                y[3] = tVshow.getLanguage();
                // Set the fifth column to the age of the TvShow
                y[4] = tVshow.getAge();
                // Set the sixth column to the duration of the Episode
                y[5] = program.getDuration();

                // Set the viewBehaviour table data
                data[i] = y;
            } else {
                // Define new Film object
                Film film = (Film) program;

                // Set first column to be empty
                y[0] = "";
                // Set the second column to the title of the Film
                y[1] = film.getTitle();
                // Set the third column to the genre of the Film
                y[2] = film.getGenre();
                // Set the fourth column to the language of the Film
                y[3] = film.getLanguage();
                // Set the fifth column to the age of the Film
                y[4] = film.getAge();
                // Set the sixth column to the duration of the film
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
                    profileDao.insert(Integer.parseInt(subscriptionDropDown.getSelectedItem().toString()), profileName.getText(), Integer.parseInt(age.getText()));
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

    private void deleteUserProfile(int profileID) {
        ProfileDAO profileDao = new ProfileDAO(new DatabaseConnector());
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this profile?", "", JOptionPane.OK_CANCEL_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            profileDao.delete(profileID);
            loadProfileDropDown();
        }
    }

    private void updateUserProfile(int profileID , JTextField name, JTextField age) {
        ProfileDAO profileDAO = new ProfileDAO(new DatabaseConnector());
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this profile?", "", JOptionPane.OK_CANCEL_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            profileDAO.update(name.getText().toString(), Integer.parseInt(age.getText().toString()), profileID);
        }
    }

    @Override
    public void createComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

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
                loadProfileDropDown();
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
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                allUserProfiles = UserProfile.getUserProfilesBySubscriptionId((int)subscriptionDropDown.getSelectedItem());
                for (UserProfile profile : allUserProfiles) {
                if (profile.getProfileName().equals(profileDropDown.getSelectedItem().toString())) {
                    int subID = Integer.parseInt(subscriptionDropDown.getSelectedItem().toString());
                    int profileID = profile.getProfileId();
                        updateUserProfile(profileID, nameTextField, ageTextField);
                        loadProfileDropDown();
                    }
                }
            }
        });



        // This button should delete the currently selected subscription from the database (and the application).
        deleteProfileButton = new JButton("Delete");
        deleteProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                allUserProfiles = UserProfile.getUserProfilesBySubscriptionId((int)subscriptionDropDown.getSelectedItem());
                for (UserProfile profile : allUserProfiles) {
                    if (profile.getProfileName().equals(profileDropDown.getSelectedItem().toString())) {
                        deleteUserProfile(profile.getProfileId());
                        loadProfileDropDown();
                    }
                }
            }
        });


        constraints.insets = new Insets(0,0,0,80);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(subscriptionDropDownLabel, constraints);

        constraints.insets = new Insets(0,80,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(subscriptionDropDown, constraints);

        constraints.insets = new Insets(0,0,0,80);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(profileDropDownLabel, constraints);

        constraints.insets = new Insets(0,80,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(profileDropDown, constraints);

        constraints.insets = new Insets(0,0,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(createNewProfileButton, constraints);

        constraints.insets = new Insets(10,0,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 4;
        this.add(jScrollPane, constraints);

        constraints.insets = new Insets(10,0,0,80);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        this.add(nameLabel, constraints);

        constraints.insets = new Insets(10,80,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        this.add(nameTextField, constraints);

        constraints.insets = new Insets(10,0,0,80);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        this.add(ageLabel, constraints);

        constraints.insets = new Insets(10,80,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        this.add(ageTextField, constraints);

        constraints.insets = new Insets(10,0,0,0);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        this.add(saveChangesButton, constraints);

        constraints.insets = new Insets(10,0,0,0);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        this.add(deleteProfileButton, constraints);
    }
}


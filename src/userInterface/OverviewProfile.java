package userInterface;

import applicationLogic.*;
import database.DatabaseConnector;
import database.ProfileDAO;
import database.SubscriptionDAO;

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

    private static OverviewProfile instance = null;

    public static OverviewProfile getInstance() {
        if (instance == null) {
            instance = new OverviewProfile();
        }
        return instance;
    }

    private OverviewProfile() {
        // Define ArrayList to store subscriptions, add all subscriptions found in the database to the ArrayList
        allSubscriptions = Subscription.getAllSubscriptions();
        // Call createTestData()
        createTestData();
        createComponents();
    }

    public void loadEverything() {
        loadSubscriptionDropDown();
        loadProfileDropDown();
        loadViewBehaviour();
        loadProfileInfo();
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
        subscriptionDropDown.removeAllItems();

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
        if (subscriptionDropDown.getSelectedItem() != null)
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
        // Create profileDAO to connect to the database
        ProfileDAO profileDao = new ProfileDAO(new DatabaseConnector());
        // Show a confirmation dialog asking the user to confirm their action
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this profile?", "", JOptionPane.OK_CANCEL_OPTION);
        // Check if the user clicked the "OK" button
        if (confirm == JOptionPane.YES_OPTION) {
            // Call the delete() method from the ProfileDAO, which deletes the given Profile from the database
            profileDao.delete(profileID);
            // Reload the profileDropdown menu
            loadProfileDropDown();
        }
    }

    private void updateUserProfile(int profileID , JTextField name, JTextField age) {
        // Create profileDAO to connect to the database
        ProfileDAO profileDAO = new ProfileDAO(new DatabaseConnector());
        // Show a confirmation dialog asking the user to confirm their action
        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this profile?", "", JOptionPane.OK_CANCEL_OPTION);
        // Check if the user clicked the "OK" button
        if (confirm == JOptionPane.YES_OPTION) {
            // Call the update() method from the ProfileDAO, which update the given Profile in the database
            profileDAO.update(name.getText().toString(), Integer.parseInt(age.getText().toString()), profileID);
        }
    }

    /**
     * This method loads the info of the current selected profile and shows it in the input fields.
     */
    private void loadProfileInfo() {
        UserProfile userProfile = null;

        for (UserProfile u : allUserProfiles) {
            if (u.getProfileName().equals(profileDropDown.getSelectedItem())) {
                userProfile = u;
            }
        }

        nameTextField.setText(userProfile.getProfileName());
        ageTextField.setText(userProfile.getAge() + "");
    }

    @Override
    public void createComponents() {
        // Set the layout of the overViewProfile to GridbagLayout
        setLayout(new GridBagLayout());
        // Define new GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();

        // Define a label for the Subscription dropdown-menu
        JLabel subscriptionDropDownLabel = new JLabel("Select Subscription:");
        // Define a new combobox which is used as dropdown menu to show Subscriptions
        subscriptionDropDown = new JComboBox();
        // Set the label for the Subscription dropdown-menu
        subscriptionDropDownLabel.setLabelFor(subscriptionDropDown);

        // Fill the subscriptionDropDown with id's
        loadSubscriptionDropDown();

        // Add an ActionListener to the Subscriptions dropdown-menu
        subscriptionDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Load the profiles in the dropdown menu corresponding to the Subscription ID
                loadProfileDropDown();
            }
        });

        // Define a label for the UserProfiles dropdown-menu
        JLabel profileDropDownLabel = new JLabel("Select Profile:");
        // Define a new combobox which is used as dropdown menu to show UserProfiles
        profileDropDown = new JComboBox();
        // Set the label for the UserProfiles dropdown-menu
        profileDropDownLabel.setLabelFor(profileDropDown);

        // Fill the profileDropDown with profileNames based on the subscriptionDropDown id
        loadProfileDropDown();


        // This button opens an input screen where users can make a new profile in the current subscription
        createNewProfileButton = new JButton("Create new profile");
        // Add ActionListener to createNewProfile button
        createNewProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Calls createProfileDialog, which opens a dialog in which users can enter information to create a new UserProfile
                createProfileDialog();
                // Reload the UserProfile dropdown-menu
                loadProfileDropDown();
            }
        });

        // Define a new table for to show the viewBehaviour
        viewBehaviourTable = new JTable();
        // Define a table model which is to be used by the viewBehaviourTable
        DefaultTableModel defaultTableModel = (DefaultTableModel) viewBehaviourTable.getModel();

        // Load the viewBehaviourTable data
        loadViewBehaviour();

        // Add an ActionListener to the profileDropDown-menu
        profileDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if there is a profile selected in the profileDropDown-menu
                if (profileDropDown.getSelectedItem() != null) {
                    // Load the viewBehaviour from the selected UserProfile
                    loadViewBehaviour();
                    loadProfileInfo();
                }

                // Fill the table with the columnnames and data
                defaultTableModel.setDataVector(data, columnNames);
            }
        });

        // Fill the table with the columnnames and data
        defaultTableModel.setDataVector(data, columnNames);
        // Define a new jScrollPane, which makes the table scrollable and adds a scrollbar
        JScrollPane jScrollPane = new JScrollPane(viewBehaviourTable);

        // Define a label for the name input field
        JLabel nameLabel = new JLabel("Name:");
        // Define a nameTextField in which the user can enter their name
        nameTextField = new JTextField("[NAME]", 10);
        // Add the label to the nameTextField
        nameLabel.setLabelFor(nameTextField);

        // Define a label for the age input field
        JLabel ageLabel = new JLabel("Age:");
        // Define an ageTextField in which the user can enter their age
        ageTextField = new JTextField("[AGE]", 10);
        // Add the label to the ageTextField
        nameLabel.setLabelFor(ageTextField);


        // This button saves the input from the textfields above.
        saveChangesButton = new JButton("Save changes");
        // Add an ActionListener to the saveChanges button
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent){
                // Define ArrayList of UserProfiles and store all UserProfiles from the database in this list
                allUserProfiles = UserProfile.getUserProfilesBySubscriptionId((int)subscriptionDropDown.getSelectedItem());
                // Loop through the list of UserProfiles
                for (UserProfile profile : allUserProfiles) {
                // Check if the profileName in the list is equal to the currently selected profile in the profileDropDown-menu
                if (profile.getProfileName().equals(profileDropDown.getSelectedItem().toString())) {
                    // Define and store the Subscription ID, which is gotten from the currently selected item in the Subscription dropdown-menu
                    int subID = Integer.parseInt(subscriptionDropDown.getSelectedItem().toString());
                    // Define and store the profileID
                    int profileID = profile.getProfileId();
                    // Call the updateUserProfile method, which updates the profile with the newly entered information
                        updateUserProfile(profileID, nameTextField, ageTextField);
                        // Reload the profile dropdown-menu
                        loadProfileDropDown();
                    }
                }
            }
        });



        // This button delets the currently selected subscription from the database (and the application).
        deleteProfileButton = new JButton("Delete");
        // Add ActionListener to the delete button
        deleteProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // Get all UserProfiles from the database and store them in allUserProfiles ArrayList
                allUserProfiles = UserProfile.getUserProfilesBySubscriptionId((int)subscriptionDropDown.getSelectedItem());
                // Loop through list of UserProfiles
                for (UserProfile profile : allUserProfiles) {
                    // Check if profileName in list is equal to the currently selected item in the profileDropdown-menu
                    if (profile.getProfileName().equals(profileDropDown.getSelectedItem().toString())) {
                        // Call the deleteUserProfile() method, which deletes the profile corresponding to the given profileID from the database
                        deleteUserProfile(profile.getProfileId());
                        // Reload the profileDropDown-menu
                        loadProfileDropDown();
                    }
                }
            }
        });

        loadProfileInfo();

        // Set the external padding between the SubscriptionDropdownLabel and the edges of the layout
        constraints.insets = new Insets(0,0,0,80);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 0;
        // Set top most cell location of the component
        constraints.gridy = 0;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the Subscriptiondropdown-label and the constraints to the Profile overview page
        this.add(subscriptionDropDownLabel, constraints);

        // Set the external padding between the SubscriptionDropdown-menu and the edges of the layout
        constraints.insets = new Insets(0,80,0,0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 0;
        // Set top most cell location of the component
        constraints.gridy = 0;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the Subscriptiondropdown-menu and the constraints to the Profile overview page
        this.add(subscriptionDropDown, constraints);

        // Set the external padding between the profileDropDown-label and the edges of the layout
        constraints.insets = new Insets(0,0,0,80);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 1;
        // Set top most cell location of the component
        constraints.gridy = 0;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the profileDropDown-label and the constraints to the Profile overview page
        this.add(profileDropDownLabel, constraints);

        // Set the external padding between the profileDropDown-menu and the edges of the layout
        constraints.insets = new Insets(0,80,0,0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 1;
        // Set top most cell location of the component
        constraints.gridy = 0;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the profileDropDown-menu and the constraints to the Profile overview page
        this.add(profileDropDown, constraints);

        // Set the external padding between the profileDropDown-label and the edges of the layout
        constraints.insets = new Insets(0,0,0,0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 2;
        // Set top most cell location of the component
        constraints.gridy = 0;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the createNeProfile-button and the constraints to the Profile overview page
        this.add(createNewProfileButton, constraints);

        // Set the external padding between the jScrollPane and the edges of the layout
        constraints.insets = new Insets(10,0,0,0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 0;
        // Set top most cell location of the component
        constraints.gridy = 3;
        // Set number of cells in a row
        constraints.gridwidth = 4;
        // Add the jScrollPane and the constraints to the Profile overview page
        this.add(jScrollPane, constraints);

        // Set the external padding between the nameLabel and the edges of the layout
        constraints.insets = new Insets(10,0,0,80);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 0;
        // Set top most cell location of the component
        constraints.gridy = 4;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the nameLabel and the constraints to the Profile overview page
        this.add(nameLabel, constraints);

        // Set the external padding between the nameTextField and the edges of the layout
        constraints.insets = new Insets(10,80,0,0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 0;
        // Set top most cell location of the component
        constraints.gridy = 4;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the nameTextField and the constraints to the Profile overview page
        this.add(nameTextField, constraints);

        // Set the external padding between the ageLabel and the edges of the layout
        constraints.insets = new Insets(10,0,0,80);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 1;
        // Set top most cell location of the component
        constraints.gridy = 4;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the ageLabel and the constraints to the Profile overview page
        this.add(ageLabel, constraints);

        // Set the external padding between the ageTextField and the edges of the layout
        constraints.insets = new Insets(10,80,0,0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 1;
        // Set top most cell location of the component
        constraints.gridy = 4;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the ageTextField and the constraints to the Profile overview page
        this.add(ageTextField, constraints);

        // Set the external padding between the saveChangesButton and the edges of the layout
        constraints.insets = new Insets(10,0,0,0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.EAST;
        // Set leading cell location of the component
        constraints.gridx = 0;
        // Set top most cell location of the component
        constraints.gridy = 5;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the saveChangesButton and the constraints to the Profile overview page
        this.add(saveChangesButton, constraints);

        // Set the external padding between the deleteProfileButton and the edges of the layout
        constraints.insets = new Insets(10,0,0,0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.EAST;
        // Set leading cell location of the component
        constraints.gridx = 1;
        // Set top most cell location of the component
        constraints.gridy = 5;
        // Set number of cells in a row
        constraints.gridwidth = 1;
        // Add the deleteProfileButton and the constraints to the Profile overview page
        this.add(deleteProfileButton, constraints);
    }
}


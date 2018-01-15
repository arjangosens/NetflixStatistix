package userInterface;

import applicationLogic.Subscription;
import applicationLogic.UserProfile;
import database.DatabaseConnector;
import database.SubscriptionDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.CopyOnWriteArraySet;

public class OverviewSubscriptions extends JPanel implements Overview {

    private JComboBox subsDropDown;
    private JTable connectedProfilesJTable;
    private JTextField nameTextField;
    private JTextField streetTextField;
    private JTextField houseNrTextField;
    private JTextField cityTextField;
    private JButton createNewSubButton;
    private JButton saveChangesButton;
    private JButton deleteSubButton;
    private ArrayList<UserProfile> allUserProfiles;
    private List<Subscription> allSubscriptions;
    private String[] columnNames;
    private Object[][] data;

    private static OverviewSubscriptions instance = null;

    public static OverviewSubscriptions getInstance() {
        if (instance == null)
            instance = new OverviewSubscriptions();

        return instance;
    }

    private OverviewSubscriptions() {
        // Define and fill the allUserProfiles ArrayList with all UserProfiles found in the database
        allUserProfiles = Subscription.getAllUserProfiles();
        // Define and fill the allSubscriptions ArrayList with all Subscriptions found in the database
        allSubscriptions = Subscription.getAllSubscriptions();
        // Call createColumnNames(), which creates the column names which are to be shown
        createCulumnNames();
        // Call createComponents(), which creates all gui-components
        createComponents();
    }

    /**
     * Create the testData for the table.
     */
    private void createCulumnNames() {
        // Define String Array to store the columnNames which are to be shown in the Subscription Overview table
        columnNames = new String[]{
                "Connected profiles",
                "Age"
        };
    }

    /**
     * Loads everything in the page.
     */
    public void loadEverything() {
        loadSubscriptionComboboxData();
        if (subsDropDown.getSelectedItem() != null) {
            loadConnectedProfiles((int)subsDropDown.getSelectedItem());
            loadSubscriberInfo((int)subsDropDown.getSelectedItem());
        }
    }

    /**
     * Load the table data with the connected UserProfiles
     */
    private void loadConnectedProfiles(int subId) {
        allUserProfiles.clear();
        allUserProfiles = Subscription.getAllUserProfiles();
        
        // Define ArrayList to store all connected UserProfiles
        ArrayList<UserProfile> connectedUserProfiles = new ArrayList<>();

        // Loop through the list of UserProfiles
        for (UserProfile userProfile : allUserProfiles) {
            // Check if the subID given as parameter is equal to the userProfiles subID
            if (subId == userProfile.getSubId()) {
                // Add the found UserProfile to the list of connectedUserProfiles
                connectedUserProfiles.add(userProfile);
            }
        }

        // Define Object Array to store connectedUserProfiles, which is used to show as table data
        data = new Object[connectedUserProfiles.size()][2];
        // Loop through the
        for (int i = 0; i < connectedUserProfiles.size(); i++) {
            Object[] y = new Object[2];

            y[0] = connectedUserProfiles.get(i).getProfileName();
            y[1] = connectedUserProfiles.get(i).getAge();

            data[i] = y;
        }
    }

    private void loadSubscriptionComboboxData() {
        // We need to empty the comboBox, else some of the id's will be duplicated.
        subsDropDown.removeAllItems();
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
            subsDropDown.addItem(id);
        }
    }

    private void loadSubscriberInfo(int subId) {
        // Create connection with database
        SubscriptionDAO subDAO = new SubscriptionDAO(new DatabaseConnector());
        // Get the selected SubscriptionID
        Subscription sub = subDAO.getSubscription(subId);
        // Set the value of nameTextField to the name of the currently selected Subscription
        nameTextField.setText(sub.getSubName());
        // Set the value of streetTextField to the Street of the currently selected Subscription
        streetTextField.setText(sub.getStreet());
        // Set the value of houseNrTextField to the housenumber of the currently selected Subscription
        houseNrTextField.setText(sub.getHouseNumber());
        // Set the value of cityTextField to the city of the currently selected Subscription
        cityTextField.setText(sub.getCity());
    }

    @Override
    public void createComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel subsDropDownLabel = new JLabel("Select subscription ");
        subsDropDown = new JComboBox();
        subsDropDownLabel.setLabelFor(subsDropDown);

        constraints.insets = new Insets(0, 0, 5, 20);
        constraints.anchor = GridBagConstraints.WEST;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(subsDropDownLabel, constraints);

        constraints.insets = new Insets(0, 0, 5, 10);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(subsDropDown, constraints);

        loadSubscriptionComboboxData();

        // This button opens an input screen where users can make a new subscription
        createNewSubButton = new JButton("Create new subscription");
        createNewSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean isRunning = true;
                // First the input fields are declared (subName, streetName, houseNumber, city)
                JTextField subName = new JTextField(40);
                JTextField streetName = new JTextField(40);
                JTextField houseNumber = new JTextField(40);
                JTextField city = new JTextField(40);
                // A new input panel is created here
                JPanel inputPanel = new JPanel();
                // The layout is set to BoxLayout and the positioning is set to the Y_Axis, so the items will be stacked vertically
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
                // Labels are created and added to the input panel
                inputPanel.add(new JLabel("Name:"));
                inputPanel.add(subName);
                inputPanel.add(new JLabel("Streetname:"));
                inputPanel.add(streetName);
                inputPanel.add(new JLabel("Housenumber:"));
                inputPanel.add(houseNumber);
                inputPanel.add(new JLabel("City:"));
                inputPanel.add(city);
                // The input panel is shown to the user

                // Create SubDao to enter data into database
                SubscriptionDAO subDao = new SubscriptionDAO(new DatabaseConnector());
                // The while loop is used to show the inputfields again in case of wrongly entered data
                while (isRunning) {
                    try {
                        // Show input fields to user where he can enter his subscription information
                        int n = JOptionPane.showConfirmDialog(null, inputPanel, "Enter your information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                        // If user presses cancel or exit button, close dialog
                        if (n == JOptionPane.CANCEL_OPTION || n == JOptionPane.CLOSED_OPTION) {
                            isRunning = false;
                            break;
                        }
                        // Check if all fields have been filled in
                        if (subName.getText().length() > 0 && streetName.getText().length() > 0 && houseNumber.getText().length() > 0 && houseNumber.getText().length() <= 5 && city.getText().length() > 0) {
                            // Call the insert() method, which inserts the data into the Subscription table in the database
                            subDao.insert(subName.getText(), streetName.getText(), houseNumber.getText(), city.getText());
                            isRunning = false;

                            // Update the comboBox.
                            loadSubscriptionComboboxData();

                            // Check if the entered houseNumber doesn't exceed the limit. If it does, show an error message
                        } else if (houseNumber.getText().length() > 5) {
                            JOptionPane.showMessageDialog(inputPanel, "The housenumber can only be 5 characters long");
                        } else {
                            // If the fields are empty, show an error message
                            JOptionPane.showMessageDialog(inputPanel, "These fields cannot be empty");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        constraints.insets = new Insets(0, 0, 5, 1);
        constraints.anchor = GridBagConstraints.EAST;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        this.add(createNewSubButton, constraints);

        connectedProfilesJTable = new JTable();
        DefaultTableModel defaultTableModel = (DefaultTableModel) connectedProfilesJTable.getModel();
        loadConnectedProfiles((int)subsDropDown.getSelectedItem());
        defaultTableModel.setDataVector(data, columnNames);

        subsDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (subsDropDown.getSelectedItem() != null) {
                    loadConnectedProfiles((int)subsDropDown.getSelectedItem());
                    loadSubscriberInfo((int)subsDropDown.getSelectedItem());
                    defaultTableModel.setDataVector(data, columnNames);
                }
            }
        });

        JScrollPane jScrollPane = new JScrollPane(connectedProfilesJTable);

        constraints.insets = new Insets(0,0,10,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 4;
        this.add(jScrollPane, constraints);


        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField(6);
        nameLabel.setLabelFor(nameTextField);

        constraints.insets = new Insets(0,0,0,60);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(nameLabel, constraints);

        constraints.insets = new Insets(0,60,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(nameTextField, constraints);

        JLabel streetLabel = new JLabel("Street:");
        streetTextField = new JTextField(10);
        streetLabel.setLabelFor(streetTextField);

        constraints.insets = new Insets(0,0,0,80);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(streetLabel, constraints);

        constraints.insets = new Insets(0,80,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(streetTextField, constraints);

        JLabel houseNrLabel = new JLabel("House Nr:");
        houseNrTextField = new JTextField(3);
        houseNrLabel.setLabelFor(houseNrTextField);

        constraints.insets = new Insets(0,0,0,60);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(houseNrLabel, constraints);

        constraints.insets = new Insets(0,60,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 2;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(houseNrTextField, constraints);

        JLabel cityLabel = new JLabel("City:");
        cityTextField = new JTextField(6);
        cityLabel.setLabelFor(cityTextField);

        loadSubscriberInfo((int)subsDropDown.getSelectedItem());

        constraints.insets = new Insets(0,0,0,75);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(cityLabel, constraints);

        constraints.insets = new Insets(0,75,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 3;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        this.add(cityTextField, constraints);

        // This button should save the input from the textfields above.
        saveChangesButton = new JButton("Save changes");
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SubscriptionDAO subDao = new SubscriptionDAO(new DatabaseConnector());
                try {
                    int subID = Integer.parseInt(subsDropDown.getSelectedItem().toString());
                    String subName = nameTextField.getText();
                    String subStreet = streetTextField.getText();
                    String houseNumber = houseNrTextField.getText();
                    String city = cityTextField.getText();

                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", "", JOptionPane.OK_CANCEL_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        subDao.update(subName, subStreet, houseNumber, city, subID);
                        loadEverything();
                        JOptionPane.showMessageDialog(null, "Subscription Updated");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        constraints.insets = new Insets(10,0,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        this.add(saveChangesButton, constraints);

        // This button should delete the currently selected subscription from the database (and the application).
        deleteSubButton = new JButton("Delete");
        deleteSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SubscriptionDAO subDAO = new SubscriptionDAO(new DatabaseConnector());
                try {
                    // Get the selected Subscription ID
                    int subID = Integer.parseInt(subsDropDown.getSelectedItem().toString());
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure?", "", JOptionPane.OK_CANCEL_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean isDeleted = false;
                        isDeleted = subDAO.delete(subID);
                        loadSubscriptionComboboxData();
                        if (isDeleted)
                            JOptionPane.showMessageDialog(null, "Subscription deleted");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        constraints.insets = new Insets(10,0,0,0);
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        this.add(deleteSubButton, constraints);

    }
}
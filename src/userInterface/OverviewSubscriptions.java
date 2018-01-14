package userInterface;

import applicationLogic.Subscription;
import applicationLogic.UserProfile;
import database.DatabaseConnector;
import database.SubscriptionDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

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

    public OverviewSubscriptions() {
        allUserProfiles = Subscription.getAllUserProfiles();
        allSubscriptions = Subscription.getAllSubscriptions();
        createCulumnNames();
        createComponents();
    }

    /**
     * Create the testData for the table.
     * TODO: Fill the profiles object variable in this function.
     */
    private void createCulumnNames() {
        columnNames = new String[]{
                "Connected profiles",
                "Age"
        };
    }

    /**
     * Load the table data with the connected UserProfiles
     */
    private void loadConnectedProfiles(int subId) {
        ArrayList<UserProfile> connectedUserProfiles = new ArrayList<>();

        for (UserProfile userProfile : allUserProfiles) {
            if (subId == userProfile.getSubId()) {
                connectedUserProfiles.add(userProfile);
            }
        }

        data = new Object[connectedUserProfiles.size()][2];
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
        SubscriptionDAO subDAO = new SubscriptionDAO(new DatabaseConnector());
        Subscription sub = subDAO.getSubscription(subId);
        nameTextField.setText(sub.getSubName());
        streetTextField.setText(sub.getStreet());
        houseNrTextField.setText(sub.getHouseNumber());
        cityTextField.setText(sub.getCity());
    }

    @Override
    public void createComponents() {
        JLabel subsDropDownLabel = new JLabel("Select subscription");
        subsDropDown = new JComboBox();
        subsDropDownLabel.setLabelFor(subsDropDown);

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

        connectedProfilesJTable = new JTable();
        DefaultTableModel defaultTableModel = (DefaultTableModel) connectedProfilesJTable.getModel();
        loadConnectedProfiles((int)subsDropDown.getSelectedItem());
        defaultTableModel.setDataVector(data, columnNames);

        subsDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (subsDropDown.getSelectedItem() != null) {
                    loadConnectedProfiles((int)subsDropDown.getSelectedItem());
                    defaultTableModel.setDataVector(data, columnNames);
                    loadSubscriberInfo((int)subsDropDown.getSelectedItem());
                }
            }
        });

        JScrollPane jScrollPane = new JScrollPane(connectedProfilesJTable);

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField("");
        nameLabel.setLabelFor(nameTextField);

        JLabel streetLabel = new JLabel("Street:");
        streetTextField = new JTextField("");
        streetLabel.setLabelFor(streetTextField);

        JLabel houseNrLabel = new JLabel("House Nr:");
        houseNrTextField = new JTextField("");
        houseNrLabel.setLabelFor(houseNrTextField);

        JLabel cityLabel = new JLabel("City:");
        cityTextField = new JTextField("");
        cityLabel.setLabelFor(cityTextField);

        loadSubscriberInfo((int)subsDropDown.getSelectedItem());

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
                        System.out.println("Subscription information updated");
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

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
                        subDAO.delete(subID);
                        System.out.println("Subscription deleted");
                        loadSubscriptionComboboxData();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        this.add(subsDropDownLabel);
        this.add(subsDropDown);

        this.add(createNewSubButton);

        this.add(jScrollPane);

        this.add(nameLabel);
        this.add(nameTextField);

        this.add(streetLabel);
        this.add(streetTextField);

        this.add(houseNrLabel);
        this.add(houseNrTextField);

        this.add(cityLabel);
        this.add(cityTextField);

        this.add(saveChangesButton);
        this.add(deleteSubButton);
    }
}
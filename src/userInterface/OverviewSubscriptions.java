package userInterface;

import applicationLogic.Subscription;
import database.DatabaseConnector;
import database.SubscriptionDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

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
    private String[] columnNames;
    private Object[][] data;

    public OverviewSubscriptions() {
        createTestData();
        createComponents();
    }

    /**
     * Create the testData for the table.
     * TODO: Fill the profiles object variable in this function.
     */
    private void createTestData() {
        columnNames = new String[]{
                "Connected profiles",
        };

        data = new Object[][]{
                {"Arjan"},
                {"Sam"},
                {"Niek"},
        };
    }

    @Override
    public void createComponents() {
        JLabel subsDropDownLabel = new JLabel("Select subscription");
        subsDropDown = new JComboBox();
        subsDropDownLabel.setLabelFor(subsDropDown);

        // Create DAO for getting all the registered subscriptions
        SubscriptionDAO getSubs = new SubscriptionDAO(new DatabaseConnector());
        // Create Set to store subscriptions
        Set<Subscription> listOfSubs = new HashSet<Subscription>();
        // Add all subscriptions found in the database
        listOfSubs.addAll(getSubs.getAll());
        // Loop through the set, put each found SubID in the dropdown menu
        for (Subscription sub : listOfSubs) {
            subsDropDown.addItem(sub.getSubscriptionId());
        }

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

        connectedProfilesJTable = new JTable(data, columnNames);
        JScrollPane jScrollPane = new JScrollPane(connectedProfilesJTable);

        JLabel nameLabel = new JLabel("Name:");
        nameTextField = new JTextField("[NAME]", 10);
        nameLabel.setLabelFor(nameTextField);

        JLabel streetLabel = new JLabel("Street:");
        streetTextField = new JTextField("[STREET]", 10);
        streetLabel.setLabelFor(streetTextField);

        JLabel houseNrLabel = new JLabel("House Nr:");
        houseNrTextField = new JTextField("[NR]", 3);
        houseNrLabel.setLabelFor(houseNrTextField);

        JLabel cityLabel = new JLabel("City:");
        cityTextField = new JTextField("[CITY]", 10);
        cityLabel.setLabelFor(cityTextField);

        // This button should save the input from the textfields above.
        saveChangesButton = new JButton("Save changes");

        // This button should delete the currently selected subscription from the database (and the application).
        deleteSubButton = new JButton("Delete");



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
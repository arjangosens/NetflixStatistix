package userInterface;

import applicationLogic.Subscription;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        subsDropDown = new JComboBox(new String[]{"(id 1)", "(id 2)", "(id 3)"});
        subsDropDownLabel.setLabelFor(subsDropDown);

        // This button should add a new id to the dropdown and (if possible) should focus on said new id.
        createNewSubButton = new JButton("Create new subscription");
        createNewSubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                // First the input fields are declared (id, subName, streetName, houseNumber, city)
                JTextField id = new JTextField(5);
                JTextField subName = new JTextField(40);
                JTextField streetName = new JTextField(40);
                JTextField houseNumber = new JTextField(40);
                JTextField city = new JTextField(40);
                // A new input panel is created here
                JPanel inputPanel = new JPanel();
                // The layout is set to BoxLayout and the positioning is set to the Y_Axis, so the items will be stacked vertically
                inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
                // Labels are created and added to the input panel
                inputPanel.add(new JLabel("ID: "));
                inputPanel.add(id);
                inputPanel.add(new JLabel("Name:"));
                inputPanel.add(subName);
                inputPanel.add(new JLabel("Streetname:"));
                inputPanel.add(streetName);
                inputPanel.add(new JLabel("Housenumber:"));
                inputPanel.add(houseNumber);
                inputPanel.add(new JLabel("City:"));
                inputPanel.add(city);
                // The input panel is shown to the user
                JOptionPane.showConfirmDialog(null, inputPanel, "Enter your information", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
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
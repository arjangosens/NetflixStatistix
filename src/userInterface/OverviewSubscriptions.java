package userInterface;

import javax.swing.*;

public class OverviewSubscriptions extends JPanel implements Overview {

    private JComboBox subsDropDown;
    private JTable jTable;
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
        JLabel labelSubsDropDown = new JLabel("Select subscription");
        subsDropDown = new JComboBox(new String[]{"(id 1)", "(id 2)", "(id 3)"});
        labelSubsDropDown.setLabelFor(subsDropDown);

        jTable = new JTable(data, columnNames);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        this.add(labelSubsDropDown);
        this.add(subsDropDown);
        this.add(jScrollPane);
    }
}
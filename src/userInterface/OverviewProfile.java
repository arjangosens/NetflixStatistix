package userInterface;

import applicationLogic.UserProfile;

import javax.swing.*;
import java.util.ArrayList;

public class OverviewProfile extends JPanel implements Overview {

    private JComboBox profileDropDown;
    private ArrayList<UserProfile> profiles;
    private JTable jTable;
    private String[] columnNames;
    private Object[][] data;
    private JRadioButton showFilms;
    private JRadioButton showEpisodes;

    public OverviewProfile() {
        createTestData();
        createComponents();
    }

    /**
     * Create the testData for the table.
     * TODO: Fill the profiles object variable in this function.
     */
    private void createTestData() {
        columnNames = new String[]{
                "Title",
                "iets anders",
        };

        data = new Object[][]{
                {"123", "321"},
                {"123", "321"},
                {"123", "321"},
        };
    }

    @Override
    public void createComponents() {
        JLabel labelProfileDropDown = new JLabel("Select profile");
        profileDropDown = new JComboBox(new String[]{"Arjan", "Niek", "Sam"});
        labelProfileDropDown.setLabelFor(profileDropDown);

        JLabel labelForFilms = new JLabel("Show films");
        showFilms = new JRadioButton();
        labelForFilms.setLabelFor(showFilms);

        JLabel labelForEpisodes = new JLabel("Show episodes");
        showEpisodes = new JRadioButton();
        labelForEpisodes.setLabelFor(showEpisodes);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(showFilms);
        buttonGroup.add(showEpisodes);

        jTable = new JTable(data, columnNames);
        JScrollPane jScrollPane = new JScrollPane(jTable);

        this.add(labelProfileDropDown);
        this.add(profileDropDown);
        this.add(labelForFilms);
        this.add(showFilms);
        this.add(labelForEpisodes);
        this.add(showEpisodes);
        this.add(jScrollPane);
    }
}

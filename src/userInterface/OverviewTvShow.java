package userInterface;

import applicationLogic.Episode;
import applicationLogic.TVshow;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OverviewTvShow extends JPanel implements Overview{
    private JComboBox tvShowDropDown;
    private ArrayList<TVshow> tVshows;
    private ArrayList<Episode> episodes;
    private JTable jTable;
//    private String[] columnNames;
//    private Object[][] data;

    public OverviewTvShow() {
        createComponents();
    }

    @Override
    public void createComponents() {
        String[] testItems = {
                "The walking dead",
                "Breaking Bad",
                "Friends"
        };

        String[] columnNames = {
            "Title",
            "Genre",
            "Language",
            "Age"
        };

        Object[][] data = {
                {"The walking dead", "Zombi", "English", 18},
                {"Breaking Bad", "IDK", "Ënglish", 12},
                {"Friends", "Saai", "English", 3}
        };

        tvShowDropDown = new JComboBox(testItems);
        JLabel forTvshowDropdown = new JLabel("Select a tvShow");
        forTvshowDropdown.setLabelFor(tvShowDropDown);

        jTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(jTable);

        this.add(forTvshowDropdown);
        this.add(tvShowDropDown);
        this.add(scrollPane);
    }
}

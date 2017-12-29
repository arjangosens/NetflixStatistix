package userInterface;

import applicationLogic.Episode;
import applicationLogic.TVshow;
import database.TvShowDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class OverviewTvShow extends JPanel implements Overview {
    private JComboBox tvShowDropDown;
    private String[] tvShowTitles;
//    private ArrayList<Episode> episodes;
    private JTable jTable;
//    private String[] columnNames;
//    private Object[][] data;

    public OverviewTvShow() {
        Set<TVshow> tvShows = TVshow.getAll();
        prepareContent(tvShows);
        createComponents();
    }

    private void prepareContent(Set<TVshow> tvShows) {
        tvShowTitles = new String[tvShows.size()];
        ArrayList<TVshow> arrayList = new ArrayList<>();

        for (TVshow tVshow : tvShows) {
            Collections.addAll(arrayList, tVshow);
        }

        for (int i = 0; i < tvShowTitles.length; i++) {
            tvShowTitles[i] = arrayList.get(i).toString();
        }
    }

    @Override
    public void createComponents() {
        String[] columnNames = {
                "Episode number",
                "Title",
                "Genre",
                "Language",
                "Age"
        };

        Object[][] data = {
                {"S1E1", "The walking dead", "Zombi", "English", 18},
                {"S1E2", "Breaking Bad", "IDK", "Ënglish", 12},
                {"S2E1", "Friends", "Saai", "English", 3}
        };

        tvShowDropDown = new JComboBox(tvShowTitles);
        JLabel forTvshowDropdown = new JLabel("Select a tvShow");
        forTvshowDropdown.setLabelFor(tvShowDropDown);

        jTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(jTable);

        this.add(forTvshowDropdown);
        this.add(tvShowDropDown);
        this.add(scrollPane);
    }
}

package userInterface;

import applicationLogic.Episode;
import applicationLogic.TVshow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class OverviewTvShow extends JPanel implements Overview {
    private JComboBox tvShowDropDown;
    private String[] tvShowTitles;
    private ArrayList<TVshow> allTvShows;
    private String[] columnNames;
    private Object[][] data;

    OverviewTvShow() {
        Set<TVshow> tvShows = TVshow.getAll();
        Set<Episode> episodes = Episode.getAll();
        prepareContent(tvShows, episodes);
        createComponents();
    }

    private void prepareContent(Set<TVshow> tvShows, Set<Episode> episodes) {
        tvShowTitles = new String[tvShows.size()];
        allTvShows = new ArrayList<>();
        ArrayList<Episode> allEpisodes = new ArrayList<>();

        columnNames = new String[]{
                "Episode number",
                "Title",
                "Genre",
                "Language",
                "Age",
                "Duration"
        };

        for (TVshow tVshow : tvShows) {
            Collections.addAll(allTvShows, tVshow);
        }

        for (Episode episode : episodes) {
            Collections.addAll(allEpisodes, episode);
        }

        for (TVshow tvShow : allTvShows) {
            ArrayList<Episode> episodesByTvShow = new ArrayList<>();
            for (Episode episode : allEpisodes) {
                if (episode.getTvShowId() == tvShow.getTvshowId()) {
                    episode.setTvshow(tvShow);
                    episodesByTvShow.add(episode);
                }
            }
            tvShow.setEpisodes(episodesByTvShow);
        }

        for (int i = 0; i < tvShowTitles.length; i++) {
            tvShowTitles[i] = allTvShows.get(i).toString();
        }
    }

    @Override
    public void createComponents() {
        tvShowDropDown = new JComboBox(tvShowTitles);
        JLabel forTvshowDropdown = new JLabel("Select a tvShow");
        forTvshowDropdown.setLabelFor(tvShowDropDown);

        try {
            tableData(tvShowDropDown.getSelectedItem().toString());
        } catch (NullPointerException e) {
            System.out.println("Could not get TvShow table data");
        }
        if (data == null) {
            data = new Object[][]{};
        }
        JTable jTable = new JTable();
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable.getModel();
        defaultTableModel.setDataVector(data, columnNames);

        tvShowDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tableData(tvShowDropDown.getSelectedItem().toString());
                defaultTableModel.setDataVector(data, columnNames);
            }
        });

        JScrollPane scrollPane = new JScrollPane(jTable);

        this.add(forTvshowDropdown);
        this.add(tvShowDropDown);
        this.add(scrollPane);
    }

    /**
     * Overrides the current data in the jTable with the new values
     * @param currentTvShow The tvShow that is selected in the combobox
     */
    private void tableData(String currentTvShow) {
        ArrayList<Episode> theEpisodes = new ArrayList<>();

        for (TVshow tVshow : allTvShows) {
            if (tVshow.getTitle().equals(currentTvShow)) {
                theEpisodes.addAll(tVshow.getEpisodes());
                break;
            }
        }

        data = new Object[theEpisodes.size()][6];
        for (int i = 0; i < theEpisodes.size(); i++) {
            Object[] y = new Object[6];
            y[0] = theEpisodes.get(i).getEpisodeNumber();
            y[1] = theEpisodes.get(i).getTitle();
            y[2] = theEpisodes.get(i).getTvshow().getGenre();
            y[3] = theEpisodes.get(i).getTvshow().getLanguage();
            y[4] = theEpisodes.get(i).getTvshow().getAge();
            y[5] = theEpisodes.get(i).getDuration();

            data[i] = y;
        }
    }
}

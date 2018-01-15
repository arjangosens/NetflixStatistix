package userInterface;

import applicationLogic.Episode;
import applicationLogic.TVshow;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class OverviewTvShow extends JPanel implements Overview {
    private JComboBox tvShowDropDown;
    private String[] tvShowTitles;
    private ArrayList<TVshow> allTvShows;
    private ArrayList<Episode> allEpisodes;
    private String[] columnNames;
    private Object[][] data;

    OverviewTvShow() {
        // Add all TvShows from the database to allTvShows ArrayList
        allTvShows = TVshow.getAll();
        // Add all Episodes from the database to allEpsidodes ArrayList
        allEpisodes = Episode.getAll();
        // Call prepareContent(), which prepares the table data
        prepareContent();
        // Call createComponents(), which creates the gui-components
        createComponents();
    }

    /**
     * This method creates the columnNames and sets the tvShow for an episode and sets the episodes for a tvShow
     */
    private void prepareContent() {
        // Define new String array for the tvShow titles
        tvShowTitles = new String[allTvShows.size()];

        // Define the columnnames which are to be shown in the table
        columnNames = new String[]{
                "Episode number",
                "Title",
                "Genre",
                "Language",
                "Age",
                "Duration"
        };

        // Loop through all the found tvShows
        for (TVshow tvShow : allTvShows) {
            // Define an ArrayList to store all episodes per show
            ArrayList<Episode> episodesByTvShow = new ArrayList<>();
            // Loop through all episodes
            for (Episode episode : allEpisodes) {
                // Check if the episode primary key is equal to the episode foreign key
                if (episode.getTvShowId() == tvShow.getTvshowId()) {
                    // Add the right tvShow to the corresponding episode
                    episode.setTvshow(tvShow);
                    episodesByTvShow.add(episode);
                }
            }
            tvShow.setEpisodes(episodesByTvShow);
        }

        // Set Tvshow titles in table
        for (int i = 0; i < tvShowTitles.length; i++) {
            tvShowTitles[i] = allTvShows.get(i).toString();
        }
    }

    @Override
    public void createComponents() {
        // Set the layout of the TvShow Overview
        setLayout(new GridBagLayout());
        // Define new GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();

        // Define a new combobox which is used as dropdown menu to show tv shows
        tvShowDropDown = new JComboBox(tvShowTitles);
        // Define a label for the TvShow dropdown-menu
        JLabel forTvshowDropdown = new JLabel("Select a tvShow");
        // Set the label for the TvShow dropdown-menu
        forTvshowDropdown.setLabelFor(tvShowDropDown);

        // Set the external padding between the TvShowDropdownmenu label and the edges of the layout
        constraints.insets = new Insets(0, 0, 0, 0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 0;
        // Set top most cell location of the component
        constraints.gridy = 0;
        // Set number of cells in a row
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        // Add the JLabel and the constraints to the TvShow overview page
        this.add(forTvshowDropdown, constraints);

        // Set the external padding between the TvShowDropdownmenu and the edges of the layout
        constraints.insets = new Insets(0, 0, 20, 0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 0;
        // Set top most cell location of the component
        constraints.gridy = 1;
        // Set number of cells in a row
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        // Add the TvShow dropdown menu and the constraints to the TvShow overview page
        this.add(tvShowDropDown, constraints);

        try {
            // Try to get the TvShow data from the database and add it to the table
            tableData(tvShowDropDown.getSelectedItem().toString());
        } catch (NullPointerException e) {
            System.out.println("Could not get TvShow table data");
        }
        if (data == null) {
            // Create new data object which is used as table data
            data = new Object[][]{};
        }
        // Define a new table to show tvshow Data
        JTable jTable = new JTable();
        // Define a table model to be used
        DefaultTableModel defaultTableModel = (DefaultTableModel) jTable.getModel();
        // Load the data into the table
        defaultTableModel.setDataVector(data, columnNames);

        // Add an ActionListener to the tvShowDropDown menu
        tvShowDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the currently selected TvShow
                tableData(tvShowDropDown.getSelectedItem().toString());
                // Add corresponding data to table
                defaultTableModel.setDataVector(data, columnNames);
            }
        });

        // Set a scrollpane in the table, this makes the table scrollable
        JScrollPane scrollPane = new JScrollPane(jTable);

        // Set the external padding between the scrollPane and the edges of the layout
        constraints.insets = new Insets(0, 0, 0, 0);
        // Set the anchorpoint used by the layout
        constraints.anchor = GridBagConstraints.CENTER;
        // Set leading cell location of the component
        constraints.gridx = 0;
        // Set number of cells in a row
        constraints.gridy = 2;
        // Set number of cells in a row
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        // Add the TvShow dropdown menu and the constraints to the TvShow overview page
        this.add(scrollPane, constraints);
    }

    /**
     * Overrides the current data in the jTable with the new values
     *
     * @param currentTvShow The tvShow that is selected in the combobox
     */
    private void tableData(String currentTvShow) {
        // Define arrayList to store episodes
        ArrayList<Episode> theEpisodes = new ArrayList<>();

        // Loop through tvShows from database
        for (TVshow tVshow : allTvShows) {
            // Check if title of the TvShow is equal to the currently selected TvShow in the dropdown-menu
            if (tVshow.getTitle().equals(currentTvShow)) {
                // Add all found episodes to the Episode dropdown menu
                theEpisodes.addAll(tVshow.getEpisodes());
                break;
            }
        }

        // Define a new Object array to store table data
        data = new Object[theEpisodes.size()][6];
        // Loop through the Episodes ArrayList
        for (int i = 0; i < theEpisodes.size(); i++) {
            // Define object Array to store Episode
            Object[] y = new Object[6];
            // Get Episode number and add it to the episode Array
            y[0] = theEpisodes.get(i).getEpisodeNumber();
            // Get Episode title and add it to the episode Array
            y[1] = theEpisodes.get(i).getTitle();
            // Get Episode genre and add it to the episode Array
            y[2] = theEpisodes.get(i).getTvshow().getGenre();
            // Get Episode language and add it to the episode Array
            y[3] = theEpisodes.get(i).getTvshow().getLanguage();
            // Get Episode age rating and add it to the episode Array
            y[4] = theEpisodes.get(i).getTvshow().getAge();
            // Get Episode duration and add it to the episode Array
            y[5] = theEpisodes.get(i).getDuration();

            // Set column data
            data[i] = y;
        }
    }
}

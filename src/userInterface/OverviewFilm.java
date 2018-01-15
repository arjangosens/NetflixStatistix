package userInterface;

import applicationLogic.Film;
import database.FilmDAO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class OverviewFilm extends JPanel implements Overview {
    private ArrayList<Film> films = new ArrayList<>();
    private JTable jTable;
    private String[] columnNames;
    private Object[][] data;

    public OverviewFilm(){
        // Get all Films from the database and store them in Set filmSet
        Set<Film> filmSet = Film.getAll();
        // Load table columns and data
        prepareContent(filmSet);
        // Create gui-components
        createComponents();
    }

    private void prepareContent(Set<Film> filmSet) {
        // Create column names
        columnNames = new String[]{
                "Title",
                "Genre",
                "Language",
                "Duration in minutes",
                "Age"
        };

        // Loop through set of Films
        for (Film film : filmSet) {
            // Add all films to the films ArrayList
            Collections.addAll(films, film);
        }

        // Define a new data Object, which is used as the table data
        data = new Object[films.size()][5];
        // Loop through the films ArrayList
        for (int i = 0; i < films.size(); i++) {
            // Define a new object in which the column values will be stored
            Object[] x = new Object[5];
            // The first column will hold the Film Title
            x[0] = films.get(i).getTitle();
            // The second column will hold the Film Genre
            x[1] = films.get(i).getGenre();
            // The third column will hold the Film Language
            x[2] = films.get(i).getLanguage();
            // The fourth column will hold the Film Duration
            x[3] = films.get(i).getDuration();
            // The fifth column will hold the Film Age
            x[4] = films.get(i).getAge();

            // Set the column data
            data[i] = x;
        }
    }

    @Override
    public void createComponents() {
        // Set the layout of the OverViewFilm to GridBagLayout
        setLayout(new GridBagLayout());
        // Define new GridBagConstraints
        GridBagConstraints constraints = new GridBagConstraints();

        // Create JTable to show Film information
        jTable = new JTable(data, columnNames);
        // Set a scrollpane in the table, this makes the table scrollable
        JScrollPane scrollPane = new JScrollPane(jTable);

        // Add the Film dropdown menu and the constraints to the Film overview page
        this.add(scrollPane, constraints);
    }
}

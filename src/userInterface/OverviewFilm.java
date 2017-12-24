package userInterface;

import applicationLogic.Film;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OverviewFilm extends JPanel implements Overview {
    private JComboBox filmDropDown;
    private ArrayList<Film> films;
    private JTable jTable;
    private String[] columnNames;
    private Object[][] data;

    public OverviewFilm(){
        createComponents();
    }

//    public OverviewFilm(String[] columnNames, Object[][] data) {
//        this.filmDropDown = new JComboBox();
//        this.films =  new ArrayList<Film>();
//        this.jTable = new JTable();
//        this.columnNames = columnNames;
//        this.data = data;
//
//        createComponents();
//    }

    @Override
    public void createComponents() {
        // Create test items for comboBox
        String[] testValues = {
                "Select film", "Lord of the Rings",
                "Pepa Pig",
                "Frozen",
                "Buurman en Buurman",
                "The pick of destiny"
        };

        // Create comboBox
        filmDropDown = new JComboBox(testValues);

        // Create Label for combobox
        JLabel labelFilmDropDown = new JLabel("Select a film");
        labelFilmDropDown.setLabelFor(filmDropDown);

        // Create column names
        String[] columnNames = {
                "Title",
                "Genre",
                "Language",
                "Duration in minutes",
                "Age"
        };

        // Create test data for JTable
        Object[][] testData = {
            {"Lord of the Rings", "Leuk", "English", 200, 12},
            {"Pepa Pig", "Child", "Dutch", 120, 3},
            {"Frozen", "Leuk", "English", 97, 9},
            {"Buurman en Buurman", "Facking Leuk!", "Dutch", 30, 3},
            {"The pick of destiny", "Awesome", "English", 120, 12}
        };

        // Create JTable
        jTable = new JTable(testData, columnNames);
        JScrollPane scrollPane = new JScrollPane(jTable);

        this.add(labelFilmDropDown);
        this.add(filmDropDown);
        this.add(scrollPane);
    }
}

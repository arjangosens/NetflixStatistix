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
        Set<Film> filmSet = Film.getAll();
        prepareContent(filmSet);
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

        for (Film film : filmSet) {
            Collections.addAll(films, film);
        }

        data = new Object[films.size()][5];
        for (int i = 0; i < films.size(); i++) {
            Object[] x = new Object[5];
            x[0] = films.get(i).getTitle();
            x[1] = films.get(i).getGenre();
            x[2] = films.get(i).getLanguage();
            x[3] = films.get(i).getDuration();
            x[4] = films.get(i).getAge();

            data[i] = x;
        }
    }

    @Override
    public void createComponents() {
        // Create JTable
        jTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(jTable);

        this.add(scrollPane);
    }
}

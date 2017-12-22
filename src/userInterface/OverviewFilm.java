package userInterface;

import applicationLogic.Film;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class OverviewFilm implements Overview {
    private JComboBox filmDropDown;
    private ArrayList<Film> films;
    private JTable jTable;
    private String[] columnNames;
    private Object[][] data;

    public OverviewFilm(String[] columnNames, Object[][] data) {
        this.filmDropDown = new JComboBox();
        this.films =  new ArrayList<Film>();
        this.jTable = new JTable();
        this.columnNames = columnNames;
        this.data = data;
    }


    @Override
    public void createComponents(Container container) {

    }
}

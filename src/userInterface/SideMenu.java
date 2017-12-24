package userInterface;

import javax.swing.*;
import java.awt.*;

public class SideMenu extends JPanel{
    private JButton homeBtn;
    private JButton subsOverviewBtn;
    private JButton profileOverviewBtn;
    private JButton tvShowOverviewBtn;
    private JButton filmOverviewBtn;

    public SideMenu() {
        // Set Layout
        GridLayout gridLayout = new GridLayout(5, 1);
        gridLayout.setVgap(10);
        this.setLayout(gridLayout);

        // Create the components
        createComponents();
    }

    /**
     * Creates the components that will be viewed in the side menu
     */
    private void createComponents() {
        homeBtn = new JButton("Home");
        subsOverviewBtn = new JButton("Subscriptions");
        profileOverviewBtn = new JButton("Profile");
        tvShowOverviewBtn = new JButton("TvShow");
        filmOverviewBtn = new JButton("Film");

//        subsOverviewBtn.setPreferredSize(new Dimension( 100,50));

        this.add(homeBtn);
        this.add(subsOverviewBtn);
        this.add(profileOverviewBtn);
        this.add(tvShowOverviewBtn);
        this.add(filmOverviewBtn);
    }

    public JButton getSubsOverviewBtn() {
        return subsOverviewBtn;
    }

    public JButton getProfileOverviewBtn() {
        return profileOverviewBtn;
    }

    public JButton getTvShowOverviewBtn() {
        return tvShowOverviewBtn;
    }

    public JButton getFilmOverviewBtn() {
        return filmOverviewBtn;
    }
}

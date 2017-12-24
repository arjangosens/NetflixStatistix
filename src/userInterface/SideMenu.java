package userInterface;

import javax.swing.*;
import java.awt.*;

public class SideMenu extends JPanel{
    private JButton subsOverviewBtn;
    private JButton profileOverviewBtn;
    private JButton tvShowOverviewBtn;
    private JButton filmOverviewBtn;

    public SideMenu() {
        // Set Layout
        GridLayout gridLayout = new GridLayout(4, 1);
        gridLayout.setVgap(10);
        this.setLayout(gridLayout);

        // Create the components
        createComponents();
    }

    /**
     * Creates the components that will be viewed in the side menu
     */
    private void createComponents() {
        subsOverviewBtn = new JButton("Subscriptions");
        profileOverviewBtn = new JButton("Profile");
        tvShowOverviewBtn = new JButton("TvShow");
        filmOverviewBtn = new JButton("Film");

        subsOverviewBtn.setPreferredSize(new Dimension( 100,50));

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

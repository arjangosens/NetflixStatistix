package userInterface;

import javax.swing.*;

public class SideMenu extends JPanel{
    private JButton subsOverviewBtn;
    private JButton profileOverviewBtn;
    private JButton tvShowOverviewBtn;
    private JButton filmOverviewBtn;

    public SideMenu() {
        // Set Layout
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set background to check how big this panel is
        this.setBackground(java.awt.Color.BLUE);

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

        subsOverviewBtn.setSize(200, 100);
        profileOverviewBtn.setSize(200, 100);
        tvShowOverviewBtn.setSize(200, 100);
        filmOverviewBtn.setSize(200, 100);

        JLabel header = new JLabel("Overviews");

        this.add(header);
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

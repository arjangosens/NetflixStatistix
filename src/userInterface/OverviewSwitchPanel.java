package userInterface;

import javax.swing.*;
import java.awt.*;

/**
 * This panel holds the overviews that are used in the CardLayout
 */
public class OverviewSwitchPanel extends JPanel {

    private HomeScreen homeScreen;
    private OverviewProfile overviewProfile;
    private OverviewTvShow overviewTvShow;
    private OverviewFilm overviewFilm;

    public final static String HOMESCREENPANEL = "HomeScreenPanel";
    public final static String OVERVIEWPROFILEPANEL = "OverviewProfilePanel";
    public final static String OVERVIEWTVSHOW = "OverviewTvShowPanel";
    public final static String OVERVIEWFILMPANEL = "OverviewFilmPanel";

    public OverviewSwitchPanel() {
        // Create the panels
        homeScreen = new HomeScreen();
        overviewProfile = new OverviewProfile();
        overviewTvShow = new OverviewTvShow();
        overviewFilm = new OverviewFilm();

        // Set the actual CardLayout
        this.setLayout(new CardLayout());

        // Add the panels to the CardLayout
        this.add(homeScreen, HOMESCREENPANEL);
        this.add(overviewProfile, OVERVIEWPROFILEPANEL);
        this.add(overviewTvShow, OVERVIEWTVSHOW);
        this.add(overviewFilm, OVERVIEWFILMPANEL);
    }

    /**
     * This method will switch between the panels that exists in the CardLayout indicated by the parameter
     * @param panelIndicator The string that indicates the panel that the user will view
     */
    public void switchPanels(String panelIndicator) {
        CardLayout cardLayout = (CardLayout) this.getLayout();
        cardLayout.show(this, panelIndicator);
    }
}

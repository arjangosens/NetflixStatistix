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
    private OverviewSubscriptions overviewSubscriptions;

    public final static String HOMESCREENPANEL = "HomeScreenPanel";
    public final static String OVERVIEWPROFILEPANEL = "OverviewProfilePanel";
    public final static String OVERVIEWTVSHOW = "OverviewTvShowPanel";
    public final static String OVERVIEWFILMPANEL = "OverviewFilmPanel";
    public final static String OVERVIEWSUBSCRIPTIONS = "OverviewSubscriptionsPanel";

    public OverviewSwitchPanel() {
        // Create the panels
        homeScreen = new HomeScreen();
        overviewProfile = OverviewProfile.getInstance();
        overviewTvShow = new OverviewTvShow();
        overviewFilm = new OverviewFilm();
        overviewSubscriptions = OverviewSubscriptions.getInstance();

        // Set the layout to CardLayout
        this.setLayout(new CardLayout());

        // Add the panels to the CardLayout
        this.add(homeScreen, HOMESCREENPANEL);
        this.add(overviewProfile, OVERVIEWPROFILEPANEL);
        this.add(overviewTvShow, OVERVIEWTVSHOW);
        this.add(overviewFilm, OVERVIEWFILMPANEL);
        this.add(overviewSubscriptions, OVERVIEWSUBSCRIPTIONS);
    }

    /**
     * This method will switch between the panels that exists in the CardLayout indicated by the parameter
     * @param panelIndicator The string that indicates the panel that the user will view
     */
    public void switchPanels(String panelIndicator) {
        // Define a CardLayout to be used by the panelIndicator
        CardLayout cardLayout = (CardLayout) this.getLayout();
        // Show the panel with the corresponding layout
        cardLayout.show(this, panelIndicator);
    }
}
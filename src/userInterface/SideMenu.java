package userInterface;

import applicationLogic.OverviewSwitchController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideMenu extends JPanel{
    private JButton homeBtn;
    private JButton subsOverviewBtn;
    private JButton profileOverviewBtn;
    private JButton tvShowOverviewBtn;
    private JButton filmOverviewBtn;

    public SideMenu() {
        // Define a new GridLayout
        GridLayout gridLayout = new GridLayout(5, 1);
        // Set vertical gap between components
        gridLayout.setVgap(10);
        // Set the layout of the SideMenu to GridLayout
        this.setLayout(gridLayout);

        // Create the components
        createComponents();
    }

    /**
     * Creates the components that will be viewed in the side menu
     */
    private void createComponents() {
        // Define the sidemenu-buttons
        homeBtn = new JButton("Home");
        subsOverviewBtn = new JButton("Subscriptions");
        profileOverviewBtn = new JButton("Profile");
        tvShowOverviewBtn = new JButton("TvShow");
        filmOverviewBtn = new JButton("Film");

        // Add actionlisteners to the buttons
        homeBtn.addActionListener(new OverviewSwitchController(OverviewSwitchPanel.HOMESCREENPANEL));
        profileOverviewBtn.addActionListener(new OverviewSwitchController(OverviewSwitchPanel.OVERVIEWPROFILEPANEL));
        profileOverviewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OverviewProfile.getInstance().loadEverything();
            }
        });
        tvShowOverviewBtn.addActionListener(new OverviewSwitchController(OverviewSwitchPanel.OVERVIEWTVSHOW));
        filmOverviewBtn.addActionListener(new OverviewSwitchController(OverviewSwitchPanel.OVERVIEWFILMPANEL));
        subsOverviewBtn.addActionListener(new OverviewSwitchController(OverviewSwitchPanel.OVERVIEWSUBSCRIPTIONS));
        subsOverviewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OverviewSubscriptions.getInstance().loadEverything();
            }
        });

        // Add the buttons to the sidemenu
        this.add(homeBtn);
        this.add(subsOverviewBtn);
        this.add(profileOverviewBtn);
        this.add(tvShowOverviewBtn);
        this.add(filmOverviewBtn);
    }

    // This method returns the SubscriptionOverview button
    public JButton getSubsOverviewBtn() {
        return subsOverviewBtn;
    }

    // This method returns the ProfileOverview button
    public JButton getProfileOverviewBtn() {
        return profileOverviewBtn;
    }

    // This method returns the TvShowOverviewBtn button
    public JButton getTvShowOverviewBtn() {
        return tvShowOverviewBtn;
    }

    // This method retunrs the getFilmOverviewBtn
    public JButton getFilmOverviewBtn() {
        return filmOverviewBtn;
    }
}